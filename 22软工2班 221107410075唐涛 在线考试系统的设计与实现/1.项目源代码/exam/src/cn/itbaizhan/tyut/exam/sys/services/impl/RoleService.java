package cn.itbaizhan.tyut.exam.sys.services.impl;

import java.util.ArrayList;
import java.util.List;

import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.SysFunction;
import cn.itbaizhan.tyut.exam.model.Sysrole;
import cn.itbaizhan.tyut.exam.sys.dao.impl.RoleDao;
import cn.itbaizhan.tyut.exam.sys.dao.interfaces.IRoleDao;
import cn.itbaizhan.tyut.exam.sys.services.interfaces.IRoleService;

public class  RoleService implements IRoleService {

	IRoleDao dao = new RoleDao();
	
	public Pager<Sysrole> list(Sysrole role, PageControl pc) {
		return dao.list(role, pc);
	}

	public Integer add(Sysrole role) {
		return dao.add(role);
	}

	public List<SysFunction> initfunlist(Sysrole role) {
		return dao.initfunlist(role);
	}

	public Sysrole detail(Sysrole role) {
		return dao.detail(role);
	}

	public Integer saveright(String roleid, String[] funids) {
		return dao.saveright(roleid, funids);
	}
	public Integer edit(Sysrole role) {
		return dao.edit(role);
	}

	public ArrayList<Sysrole> getALL() {
		// TODO Auto-generated method stub
		return dao.getALL();
	}
}
//实现与系统角色相关的业务逻辑，包括增加系统角色、查询全部系统角色列表、查询一个系统角色的初始功能列表、查询一个系统角色的详情、保存一个系统角色的功能权限和修改一个系统角色等