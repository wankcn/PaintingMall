package com.imooc.domain;

public class Category {
	private Integer cid;
	private String cname;
	private String cdesc;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCdesc() {
		return cdesc;
	}
	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + ", cdesc=" + cdesc + "]";
	}
	
	
}
