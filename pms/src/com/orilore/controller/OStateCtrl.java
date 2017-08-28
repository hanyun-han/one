package com.orilore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;

import com.orilore.bizs.IOpstoreBiz;
import com.orilore.bizs.OpstoreBiz;

public class OStateCtrl extends HttpServlet {
	private IOpstoreBiz biz = new OpstoreBiz();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String year = request.getParameter("year");
		if(year==null || "".equals(year)){
			Calendar cal = Calendar.getInstance();
			Integer y = cal.get(Calendar.YEAR);
			year = y.toString();
		}
		List<Map<String,Object>> rows = biz.outstate(year);
		JSONArray array = new JSONArray();
		try {
			for (Map<String, Object> row : rows) {
				JSONObject obj = new JSONObject();
				obj.put("name", row.get("name"));
				obj.put("month", row.get("month"));
				obj.put("total", row.get("total"));
				array.add(obj);
			}
		} catch (Exception e) {
			out.print(e.getMessage());
		}
		out.print(array);
		out.flush();
		out.close();
	}

}
