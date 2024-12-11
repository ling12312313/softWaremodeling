package cn.itbaizhan.tyut.exam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class DBUnitHelper {

	/**
	 * 获取数据库链接
	 * @return
	 */
	//获取数据库链接
	public static Connection getConn(){
		Connection conn = null;	//返回类型
		try {
			
			DbUtils.loadDriver("com.mysql.cj.jdbc.Driver");//连接数据库
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3308/mysqldatabase?characterEncoding=utf-8&serverTimezone=GMT%2B8", "root", "root");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;////果在获取连接过程中发生异常，会捕获并打印异常信息。最后返回获取到的数据库连接。
	}
	
	//执行带参数的更新操作，如插入 更新 删除等
	public static Integer executeUpdate(String sql,Object ...objects){
		
		Connection conn = getConn();
		QueryRunner qr = new QueryRunner();
		Integer rtn = 0;
		try {
			if(objects == null){
				rtn = qr.update(conn, sql);
			}else{
				rtn = qr.update(conn, sql, objects);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return rtn;//关闭数据库连接并返回受影响的行数。如果在执行过程中发生异常，会捕获并打印异常信息。


	}
	

	//执行不带参数的更新操作
	public static Integer executeUpdate(String sql){
		return executeUpdate(sql, null);
	}
	
	public static <T> List<T> executeQuery(String sql,Class<T> cls,Object ...objects){
		Connection conn = getConn();
		List<T> list = null;
		try{
			QueryRunner rq = new QueryRunner();
			if(objects == null){
				list = rq.query(conn, sql,new BeanListHandler<T>(cls)); 
			}else{
				list = rq.query(conn, sql,new BeanListHandler<T>(cls),objects); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	public static <T> List<T> executeQuery(String sql,Class<T> cls){
		return executeQuery(sql,cls,null);
	}
	
	/**
	 * 带分页的查询
	 * @param sql SQL语句
	 * @param map SQL参数
	 * @param pc 分页控制对象，需要传递参数：当前第几页（currentindex）,每页显示多少行：(pagesize)
	 * 分页控件显示多少也：showpcount
	 * @return
	 */
	public static <T> Pager<T> execlist(String sql,PageControl pc,Class cls,String pk,Object...object){

		//获取总记录数sql		 
		String sqlcount = "select count(*) as count from ("+sql+") a";//定义一个字符串变量sqlcount，用于存储计算总记录数的SQL语句。该语句通过将传入的SQL语句作为子查询，并使用count函数计算总记录数。
		//获取具体数据的SQL语句
		Integer min = (pc.getCurrentindex()-1)*pc.getPagesize();//定义一个整数变量min，用于存储当前页的起始记录索引。该值通过获取PageControl对象的当前页码和每页显示的记录数，计算出起始记录索引。
		Integer max = pc.getPagesize();//定义一个整数变量max，用于存储每页显示的记录数。该值直接从PageControl对象中获取。
		String sqllist = "select * from ("+sql+") a  limit "+min+","+max;
//		String sqllist = "select * from ("+sql+") a where a."+pk+" limit "+min+","+max;//定义一个字符串变量sqllist，用于存储具体数据的SQL语句。该语句通过将传入的SQL语句作为子查询，并使用limit关键字限制查询结果的范围。
		
		Connection conn = getConn();//调用getConn方法获取数据库连接，并将返回的Connection对象赋值给变量conn。
		Pager<T> pager = new Pager<T>();
		try {
			
			QueryRunner rq = new QueryRunner();
			Object count = rq.query(conn, sqlcount, new ScalarHandler<Object>("count"), object);//使用QueryRunner对象执行计算总记录数的SQL语句，并将结果存储在变量count中。该语句使用了ScalarHandler来处理查询结果。
			List<T> list = executeQuery(sqllist,cls,object);//调用executeQuery方法执行具体数据的SQL语句，并将结果存储在变量list中。该方法需要传入SQL语句、Class对象和可变长度的对象数组
			//设置总记录数
			Integer c = 0;
			if(count!=null){
				c=Integer.parseInt(count.toString());//判断是否为空，如果不为空，则将其转换为整数类型，并赋值给变量c。
			}
			pc.setRscount(c);//将变量c的值设置为PageControl对象的总记录数
			
			pager.setList(list);//将变量list的值设置为Pager对象的列表数据。
			pc = dealpage(pc);
			pager.setPagectrl(pc);	//调用dealpage方法处理分页参数，并将返回的结果赋值给变量pc。
			DbUtils.close(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}					
		return pager;
	}
	/**
	 * 处理分页参数
	 * @param pc
	 * @return
	 */
	private static PageControl dealpage(PageControl pc){
		//获取总页数
		Integer pagecount = pc.getRscount()/pc.getPagesize();
		if(pc.getRscount()%pc.getPagesize()>0){
			pagecount++;
		}
		pc.setPagecount(pagecount);
		
		//计算最大(最小)显示页数
		Integer showpcount = pc.getShowpcount();//分页一次显示多少页
		Integer maxpage = 0;//当前显示最大页码
		Integer minpage = 0;
		Integer index = pc.getCurrentindex();//当前第几页
		if(pagecount<=showpcount){//当总页数小于等于显示的页数时
			maxpage = pagecount;
			minpage = 1;
		}else{
			Integer buff = showpcount/2; //取中间数。maxpage=index+buff
			buff = index+buff;
			if(buff<=showpcount){
				maxpage = showpcount;
				minpage = 1;
			}else if(buff<pagecount){
				maxpage = buff;
				minpage = maxpage - showpcount + 1;
				
			}else if(buff>=pagecount){
				maxpage = pagecount;
				minpage = maxpage - showpcount + 1;
			}
		}
		pc.setMaxpage(maxpage);	
		pc.setMinpage(minpage);
		return pc;
	}
	/*public static void main(String args[]) throws SQLException
	{
		Connection con=getConn();
		for(int i=0;i<=700;i++){
		String sql="insert into r values(?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, i);
		pstmt.executeUpdate();
		System.out.println("插入成功"+i);
		}
	}*/
}
