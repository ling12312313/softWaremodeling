package cn.itbaizhan.tyut.exam.sys.services.impl;

import java.util.List;

import cn.itbaizhan.tyut.exam.common.PageControl;
import cn.itbaizhan.tyut.exam.common.Pager;
import cn.itbaizhan.tyut.exam.model.Studentpaper;
import cn.itbaizhan.tyut.exam.model.Subject;
import cn.itbaizhan.tyut.exam.sys.dao.impl.StudentpaperDao;
import cn.itbaizhan.tyut.exam.sys.dao.interfaces.IStudentpaperDao;
import cn.itbaizhan.tyut.exam.sys.services.interfaces.IStudentpaperService;

public class StudentpaperService implements IStudentpaperService {
	IStudentpaperDao dao = new StudentpaperDao();

	@Override
	public Integer addPaper(Studentpaper studentpaper) {
		// TODO Auto-generated method stub
		return dao.addPaper(studentpaper);
	}

	@Override
	public Pager<Subject> list(Studentpaper studentpaper,PageControl pc){
		// TODO Auto-generated method stub
		return dao.list(studentpaper, pc);
	}

	@Override
	public List<Studentpaper> listByRightcount(Studentpaper studentpaper) {
		// TODO Auto-generated method stub
		return dao.listByRightcount(studentpaper);
	}//据答题正确数量排序

	@Override
	public List<Studentpaper> StudentPaperList(Studentpaper studentpaper) {
		// TODO Auto-generated method stub
		return dao.StudentPaperList(studentpaper);
	}



}
//增加学生试卷、查询全部学生试卷列表、根据答题正确数量排序查询一个学生的试卷列表和查询一个学生的试卷列表等