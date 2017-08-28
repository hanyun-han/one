package com.orilore.bizs;
import com.orilore.daos.*;
import com.orilore.entitys.*;

import java.util.*;
import java.sql.*;
public class StoreBiz implements IStoreBiz{
	private IStoreDAO dao = new StoreDAO();
	private DBUtil db = new DBUtil();
	private Connection conn = null;
	public boolean addStore(Store store){
		try{
			conn = db.getConnection();
			if(dao.insert(store,conn)){
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
	
	public void close(){
		try{
			if(conn!=null && !conn.isClosed()) conn.close();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public List<Map<String, Object>> storeState() {
		try{
			conn = db.getConnection();
			return this.dao.state(conn);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}finally{
			this.close();
		}
	}
}
