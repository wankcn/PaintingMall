package com.imooc.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.domain.Category;
import com.imooc.service.CategoryService;
import com.imooc.service.impl.CategoryServiceImpl;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//		请求路径 :localhost:8080/shop/CategoryServlet?method=findAll
		String methodName = request.getParameter("method");
		if("findAll".equals(methodName)){
			// 查询所有分类:
			findAll(request,response);
		}else if("saveUI".equals(methodName)){
			// 跳转到添加页面
			saveUI(request,response);
		}else if("save".equals(methodName)){
			// 保存分类的方法
			save(request,response);
		}else if("edit".equals(methodName)){
			// 编辑分类的方法
			edit(request,response);
		}else if("update".equals(methodName)){
			// 修改分类的方法
			update(request,response);
		}else if("delete".equals(methodName)){
			// 删除分类的方法:
			delete(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	/**
	 * 后台分类管理删除分类的方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 接收数据:
		Integer cid = Integer.parseInt(request.getParameter("cid"));
		// 处理数据:
		CategoryService categoryService = new CategoryServiceImpl();
		categoryService.delete(cid);
		// 页面跳转
		response.sendRedirect(request.getContextPath()+"/CategoryServlet?method=findAll");
	}
	/**
	 * 后台分类管理修改分类的方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1.接收数据:
		Integer cid = Integer.parseInt(request.getParameter("cid"));
		String cname = request.getParameter("cname");
		String cdesc = request.getParameter("cdesc");
		// 2.封装数据:
		Category category = new Category();
		category.setCid(cid);
		category.setCname(cname);
		category.setCdesc(cdesc);
		// 调用业务层处理数据:
		CategoryService categoryService = new CategoryServiceImpl();
		categoryService.update(category);
		// 页面跳转
		response.sendRedirect(request.getContextPath()+"/CategoryServlet?method=findAll");
	}
	/**
	 * 后台分类管理编辑分类的方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.接收数据
		Integer cid = Integer.parseInt(request.getParameter("cid"));
		// 2.调用业务层处理数据
		CategoryService categoryService = new CategoryServiceImpl();
		Category category = categoryService.findOne(cid);
		// 3.页面跳转
		request.setAttribute("category", category);
		request.getRequestDispatcher("/admin/category_update.jsp").forward(request, response);
	}
	/**
	 * 后台分类管理保存分类的方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1.接收数据
		String cname = request.getParameter("cname");
		String cdesc = request.getParameter("cdesc");
		System.out.println("接收的数据是："+cname+"    "+cdesc);
		// 2.封装数据
		Category category = new Category();
		category.setCname(cname);
		category.setCdesc(cdesc);
		// 3.调用业务层处理数据
		CategoryService categoryService = new CategoryServiceImpl();
		categoryService.save(category);
		// 4.页面跳转
		response.sendRedirect(request.getContextPath()+"/CategoryServlet?method=findAll");
	}
	
	/**
	 * 后台分类管理跳转到添加页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void saveUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/admin/category_add.jsp").forward(request, response);
	}
	/**
	 * 后台分类管理查询所有分类的方法:
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收参数
		// 封装数据
		// 调用业务层处理数据
		System.out.println("CategoryServlet的findAll方法执行了...");
		CategoryService categoryService = new CategoryServiceImpl();
		List<Category> list = categoryService.findAll();
		
		// 页面跳转
		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/category_list.jsp").forward(request, response);
	}
}
