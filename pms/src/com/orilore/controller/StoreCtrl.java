package com.orilore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;

import com.orilore.bizs.IStoreBiz;
import com.orilore.bizs.StoreBiz;

public class StoreCtrl extends HttpServlet {
	private IStoreBiz biz = new StoreBiz();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		List<Map<String,Object>> source = biz.storeState();
		JSONArray array = new JSONArray();
		try{
			for (Map<String, Object> row : source) {
				JSONObject obj = new JSONObject();
				obj.put("value", row.get("total"));
				obj.put("name", row.get("name"));
				array.add(obj);
			}
		}catch(Exception ex){
			out.print(ex.getMessage());
		}
		out.print(array);
		out.flush();
		out.close();
	}

}
