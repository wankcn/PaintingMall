package com.imooc.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.domain.User;
import com.imooc.service.UserService;
import com.imooc.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收参数:
		String methodName = request.getParameter("method");
		// 判断
		if("login".equals(methodName)){
			login(request,response);
		}else if("logout".equals(methodName)){
			logout(request,response);
		}
	}

	/**
	 * 用户退出的方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1.将session销毁掉
		request.getSession().invalidate();
		// 2.页面进行跳转,跳转到登录页面
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}


	/**
	 * UserServlet中的登录的方法
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收用户名和密码:
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username+"    "+password);
		// 数据的封装:
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		// 处理数据:
		UserService userService = new UserServiceImpl();
		User existUser = userService.login(user);
		// 根据处理结果,完成页面跳转
		if(existUser == null){
			// 登录失败
			// 返回到登录页面
			request.setAttribute("msg", "用户名或密码错误!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else{
			// 登录成功
			// 将用户的信息进行保存,进行页面跳转
			request.getSession().setAttribute("existUser", existUser);
			response.sendRedirect(request.getContextPath()+"/CategoryServlet?method=findAll");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
