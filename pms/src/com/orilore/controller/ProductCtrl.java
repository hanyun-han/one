package com.orilore.controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orilore.bizs.IProductBiz;
import com.orilore.bizs.ISizeBiz;
import com.orilore.bizs.ProductBiz;
import com.orilore.bizs.SizeBiz;
import com.orilore.entitys.Product;
import com.orilore.entitys.Size;

public class ProductCtrl extends HttpServlet {
	private IProductBiz biz = new ProductBiz();
	private ISizeBiz sbiz  = new SizeBiz();
	//删除商品，查询（1，m）
	//http://localhost:8080/pms/product.do
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String k   = request.getParameter("k");
		RequestDispatcher rd = null;
		if(id==null || id.equals("")){
			
			String page = request.getParameter("page");
			String count = request.getParameter("count");
			String name = request.getParameter("name");
			String kind = request.getParameter("kind");
			String factory = request.getParameter("factory");
			String price1 = request.getParameter("price1");
			String price2 = request.getParameter("price2");
			
			Map<String,String> cond = new HashMap<String,String>();
			cond.put("page", page);
			cond.put("count", count);
			
			if(name!=null){
				name = new String(name.getBytes("ISO8859-1"),"utf-8");
			}
			cond.put("name", name);
			if(kind!=null){
				kind = new String(kind.getBytes("ISO8859-1"),"utf-8");
			}
			cond.put("kind", kind);
			if(factory!=null){
				factory = new String(factory.getBytes("ISO8859-1"),"utf-8");
			}
			cond.put("factory", factory);
			cond.put("price1", price1);
			cond.put("price2", price2);
			Integer pages = biz.getPages(cond);
			cond.put("pages", pages.toString());
			
			List<Product> products = biz.findProduct(cond);
			request.setAttribute("cond", cond);
			request.setAttribute("kinds",biz.getInfo().get(0));
			request.setAttribute("factorys",biz.getInfo().get(1));
			request.setAttribute("products", products);
			rd = request.getRequestDispatcher("products.jsp");
			rd.forward(request, response);
		}else{
			if("r".equals(k)){
				biz.removeProduct(Integer.parseInt(id));
				response.sendRedirect("product.do");
			}else{
				Product product = biz.getProduct(Integer.parseInt(id));
				request.setAttribute("product", product);
				rd = request.getRequestDispatcher("product.jsp");
				rd.forward(request, response);
			}
		}
	}
	//添加商品，修改商品
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Product bean = new Product();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String factory = request.getParameter("factory");
		String term = request.getParameter("trem");
		String info = request.getParameter("info");
		String kind = request.getParameter("kind");
		int pid = 0;
		if(id!=null && !id.equals("")){
			pid = Integer.parseInt(id);
			bean.setId(pid);
		}
		bean.setName(name);
		bean.setFactory(factory);
		bean.setKind(kind);
		bean.setTrem(term);
		bean.setInfo(info);
		if(price!=null && !price.equals("")){
			bean.setPrice(Float.parseFloat(price));
		}
		
		if(pid>0){
			biz.modifyProduct(bean);
		}else{
			int psid = biz.addProduct(bean);
			String[] snames = request.getParameterValues("sname");
			if(snames!=null && snames.length>0){
				for (String s : snames) {
					Size sb = new Size();
					sb.setPid(psid);
					sb.setSname(s);
					sbiz.addSize(sb);
				}
			}
		}
		response.sendRedirect("product.do");
	}
}
