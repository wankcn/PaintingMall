package com.imooc.dao;

import java.sql.Connection;
import java.util.List;

import com.imooc.domain.Category;

public interface CategoryDao {

	List<Category> findAll();

	void save(Category category);

	Category findOne(Integer cid);

	void update(Category category);

	void delete(Integer cid);

	void delete(Connection conn, Integer cid);

}
