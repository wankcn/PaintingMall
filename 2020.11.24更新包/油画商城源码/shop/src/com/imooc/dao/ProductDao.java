package com.imooc.dao;

import java.sql.Connection;
import java.util.List;

import com.imooc.domain.Product;

public interface ProductDao {

	List<Product> findAll();

	void save(Product product);

	Product findOne(Integer pid);

	void update(Product product);

	void delete(Integer pid);

	List<Product> findByCid(Integer cid);

	void update(Connection conn, Product product);

	int findCount();

	List<Product> findByPage(int begin, int limit);

}
