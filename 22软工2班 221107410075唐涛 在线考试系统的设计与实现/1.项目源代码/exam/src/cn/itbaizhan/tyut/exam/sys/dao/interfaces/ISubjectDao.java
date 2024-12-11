package cn.itbaizhan.tyut.exam.sys.dao.interfaces;
//规范与试题相关的数据库操作，包括增加一个试题、查询全部试题列表、修改试题和查询一个试题详情
import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.Subject;


public interface ISubjectDao {

	/*** 增加一个试题* @param subject* @return*/
	public Integer addsubject(Subject subject);
	/*** 查询全部试题列表* @param subject* @return*/
	public Pager<Subject> list(Subject subject,PageControl pc);
	/*** 修改试题* @param fun* @return*/
	public Integer edit(Subject subject);
	
	/*** 查询一个试题详情* @param fun* @return*/
	public Subject detail(Subject subject);
	
}
