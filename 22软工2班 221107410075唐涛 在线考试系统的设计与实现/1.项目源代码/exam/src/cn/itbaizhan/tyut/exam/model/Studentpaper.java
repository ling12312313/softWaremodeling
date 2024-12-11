package cn.itbaizhan.tyut.exam.model;
//储存管理学生提交的试�?
public class Studentpaper {
	private String spid;//文章
	private Integer userid;//id
	private Integer sid;//储存id
	private String studentkey;//储存密钥
	private Integer studentstate;//状�?
	private String pname;//储存文章名称
	private Integer rightcount;//正确答案
	private Integer errorcount;//错误答案
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getStudentkey() {
		return studentkey;
	}
	public void setStudentkey(String studentkey) {
		this.studentkey = studentkey;
	}
	public Integer getStudentstate() {
		return studentstate;
	}
	public void setStudentstate(Integer studentstate) {
		this.studentstate = studentstate;
	}
	public void setRightcount(Integer rightcount) {
		this.rightcount = rightcount;
	}
	public Integer getRightcount() {
		return rightcount;
	}
	

	public Integer getErrorcount() {
		return errorcount;
	}
	public void setErrorcount(Integer errorcount) {
		this.errorcount = errorcount;
	}
	public String getSpid() {
		return spid;
	}
	public void setSpid(String spid) {
		this.spid = spid;
	}
	@Override
	public String toString() {
		return "Studentpaper [pname=" + pname + ", rightcount=" + rightcount
				+ ", sid=" + sid + ", spid=" + spid + ", studentkey="
				+ studentkey + ", studentstate=" + studentstate + ", userid="
				+ userid + "]";
	}//重写了toString()方法，用于返回一个包含所有成员变量值的字符串表示形式�?
}
