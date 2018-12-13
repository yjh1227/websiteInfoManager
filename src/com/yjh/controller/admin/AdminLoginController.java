package com.yjh.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yjh.model.Admin;
import com.yjh.services.AdminServices;
import com.yjh.services.AdminServicesImpi;
import com.yjh.util.CookieSessionParam;
import com.yjh.util.FormParam;

/**
 * 管理员登录Controller
 * 
 * @author yjh
 *
 */
public class AdminLoginController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		
		String id = req.getParameter(FormParam.AdminLogin_id);
		String pass = req.getParameter(FormParam.AdminLogin_pass);
		
		if("".equals(id) || "".equals(pass)) {
			req.setAttribute("mess", "用户名、密码不能为空");
			req.getRequestDispatcher("/admin/login").forward(req, resp);
			return ;
		}
		
		AdminServices services = new AdminServicesImpi();
		String result = services.allowAdminLogin(id, pass);
		if(result == null) {
			HttpSession session = req.getSession(true);
			session.setAttribute(CookieSessionParam.Session_role, CookieSessionParam.Session_role_valueAdmin);
			Admin admin = services.findAdmin(id);
			session.setAttribute(CookieSessionParam.Session_self, admin);
			req.getRequestDispatcher("/admin/home").forward(req, resp);
			return ;
		} else {
			req.setAttribute("mess", "用户名或密码错误");
			req.getRequestDispatcher("/admin/login").forward(req, resp);
			return ;
		}
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
