package com.imooc.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 文件上传的工具类
 * 
 */
public class UploadUtils {

	/**
	 * 生成唯一的文件名:
	 */
	public static String getUUIDFileName(String fileName){
		// 将文件名的前面部分进行截取：xx.jpg   --->   .jpg
		int idx = fileName.lastIndexOf(".");
		String extention = fileName.substring(idx);
		String uuidFileName = UUID.randomUUID().toString().replace("-", "")+extention;
		return uuidFileName;
	}
	
	public static Map uploadFile(HttpServletRequest request) throws IOException {
		Map<String,String> map = new HashMap<String,String>();
		// 1.创建一个磁盘文件项工厂对象
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 2.创建一个核心解析类
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		// 3.解析request请求，返回的是List集合，List集合中存放的是FileItem对象
		String url = null;
		String uuidFileName= null;
		try {
			List<FileItem> list = servletFileUpload.parseRequest(request);
			for (FileItem fileItem : list) {
				if(fileItem.isFormField()){
					// 不是文件上传
					// 接收表单项参数的值:
					String name = fileItem.getFieldName(); // 获得表单项的name属性的值
					String value = fileItem.getString("UTF-8");// 获得表单项的值
					// 存入集合
					map.put(name, value);
				}else{
					// 文件上传
					// 文件上传项:
					// 文件上传功能：
					// 获得文件上传的名称：
					String fileName = fileItem.getName();
					System.out.println("filename="+fileName);
					if(fileName !=null && !"".equals(fileName)){
						// 通过工具类获得唯一文件名:
						uuidFileName = UploadUtils.getUUIDFileName(fileName);
						// 获得文件上传的数据：
						InputStream is = fileItem.getInputStream();
						// 获得文件上传的路径:
						String path = request.getServletContext().getRealPath("/upload");
						// 将输入流对接到输出流就可以了:
						url = path+"\\"+uuidFileName;
						OutputStream os = new FileOutputStream(url);
						int len = 0;
						byte[] b = new byte[1024];
						while((len = is.read(b))!=-1){
							os.write(b, 0, len);
						}
						is.close();
						os.close();
						
						map.put("path","/upload/"+uuidFileName);
						map.put("filename", fileName);
					}
					System.out.println(map);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	public static void main(String[] args) {
		System.out.println(getUUIDFileName("1.jpg"));
	}
}
