package com.imooc.web.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.imooc.domain.User;
import com.imooc.service.UserService;
import com.imooc.service.impl.UserServiceImpl;
import com.imooc.utils.UploadUtils;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收数据
		// 创建Map集合用于保存数据:
		Map<String,String> map = new HashMap<String,String>();
		// 文件上传的代码:
		// 1.创建磁盘文件项工厂
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 2.创建核心解析类
		ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
		// 3.解析请求对象,将请求分成几个部分(FileItem)
		try {
			List<FileItem> list = fileUpload.parseRequest(request);
			// 4.遍历集合获得每个部分的对象
			for (FileItem fileItem : list) {
				// 判断是普通项还是文件上传项
				if (fileItem.isFormField()) {
					// 普通项--用户名(username--输入的值) 密码 确认密码
					// 获得普通项的名称:
					String name = fileItem.getFieldName();
					// 获得普通项的值:
					String value = fileItem.getString("UTF-8");
					// 保存数据:
					map.put(name, value);
				} else {
					// 文件上传项
					// 获得文件的名称:
					String fileName = fileItem.getName();
					// 获得唯一文件名:
					String uuidFileName = UploadUtils.getUuidFileName(fileName);
					// 获得文件的输入流:
					InputStream is = fileItem.getInputStream();
					// 需要将文件写入到服务器的某个路径即可:
					String path = getServletContext().getRealPath("/upload");
					System.out.println(path);// D:/xxx/ddd/
					// 显示图片<img src="/regist_login/upload/a.jpg">
					// 创建输出流 与 输入流进行对接:
					String url = path + "\\" + uuidFileName;
					map.put("path", request.getContextPath()+"/upload/"+uuidFileName);
					OutputStream os = new FileOutputStream(url);
					int len = 0;
					byte[] b = new byte[1024];
					while ((len = is.read(b)) != -1) {
						os.write(b, 0, len);
					}
					is.close();
					os.close();
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 封装数据
		User user = new User();
		user.setUsername(map.get("username"));
		user.setPassword(map.get("password"));
		
		user.setPath(map.get("path"));
		System.out.println(user);
		// 处理数据
		UserService userService = new UserServiceImpl();
		// 从ServletContext域中获取用户的集合即可
		List<User> userList = (List<User>) getServletContext().getAttribute("userList");
		userService.regist(userList, user);
		System.out.println(userList);
		// 显示处理结果
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
