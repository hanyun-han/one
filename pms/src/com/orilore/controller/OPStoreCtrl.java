package com.orilore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orilore.bizs.IOpstoreBiz;
import com.orilore.bizs.IProductBiz;
import com.orilore.bizs.ISizeBiz;
import com.orilore.bizs.OpstoreBiz;
import com.orilore.bizs.ProductBiz;
import com.orilore.bizs.SizeBiz;
import com.orilore.entitys.Opstore;
import com.orilore.entitys.Product;
import com.orilore.entitys.Size;

public class OPStoreCtrl extends HttpServlet {
	private IProductBiz pbiz = new ProductBiz();
	private ISizeBiz sbiz = new SizeBiz();
	private IOpstoreBiz obiz = new OpstoreBiz();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String pid = request.getParameter("pid");
		if(pid==null || "".equals(pid)){	
			List<Product> ps = pbiz.findProduct(null);
			StringBuffer buffer = new StringBuffer("[");
			for (Product p : ps) {
				buffer.append("{");
				buffer.append("\"id\":\""+p.getId()+"\",\"name\":\""+p.getName()+"\"");
				buffer.append("},");
			}
			buffer.append("]");
			buffer.deleteCharAt(buffer.length()-2);
			String json = buffer.toString();
			out.print(json);
		}else{
			List<Size> sizes = sbiz.findSize(Integer.parseInt(pid));
			StringBuffer buffer = new StringBuffer("[");
			for (Size s : sizes) {
				buffer.append("{");
				buffer.append("\"id\":\""+s.getId()+"\",\"sname\":\""+s.getSname()+"\"");
				buffer.append("},");
			}
			buffer.append("]");
			buffer.deleteCharAt(buffer.length()-2);
			String json = buffer.toString();
			out.print(json);
		}	
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String pid = request.getParameter("pid");
		String sid = request.getParameter("sid");
		String count = request.getParameter("quantity");
		String person = request.getParameter("person");
		String grade = request.getParameter("grade");
		Opstore bean = new Opstore();
		bean.setPid(Integer.parseInt(pid));
		bean.setSid(Integer.parseInt(sid));
		bean.setQuantity(Integer.parseInt(count));
		bean.setGread(Integer.parseInt(grade));
		bean.setPerson(person);
		if(obiz.addOpstore(bean)){
			response.sendRedirect("queryopstore.do?g="+grade);
		}else{
			response.sendRedirect("instore.jsp?error=y");
		}
	}

}
