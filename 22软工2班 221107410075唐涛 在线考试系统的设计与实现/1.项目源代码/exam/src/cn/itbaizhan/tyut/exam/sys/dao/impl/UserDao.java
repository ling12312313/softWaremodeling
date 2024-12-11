package cn.itbaizhan.tyut.exam.sys.dao.impl;
//处理与用户相关的数据库操作，包括登录、查询、添加、编辑等。
import java.util.List;

import cn.itbaizhan.tyut.exam.common.DBUnitHelper;
import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.SysFunction;
import cn.itbaizhan.tyut.exam.model.Sysrole;
import cn.itbaizhan.tyut.exam.model.Sysuser;
import cn.itbaizhan.tyut.exam.sys.dao.interfaces.IUserDao;

public class UserDao implements IUserDao {

	public Sysuser login(Sysuser user) {

		String sql = "SELECT USERID,A.ROLEID,USERNAME,USERPWD,USERTRUENAME,USERSTATE, "
				+ "B.ROLENAME FROM SYSUSER A "
				+ "INNER JOIN SYSROLE B ON A.ROLEID=B.ROLEID "
				+ "WHERE USERSTATE=1 AND USERNAME=? AND USERPWD=? ";
		List<Sysuser> list = DBUnitHelper.executeQuery(sql, Sysuser.class, user
				.getUsername(), user.getUserpwd());
		if (list.size() > 0) {
			user = list.get(0);
		} else {
			user = null;
		}
		return user;
	}//根据用户名和密码查询用户信息，如果查询到用户且用户状态为1（启用），则返回该用户对象，否则返回null。

	public List<SysFunction> initpage(Sysuser user) {

		List<SysFunction> list = null;

		if (user.getRoleid().equals(-1)) {
			String sql = "SELECT A.FUNID,A.FUNNAME,A.FUNURL,A.FUNPID FROM SYSFUNCTION A WHERE A.FUNSTATE=1";
			list = DBUnitHelper.executeQuery(sql, SysFunction.class);
		} else {
			String sql = "SELECT A.FUNID,A.FUNNAME,A.FUNURL,A.FUNPID FROM SYSFUNCTION A "
					+ "INNER JOIN ROLERIGHT B ON A.FUNID=B.FUNID WHERE B.ROLEID=? AND A.FUNSTATE=1";
			list = DBUnitHelper.executeQuery(sql, SysFunction.class, user
					.getRoleid());
		}
		return list;
	}//根据用户角色查询对应的功能列表，如果用户角色为-1（表示未登录），则查询所有功能；否则查询该角色对应的功能。


	public Pager<Sysuser> list(Sysuser user, PageControl pc) {
		
		String sql = "SELECT USERID,ROLEID,USERNAME,USERPWD,USERTRUENAME,USERSTATE FROM " +
				" SYSUSER WHERE USERID>0 ";
		Pager<Sysuser> pager = null;
		String userid="userid";
		if(user.getUsername()!=null && !user.getUsername().equals(""))
		{
			sql += " AND USERNAME like ?";
			pager = DBUnitHelper.execlist(sql, pc, Sysuser.class,userid, user.getUsername());
		}else{
			pager = DBUnitHelper.execlist(sql, pc, Sysuser.class,userid, null);
		}
		return pager;
	}

	public Integer add(Sysuser user) {
		
		String sql = "INSERT INTO SYSUSER(ROLEID,USERNAME,USERPWD,USERTRUENAME,USERSTATE) " +
				"VALUES (?,?,?,?,?)";
		return DBUnitHelper.executeUpdate(sql, user.getRoleid(),user.getUsername(),user.getUserpwd(),user.getUsertruename(),user.getUserstate());
	}

	public Sysuser detail(Sysuser user) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM " +
		" SYSUSER WHERE USERID=? ";
		List<Sysuser> list = DBUnitHelper.executeQuery(sql, Sysuser.class, user.getUserid());
		return list.get(0);
	}

	public Integer edit(Sysuser user) {
		// TODO Auto-generated method stub
		String sql = "UPDATE SYSUSER SET ROLEID=?,USERNAME=?," +
		"USERPWD=?,USERTRUENAME=?,USERSTATE=? WHERE USERID=?";
		Integer rtn = DBUnitHelper.executeUpdate(sql,user.getRoleid(),
		user.getUsername(),user.getUserpwd(),user.getUsertruename(),user.getUserstate(),user.getUserid());
		return rtn;
	}

	public Integer toedit(Sysuser user) {
		// TODO Auto-generated method stub
		String sql = "UPDATE SYSUSER SET USERPWD=? WHERE USERID=?";
		Integer rtn = DBUnitHelper.executeUpdate(sql,user.getUserpwd(),user.getUserid());
		return rtn;
	}
	public Integer editpwd(Sysuser user) {
		// TODO Auto-generated method stub
		String sql = "UPDATE SYSUSER SET USERPWD=? WHERE USERID=?";
		Integer rtn = DBUnitHelper.executeUpdate(sql,user.getUserpwd(),user.getUserid());
		return rtn;
	}

	public Integer toeditpwd(Sysuser user) {
		// TODO Auto-generated method stub
		String sql = "UPDATE SYSUSER SET USERPWD=? WHERE USERID=?";
		Integer rtn = DBUnitHelper.executeUpdate(sql,user.getUserpwd(),user.getUserid());
		return rtn;
	}
	public Sysuser stulogin(Sysuser user) {
		String sql = "SELECT USERID,A.ROLEID,USERNAME,USERPWD,USERTRUENAME,USERSTATE, "
				+ "B.ROLENAME FROM SYSUSER A "
				+ "INNER JOIN SYSROLE B ON A.ROLEID=B.ROLEID "
				+ "WHERE USERSTATE=1 AND USERNAME=? AND USERPWD=? ";
		List<Sysuser> list = DBUnitHelper.executeQuery(sql, Sysuser.class, user
				.getUsername(), user.getUserpwd());
		if (list.size() > 0) {
			user = list.get(0);
		} else {
			user = null;
		}
		return user;
	}
}
