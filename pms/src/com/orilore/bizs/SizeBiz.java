package com.orilore.bizs;
import com.orilore.daos.*;
import com.orilore.entitys.*;

import java.util.*;
import java.sql.*;
public class SizeBiz implements ISizeBiz{
	private ISizeDAO dao = new SizeDAO();
	private DBUtil db = new DBUtil();
	private Connection conn = null;
	public boolean addSize(Size size){
		try{
			conn = db.getConnection();
			if(dao.insert(size,conn)){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}finally{
			this.close();
		}
	}
	public boolean removeSize(int id){
		try{
			conn = db.getConnection();
			if(dao.delete(id,conn)){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}finally{
			this.close();
		}
	}
	public boolean modifySize(Size size) {;
		try{
			conn = db.getConnection();
			if(dao.update(size,conn)){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}finally{
			this.close();
		}
	}
	public Size getSize(int id){
		try{
			conn = db.getConnection();
			return dao.selectOne(id,conn);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
				return null;
		}finally{
			this.close();
		}
	}
	public List<Size> findSize(int pid){
		try{
			conn = db.getConnection();
			return dao.select(pid,conn);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}finally{
			this.close();
		}
	}
	public void close(){
		try{
			if(conn!=null && !conn.isClosed()) conn.close();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	@Override
	public boolean process(List<Size> sizes) {
		try{
			int pid = sizes.get(0).getPid();
			List<Size> ysizes = this.findSize(pid);
			//实现删除型号的功能
			for(Size s : ysizes){
				boolean flog = true;
				for(Size s1 : sizes){
					if(s1.getId()==s.getId()){
						flog = false;
						break;
					}
				}
				if(flog){
					this.removeSize(s.getId());
				}
			}
			//实现添加或修改的功能
			for(int i=0;i<sizes.size();i++){
			 	Size size = sizes.get(i);
			 	if(size.getId()==null || size.getId()<=0){
			 		this.addSize(size);
			 	}else{
			 		this.modifySize(size);
			 	}
			}
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
}
