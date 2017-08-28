package com.orilore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orilore.bizs.ISizeBiz;
import com.orilore.bizs.SizeBiz;
import com.orilore.entitys.Size;

public class SizeCtrl extends HttpServlet {
	private ISizeBiz biz = new SizeBiz();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String pid = request.getParameter("pid");
		if(pid!=null && !"".equals(pid)){
			int id = Integer.parseInt(pid);
			List<Size> sizes = biz.findSize(id);
			if(sizes.size()>0){
				StringBuffer buffer = new StringBuffer("[");
				for (Size s : sizes) {
					buffer.append("{");
					buffer.append("\"id\":\""+s.getId()+"\",\"pid\":\""+s.getPid()+"\",\"sname\":\""+s.getSname()+"\"");
					buffer.append("},");
				}
				buffer.append("]");
				buffer.deleteCharAt(buffer.length()-2);
				String json = buffer.toString();
				out.print(json);
			}
			out.print("");
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String sz = request.getParameter("sz");
		sz = sz.substring(1,sz.length()-1);
		sz = sz.replace("{","").replace("}","");
		String[] sizes = sz.split(",");
		List<Size> list = new ArrayList<Size>();
		Size bean = null;
		for (int i=0;i<sizes.length;i++) {
			String str = sizes[i];
			String[] attrs = str.split(":");
			String v = attrs[1];
			v = v.replace("\"","");
			
			if(i%3==0){
				bean = new Size();
				if(v!=null && !v.equals("")){
					bean.setId(Integer.parseInt(v));
				}
			}else if(i%3==1){
				bean.setPid(Integer.parseInt(v));
			}else if(i%3==2){
				v = new String(v.getBytes("ISO8859-1"),"utf-8");
				bean.setSname(v);
				list.add(bean);
			}
		}
		
		boolean flog = biz.process(list);
		out.print(flog);
		out.flush();
		out.close();
	}

}
