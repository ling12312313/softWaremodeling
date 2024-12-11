package cn.itbaizhan.tyut.exam.sys.dao.interfaces;
//规范与系统功能相关的数据库操作，包括增加、查询列表、获取详细信息和修改功能。
import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.SysFunction;

public interface IFunDao {

	/**增加系统功能* @param fun* @return*/
	public Integer addfun(SysFunction fun);
	/*** 查询系统功能列表
	  @param fun* @return*/
	public Pager<SysFunction> list(SysFunction fun,PageControl pc);
	/*** 获取系统功能详细信息* @param fun* @return*/
	public SysFunction detail(SysFunction fun);
	/*** 修改系统功能* @param fun* @return*/
	public Integer edit(SysFunction fun);
	
}
