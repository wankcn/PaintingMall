package com.imooc.web.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.imooc.domain.User;

@WebListener
public class InitServletContextListener implements ServletContextListener {


    public void contextDestroyed(ServletContextEvent sce)  { 
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("项目中数据初始化了....");
    	// 创建一个用于保存用户信息的List集合
    	List<User> userList = new ArrayList<User>();
    	// 将List集合存入到ServletContext域中
    	sce.getServletContext().setAttribute("userList", userList);
    }
	
}
