package com.atguigu.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 服务器启动监听器
 * @author 18801
 *
 */
public class ServerStartupListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		// 获取web应用对象
		ServletContext application = sce.getServletContext();
		
		// 获取web应用路径
		String path = application.getContextPath();
		
		// 将路径保存在application范围中
		application.setAttribute("APP_PATH", path);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
