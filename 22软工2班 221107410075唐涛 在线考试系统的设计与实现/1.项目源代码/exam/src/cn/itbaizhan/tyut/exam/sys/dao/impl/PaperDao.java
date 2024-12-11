package cn.itbaizhan.tyut.exam.sys.dao.impl;
//据库中paper表的操作，提供了方便的方法供其他类调用，实现了试卷的增加、查询、删除功能。
import java.util.List;

import cn.itbaizhan.tyut.exam.common.DBUnitHelper;
import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.Paper;
import cn.itbaizhan.tyut.exam.model.Subject;
import cn.itbaizhan.tyut.exam.sys.dao.interfaces.IPaperDao;

public class PaperDao implements IPaperDao {

	@Override
	public Integer addpaper(Paper paper) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO paper(pname,sid) SELECT ?,sid FROM " + 
			"subject where sstate = 1 ORDER BY rand() LIMIT ?";
		
		Integer rtn =DBUnitHelper.executeUpdate(sql,paper.getPname(),paper.getScount());
		
		return rtn;
	}
	
	public Pager<Paper> list(Paper paper,PageControl pc) {
		String sql = "SELECT pname,count(*) scount FROM paper GROUP BY pname" ;
//		String sql = "SELECT pid,pname,count(*) scount FROM paper GROUP BY pname" ;
		Pager<Paper> pager;
		String pid="pid";
		if(paper.getPname()!=null && !paper.getPname().equals("")){
			String sql2 = " where pname LIKE  ?  ";
			String sql3 = sql.substring(0,40) + sql2 + sql.substring(40);
			pager = DBUnitHelper.execlist(sql3, pc, Paper.class,pid, paper.getPname());
		}else{
			pager = DBUnitHelper.execlist(sql, pc, Paper.class,pid, null);	
		}
		return pager;
	}//查询paper表中的试卷列表

	@Override
	public List<Subject> subjectlist(Paper paper) {
		// TODO Auto-generated method stub
		String sql = "SELECT subject.sid,scontent,sa,sb,sc,sd,skey FROM subject,paper WHERE paper.sid = subject.sid and paper.pname = ?";
		List<Subject> list = DBUnitHelper.executeQuery(sql,Subject.class, paper.getPname());
		return list;//据给定的试卷名称查询subject表中的相关科目信息
	}

	@Override
	public List<Paper> list(Paper paper) {
		// TODO Auto-generated method stub
		String sql = "SELECT pname,count(*) scount FROM paper GROUP BY pname" ;
		List<Paper> list = DBUnitHelper.executeQuery(sql,Paper.class);
		return list;//查询paper表中的所有试卷信息
	}
	@Override
	public int delete(Paper paper){
		String sql ="DELETE  FROM paper WHERE pname=?";
		return DBUnitHelper.executeUpdate(sql, paper.getPname());
//给定的试卷名称删除paper表中的相关记录
	}
}
