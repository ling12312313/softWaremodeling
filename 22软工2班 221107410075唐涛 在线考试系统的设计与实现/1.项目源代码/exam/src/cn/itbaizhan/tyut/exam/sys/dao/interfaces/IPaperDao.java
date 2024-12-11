package cn.itbaizhan.tyut.exam.sys.dao.interfaces;
//规范与试题相关的数据库操作，包括生成试题、查询试题（后台和首页）、查询试题内容和删除试题
import java.util.List;

import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.Paper;
import cn.itbaizhan.tyut.exam.model.Subject;

public interface IPaperDao {
	/*** 生成一份试题* @param paper* @return*/
	public Integer addpaper(Paper paper);
	
	/*** 查询全部试题(后台)* @param paper* @return*/
	public Pager<Paper> list(Paper paper,PageControl pc);
	
	/*** 查询试题内容* @param paper* @return*/
	public List<Subject> subjectlist(Paper paper);
	
	/*** 查询全部试题(首页)* @param paper* @return*/
	public List<Paper> list(Paper paper);

	public int delete(Paper paper);
}
