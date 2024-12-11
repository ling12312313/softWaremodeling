package cn.itbaizhan.tyut.exam.sys.dao.impl;
//实现了科目信息的增删改查功能。
import java.util.List;

import cn.itbaizhan.tyut.exam.common.DBUnitHelper;
import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.Subject;

import cn.itbaizhan.tyut.exam.sys.dao.interfaces.ISubjectDao;

public class SubjectDao implements ISubjectDao {

	public Integer addsubject(Subject subject) {

		String sql = "INSERT INTO SUBJECT(SCONTENT,SA,SB,SC,SD,SKEY,SSTATE)" +
				" VALUES(?,?,?,?,?,?,?)";
		Integer rtn = DBUnitHelper.executeUpdate(sql, subject.getScontent(),
				subject.getSa(),subject.getSb(),subject.getSc(),subject.getSd(),
				subject.getSkey(),subject.getSstate());
		return rtn;//向SUBJECT表中插入一条新的科目记录。参数为一个Subject对象，包含了科目的各种属性。
	}

	public Pager<Subject> list(Subject subject,PageControl pc) {
		String sql = "SELECT SID,SCONTENT,SA,SB,SC,SD,SKEY,SSTATE FROM " +
		" SUBJECT WHERE SID>0 ";
		Pager<Subject> pager;
		String sid="sid";
		if(subject.getScontent()!=null && !subject.getScontent().equals("")){
			sql += " AND SCONTENT LIKE '%' "+" ? "+" '%' ";

			pager = DBUnitHelper.execlist(sql, pc, Subject.class,sid, subject.getScontent());
		}else{
			pager = DBUnitHelper.execlist(sql, pc, Subject.class,sid, null);
		}
		return pager;
	}//据给定的条件（Subject对象）和分页控制（PageControl对象），查询符合条件的科目记录

	public Integer edit(Subject subject) {

		String sql = "UPDATE SUBJECT SET SCONTENT=?,SA=?," +
				"SB=?,SC=?,SD=?,SKEY=?,SSTATE=? WHERE SID=?";
		Integer rtn = DBUnitHelper.executeUpdate(sql,subject.getScontent(),
				subject.getSa(),subject.getSb(),subject.getSc(),subject.getSd(),subject.getSkey(),subject.getSstate(),subject.getSid());
		return rtn;
	}//更新SUBJECT表中的一条科目记录。

	public Subject detail(Subject subject) {
		String sql = "SELECT * FROM " +
		" SUBJECT WHERE SID=? ";
		List<Subject> list = DBUnitHelper.executeQuery(sql, Subject.class, subject.getSid());
		return list.get(0);
	}//据给定的条件（Subject对象的SID属性），查询符合条件的科目记录
}








