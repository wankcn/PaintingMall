package com.imooc.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.domain.Category;
import com.imooc.domain.Product;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.service.impl.CategoryServiceImpl;
import com.imooc.service.impl.ProductServiceImpl;
import com.imooc.utils.UploadUtils;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收method的参数:
		String methodName = request.getParameter("method");
		if("findAll".equals(methodName)){
			findAll(request,response);
		}else if("saveUI".equals(methodName)){
			saveUI(request,response);
		}else if("save".equals(methodName)){
			save(request,response);
		}else if("edit".equals(methodName)){
			edit(request,response);
		}else if("update".equals(methodName)){
			update(request,response);
		}else if("delete".equals(methodName)){
			delete(request,response);
		}
	}
	
	/**
	 * 后台管理商品模块，删除商品的方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 接收商品ID:
		Integer pid = Integer.parseInt(request.getParameter("pid"));
		// 调用业务层处理数据:
		ProductService productService = new ProductServiceImpl();
		// 查询商品信息：
		Product product = productService.findOne(pid);
		String path = product.getPath();
		if(path != null && !"".equals(path)){
			String realPath = this.getServletContext().getRealPath(path);
			System.out.println(realPath);
			File file = new File(realPath);
			if(file.exists()){
				file.delete();
			}
		}
		productService.delete(pid);
		// 页面跳转：
		response.sendRedirect(request.getContextPath()+"/ProductServlet?method=findAll");
	}

	/**
	 * 后台商品管理，修改商品的方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 接收数据
		Map<String,String> map = UploadUtils.uploadFile(request);
		// 封装数据
		Product product = new Product();
		product.setPid(Integer.parseInt(map.get("pid")));
		product.setPname(map.get("pname"));
		product.setAuthor(map.get("author"));
		product.setPrice(Double.parseDouble(map.get("price")));
		product.setDescription(map.get("description"));
		product.setFilename(map.get("filename"));
		product.setPath(map.get("path"));
		product.getCategory().setCid(Integer.parseInt(map.get("cid")));
		// 处理数据
		ProductService productService = new ProductServiceImpl();
		productService.update(product);
		//  页面跳转
		response.sendRedirect(request.getContextPath()+"/ProductServlet?method=findAll");
	}

	/**
	 * 后台商品管理，修改商品的方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收数据:
		Integer pid = Integer.parseInt(request.getParameter("pid"));
		// 调用业务层处理数据:
		ProductService  productService = new ProductServiceImpl();
		Product product = productService.findOne(pid);
		// System.out.println(product);
		// 查询所有分类:
		CategoryService categoryService = new CategoryServiceImpl();
		List<Category> categoryList = categoryService.findAll();
		// 页面跳转
		request.setAttribute("product", product);
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("/admin/product_update.jsp").forward(request, response);
	}

	/**
	 * 后台商品管理，保存商品的方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("save=============");
		// 文件上传
		Map<String,String> map = UploadUtils.uploadFile(request);
		// 将数据完成封装
		Product product = new Product();
		product.setPname(map.get("pname"));
		product.setAuthor(map.get("author"));
		product.setPrice(Double.parseDouble(map.get("price")));
		product.setDescription(map.get("description"));
		product.setFilename(map.get("filename"));
		product.setPath(map.get("path"));
		product.getCategory().setCid(Integer.parseInt(map.get("cid")));
		// 处理数据
		ProductService productService = new ProductServiceImpl();
		productService.save(product);
		// 页面跳转：
		response.sendRedirect(request.getContextPath()+"/ProductServlet?method=findAll");
	}

	/**
	 * 商品模块，跳转到添加页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void saveUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 查询所有分类的信息:
		CategoryService categoryService = new CategoryServiceImpl();
		List<Category> list = categoryService.findAll();
		// 页面跳转
		request.setAttribute("categoryList", list);
		request.getRequestDispatcher("/admin/product_add.jsp").forward(request, response);
	}

	/**
	 * 商品模块，查询所有商品的方法:
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 不接收参数
		System.out.println("ProductServlet的findAll方法执行了...");
		// 调用业务层处理数据:
		ProductService productService = new ProductServiceImpl();
		List<Product> list = productService.findAll();
		/*for (Product product : list) {
			System.out.println(product);
		}*/
		// 页面跳转：
		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/product_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
