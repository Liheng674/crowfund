package com.atguigu.atcrowdfunding.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.atcrowdfunding.common.bean.User;

/**
 * 登陆拦截器
 * @author 18801
 *
 */
public class LoginInterceptor extends LoginIntercetporAdapter {

	/**
	 * 1) 获取用户的登陆状态
	 * 2) 如果用户已经登陆，那么继续执行
	 * 3) 如果用户没有登陆，那么不能继续执行，跳转回到登陆页面
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// URI: /xxxx/xxxx
		// URL: http://127.0.0.1:8080/xxxx/xxxxx
		//String uri = request.getRequestURI();
		//System.out.println( "uri = " + uri );
		//request.getRequestURL();
		//return true;
		
		// 获取会话范围中保存的用户信息
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		if ( user == null ) {
			response.sendRedirect("/login");
			return false;
		} else {
			return true;
		}
	}

}
