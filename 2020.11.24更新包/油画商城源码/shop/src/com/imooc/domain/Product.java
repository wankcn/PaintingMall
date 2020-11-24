package com.imooc.domain;

public class Product {
	private Integer pid;
	private String pname;
	private String author;
	private Double price;
	private String description;
	private String filename;
	private String path;
	// 表中分类的ID，面向对象的语言，如何表示商品所属的分类呢？
	private Category category = new Category();
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", author=" + author + ", price=" + price + ", description="
				+ description + ", filename=" + filename + ", path=" + path + ", category=" + category + "]";
	}
	
}
