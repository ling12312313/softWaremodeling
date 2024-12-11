package cn.itbaizhan.tyut.exam.sys.dao.impl;
//数据库中的SYSFUNCTION表，实现对功能（Function）的增删改查操作。
import java.util.List;

import cn.itbaizhan.tyut.exam.common.DBUnitHelper;
import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.SysFunction;
import cn.itbaizhan.tyut.exam.sys.dao.interfaces.IFunDao;

public class FunDao implements IFunDao {

	public Integer addfun(SysFunction fun) {
		
		String sql = "INSERT INTO SYSFUNCTION(FUNNAME,FUNURL,FUNPID,FUNSTATE)" +
				" VALUES(?,?,?,?)";
		Integer rtn = DBUnitHelper.executeUpdate(sql, fun.getFunname(),
				fun.getFunurl(),fun.getFunpid(),fun.getFunstate());		
		return rtn;//向SYSFUNCTION表中插入一条新的记录。参数fun是一个SysFunction对象，包含了要插入的功能的信息。方法返回一个整数，表示受影响的行数
	}

	public Pager<SysFunction> list(SysFunction fun,PageControl pc) {
		String sql = "SELECT A.FUNID,A.FUNNAME,A.FUNURL,A.FUNPID,A.FUNSTATE," +
				"(CASE WHEN B.FUNNAME IS NULL THEN '无' ELSE B.FUNNAME END) AS FUNPNAME" +
				" FROM SYSFUNCTION A " +
				"LEFT OUTER JOIN SYSFUNCTION B ON A.FUNPID=B.FUNID WHERE 0=0 ";
		Pager<SysFunction> pager;
		String funid="funid";
		if(fun.getFunname()!=null && !fun.getFunname().equals("")){
			sql += " AND A.FUNNAME=? ";
			
			pager = DBUnitHelper.execlist(sql, pc, SysFunction.class,funid, fun.getFunname());
		}else{
			pager = DBUnitHelper.execlist(sql, pc, SysFunction.class,funid, null);	
		}
		return pager;//查询SYSFUNCTION表中的功能列表。参数fun是一个SysFunction对象，用于过滤查询结果；参数pc是一个PageControl对象，用于分页控制。方法返回一个Pager<SysFunction>对象，包含了查询结果和分页信息
	}

	public SysFunction detail(SysFunction fun) {
		String sql = "SELECT A.FUNID,A.FUNNAME,A.FUNURL,A.FUNPID,A.FUNSTATE," +
		"(CASE WHEN B.FUNNAME IS NULL THEN '无' ELSE B.FUNNAME END) AS FUNPNAME" +
		" FROM SYSFUNCTION A " +
		"LEFT OUTER JOIN SYSFUNCTION B ON A.FUNPID=B.FUNID WHERE A.FUNID=? ";
		
		List<SysFunction> list = DBUnitHelper.executeQuery(sql, SysFunction.class, fun.getFunid());		
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;//据给定的SysFunction对象的funid属性，查询SYSFUNCTION表中对应的功能详情。方法返回一个SysFunction对象，包含了查询到的功能信息。
		}
	}

	public Integer edit(SysFunction fun) {
		
		String sql = "UPDATE SYSFUNCTION SET FUNNAME=?,FUNURL=?," +
				"FUNSTATE=? WHERE FUNID=?";
		Integer rtn = DBUnitHelper.executeUpdate(sql,fun.getFunname(),
				fun.getFunurl(),fun.getFunstate(),fun.getFunid());
		return rtn;//更新指定funid信息
	}

}
