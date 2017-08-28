package com.orilore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orilore.bizs.IOpstoreBiz;
import com.orilore.bizs.OpstoreBiz;
import com.orilore.entitys.Opstore;

public class OpstoreQueryCtrl extends HttpServlet {
	private IOpstoreBiz biz = new OpstoreBiz();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String g = request.getParameter("g");
		if(g!=null && !"".equals(g)){
			List<Opstore> opstores = biz.findOpstore(Integer.parseInt(g));
			request.setAttribute("opstores", opstores);
			RequestDispatcher rd = null;
			if("1".equals(g)){
				rd = request.getRequestDispatcher("instores.jsp");
			}else{
				rd = request.getRequestDispatcher("outstores.jsp");
			}
			rd.forward(request, response);
		}else{
			String pid = request.getParameter("pid");
			String sid = request.getParameter("sid");
			if(pid!=null && sid!=null && !"".equals(pid) && !"".equals(sid)){
				int q = biz.getQuantity(Integer.parseInt(pid), Integer.parseInt(sid));
				response.setContentType("application/json;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(q);
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
