package com.imooc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.imooc.dao.ProductDao;
import com.imooc.domain.Product;
import com.imooc.utils.JDBCUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> findAll() {
		System.out.println("ProductDao的findAll方法执行了...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = null;
		try{
			// 获得连接:
			conn = JDBCUtils.getConnection();
			// 编写SQL:
			String sql = "SELECT * FROM product p,category c WHERE p.cid=c.cid ORDER BY p.pid DESC;";
			// 预编译SQL:
			pstmt = conn.prepareStatement(sql);
			// 执行SQL:
			rs = pstmt.executeQuery();
			// 遍历结果集:
			list = new ArrayList<Product>();
			while(rs.next()){
				Product product = new Product();
				product.setPid(rs.getInt("pid"));
				product.setPname(rs.getString("pname"));
				product.setAuthor(rs.getString("author"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setFilename(rs.getString("filename"));
				product.setPath(rs.getString("path"));
				// 封装商品所属的分类:
				product.getCategory().setCid(rs.getInt("cid"));
				product.getCategory().setCname(rs.getString("cname"));
				product.getCategory().setCdesc(rs.getString("cdesc"));
				
				list.add(product);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public void save(Product product) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			// 获得连接:
			conn = JDBCUtils.getConnection();
			// 编写SQL:
			String sql = "insert into product values (null,?,?,?,?,?,?,?)";
			// 预编译SQL
			pstmt = conn.prepareStatement(sql);
			// 设置参数:
			pstmt.setString(1, product.getPname());
			pstmt.setString(2, product.getAuthor());
			pstmt.setDouble(3, product.getPrice());
			pstmt.setString(4, product.getDescription());
			pstmt.setString(5, product.getFilename());
			pstmt.setString(6, product.getPath());
			pstmt.setInt(7, product.getCategory().getCid());
			// 执行SQL：
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(pstmt, conn);
		}
	}

	@Override
	public Product findOne(Integer pid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			// 获得连接:
			conn = JDBCUtils.getConnection();
			// 编写SQL:
			String sql = "SELECT * FROM product p,category c WHERE p.cid=c.cid AND p.pid=?";
			// 预编译SQL:
			pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setInt(1, pid);
			// 执行SQL语句
			rs = pstmt.executeQuery();
			if(rs.next()){
				// 封装数据:
				Product product = new Product();
				product.setPid(rs.getInt("pid"));
				product.setPname(rs.getString("pname"));
				product.setAuthor(rs.getString("author"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setFilename(rs.getString("filename"));
				product.setPath(rs.getString("path"));
				product.getCategory().setCid(rs.getInt("cid"));
				product.getCategory().setCname(rs.getString("cname"));
				product.getCategory().setCdesc(rs.getString("cdesc"));
				return product;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(pstmt, conn);
		}
		return null;
	}

	@Override
	public void update(Product product) {
		Connection conn = null;
		PreparedStatement pstmt= null;
		try{
			// 获得连接:
			conn = JDBCUtils.getConnection();
			// 编写SQL:
			String sql = "update product set pname = ?,author=?,price=? ,description =?,filename=?,path=?,cid=? where pid=?";
			// 预编译SQL:
			pstmt = conn.prepareStatement(sql);
			// 设置参数:
			pstmt.setString(1, product.getPname());
			pstmt.setString(2, product.getAuthor());
			pstmt.setDouble(3, product.getPrice());
			pstmt.setString(4, product.getDescription());
			pstmt.setString(5, product.getFilename());
			pstmt.setString(6, product.getPath());
			pstmt.setObject(7, product.getCategory().getCid());
			pstmt.setInt(8, product.getPid());
			// 执行SQL:
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(pstmt, conn);
		}
	}

	@Override
	public void update(Connection conn,Product product) {
		PreparedStatement pstmt= null;
		try{
			// 编写SQL:
			String sql = "update product set pname = ?,author=?,price=? ,description =?,filename=?,path=?,cid=? where pid=?";
			// 预编译SQL:
			pstmt = conn.prepareStatement(sql);
			// 设置参数:
			pstmt.setString(1, product.getPname());
			pstmt.setString(2, product.getAuthor());
			pstmt.setDouble(3, product.getPrice());
			pstmt.setString(4, product.getDescription());
			pstmt.setString(5, product.getFilename());
			pstmt.setString(6, product.getPath());
			pstmt.setObject(7, product.getCategory().getCid());
			pstmt.setInt(8, product.getPid());
			// 执行SQL:
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void delete(Integer pid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = JDBCUtils.getConnection();
			String sql = "delete from product where pid = ?";
			pstmt = conn.prepareStatement(sql);
			// 设置参数:
			pstmt.setInt(1, pid);
			// 执行SQL:
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(pstmt, conn);
		}
	}

	@Override
	public List<Product> findByCid(Integer cid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = null;
		try{
			// 获得连接:
			conn = JDBCUtils.getConnection();
			// 编写SQL:
			String sql = "SELECT * FROM product p,category c WHERE p.cid=c.cid and p.cid = ? ORDER BY p.pid DESC;";
			// 预编译SQL:
			pstmt = conn.prepareStatement(sql);
			// 设置参数:
			pstmt.setInt(1, cid);
			// 执行SQL:
			rs = pstmt.executeQuery();
			// 遍历结果集:
			list = new ArrayList<Product>();
			while(rs.next()){
				Product product = new Product();
				product.setPid(rs.getInt("pid"));
				product.setPname(rs.getString("pname"));
				product.setAuthor(rs.getString("author"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setFilename(rs.getString("filename"));
				product.setPath(rs.getString("path"));
				// 封装商品所属的分类:
				product.getCategory().setCid(rs.getInt("cid"));
				product.getCategory().setCname(rs.getString("cname"));
				product.getCategory().setCdesc(rs.getString("cdesc"));
				
				list.add(product);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public int findCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Long count = 0l;
		try{
			// 获得连接:
			conn = JDBCUtils.getConnection();
			// 编写SQL:
			String sql = "select count(*) as count from product";
			// 预编译SQL:
			pstmt = conn.prepareStatement(sql);
			// 执行SQL:
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getLong("count");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(rs, pstmt, conn);
		}
		return count.intValue();
	}

	@Override
	public List<Product> findByPage(int begin, int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = null;
		try{
			// 获得连接:
			conn = JDBCUtils.getConnection();
			// 编写SQL语句:
			String sql = "select * from product limit ?,?";
			// 预编译SQL:
			pstmt = conn.prepareStatement(sql);
			// 设置参数:
			pstmt.setInt(1, begin);
			pstmt.setInt(2, limit);
			// 执行查询:
			rs = pstmt.executeQuery();
			list = new ArrayList<Product>();
			while(rs.next()){
				Product product = new Product();
				product.setPid(rs.getInt("pid"));
				product.setPname(rs.getString("pname"));
				product.setAuthor(rs.getString("author"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setFilename(rs.getString("filename"));
				product.setPath(rs.getString("path"));
				list.add(product);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils.release(rs, pstmt, conn);
		}
		return list;
	}


}
