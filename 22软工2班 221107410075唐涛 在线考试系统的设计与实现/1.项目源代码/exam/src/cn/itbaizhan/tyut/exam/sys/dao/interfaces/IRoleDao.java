package cn.itbaizhan.tyut.exam.sys.dao.interfaces;
//规范与系统角色相关的数据库操作，包括获取系统角色列表、新增角色、分配权限初始化页面数据、获取角色详细信息、保存角色权限、修改角色功能和获取全部角色功能。
import java.util.ArrayList;
import java.util.List;

import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.SysFunction;
import cn.itbaizhan.tyut.exam.model.Sysrole;

public interface IRoleDao {

	/*** 获取系统角色列表* @param role* @return*/
	public Pager<Sysrole> list(Sysrole role,PageControl pc);
	/*** 新增角色* @param role* @return*/
	public Integer add(Sysrole role);
	/*** 分配权限初始化页面数据* @param role* @return*/
	public List<SysFunction> initfunlist(Sysrole role);
	
	/*** 获取角色详细信息* @param role* @return*/
	public Sysrole detail(Sysrole role);
	/*** 保存角色权限* @param roleid* @param funids* @return*/
	public Integer saveright(String roleid,String[] funids);
	/*** 修改角色功能* @param fun* @return*/
	public Integer edit(Sysrole role);
	/*** 获取全部角色功能* @param fun* @return*/
	public ArrayList<Sysrole> getALL();
}
