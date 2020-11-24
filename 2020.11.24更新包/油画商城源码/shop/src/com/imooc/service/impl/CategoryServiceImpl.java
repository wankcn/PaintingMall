package com.imooc.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.imooc.dao.CategoryDao;
import com.imooc.dao.ProductDao;
import com.imooc.dao.impl.CategoryDaoImpl;
import com.imooc.dao.impl.ProductDaoImpl;
import com.imooc.domain.Category;
import com.imooc.domain.Product;
import com.imooc.service.CategoryService;
import com.imooc.utils.JDBCUtils;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public List<Category> findAll() {
		// 调用CategoryDao的方法
		System.out.println("CategoryService的findAll方法执行了...");
		CategoryDao categoryDao = new CategoryDaoImpl();
		return categoryDao.findAll();
	}

	@Override
	public void save(Category category) {
		CategoryDao categoryDao = new CategoryDaoImpl();
		categoryDao.save(category);
	}

	@Override
	public Category findOne(Integer cid) {
		CategoryDao categoryDao = new CategoryDaoImpl();
		return categoryDao.findOne(cid);
	}

	@Override
	public void update(Category category) {
		CategoryDao categoryDao = new CategoryDaoImpl();
		categoryDao.update(category);
	}

	@Override
	public void delete(Integer cid) {
		/**
		 * 事务管理：在业务层统一创建连接对象，保证多个DAO中使用同一个连接：
		 * 	* 1.创建连接之后，将连接对象传递给DAO
		 *  * 2.创建一个连接对象，将连接绑定到当前线程中.(ThreadLocal)
		 */
		Connection conn = null;
		try{
			conn = JDBCUtils.getConnection();
			// 开启事务:
			conn.setAutoCommit(false);
			// 要在删除分类之前，先将所属该分类的商品先处理一下.
			ProductDao productDao = new ProductDaoImpl();
			List<Product> list = productDao.findByCid(cid);
			for (Product product : list) {
				product.getCategory().setCid(null);
				productDao.update(conn,product);
			}
			
//			int d = 1 / 0;
			
			// 删除分类:
			CategoryDao categoryDao = new CategoryDaoImpl();
			categoryDao.delete(conn,cid);
			// 提交事务:
			conn.commit();
		}catch(Exception e){
			// 回滚事务:
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
