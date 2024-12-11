package cn.itbaizhan.tyut.exam.model;
//定义了一个名为scount的私有整型变量，可以在当前类中进行使用和操作�?
public class Paper {
	private String pname;
	private Integer sid;
	private Integer scount;
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
	public Integer getScount() {
		return scount;
	}
	public void setScount(Integer scount) {
		this.scount = scount;
	}
}
