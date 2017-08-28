package com.orilore.bizs;
import com.orilore.daos.*;
import com.orilore.entitys.*;
import java.util.*;
import java.sql.*;
public class ProductBiz implements IProductBiz{
	private IProductDAO dao = new ProductDAO();
	private DBUtil db = new DBUtil();
	private Connection conn = null;
	public int addProduct(Product product){
		try{
			conn = db.getConnection();
			return dao.insert(product,conn);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			this.close();
		}
		return 0;
	}
	public boolean removeProduct(int id){
		try{
			conn = db.getConnection();
			return dao.delete(id,conn);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}finally{
			this.close();
		}
	}
	public boolean modifyProduct(Product product) {;
		try{
			conn = db.getConnection();
			return dao.update(product,conn);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}finally{
			this.close();
		}
	}
	public Product getProduct(int id){
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
	public List<Product> findProduct(Map<String,String> cond){
		try{
			conn = db.getConnection();
			return dao.select(cond,conn);
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
	public List<List<String>> getInfo() {
		try{
			conn = db.getConnection();
			return dao.selectInfo(conn);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}finally{
			this.close();
		}
	}
	@Override
	public int getPages(Map<String, String> cond) {
		try{
			conn = db.getConnection();
			return dao.selectPages(cond, conn);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return 0;
		}finally{
			this.close();
		}
	}
}
