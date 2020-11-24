package com.imooc.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.domain.Category;
import com.imooc.domain.PageBean;
import com.imooc.domain.Product;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.service.impl.CategoryServiceImpl;
import com.imooc.service.impl.ProductServiceImpl;


@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 0;
		String currPage = request.getParameter("page");
		if(currPage == null){
			page = 1;
		}else{
			page = Integer.parseInt(currPage);
		}
		// 查询操作
		// 查询所有分类:
		CategoryService categoryService = new CategoryServiceImpl();
		List<Category> categoryList = categoryService.findAll();
		// 分页查询商品的数据:
		ProductService productService = new ProductServiceImpl();	
		PageBean<Product> pageBean = productService.findByPage(page);
		System.out.println(pageBean);
		// 页面跳转
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
