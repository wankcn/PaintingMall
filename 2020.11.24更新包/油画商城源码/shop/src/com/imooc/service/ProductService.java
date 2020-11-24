package com.imooc.service;

import java.util.List;

import com.imooc.domain.PageBean;
import com.imooc.domain.Product;

public interface ProductService {

	List<Product> findAll();

	void save(Product product);

	Product findOne(Integer pid);

	void update(Product product);

	void delete(Integer pid);
	
	PageBean<Product> findByPage(int page);

}
