package com.orilore.bizs;
import com.orilore.daos.*;
import com.orilore.entitys.*;
import java.util.*;
import java.sql.*;
public class OpstoreBiz implements IOpstoreBiz{
	private IOpstoreDAO dao = new OpstoreDAO();
	private IStoreDAO sdao = new StoreDAO();
	private DBUtil db = new DBUtil();
	private Connection conn = null;
	public boolean addOpstore(Opstore bean){
		boolean flog = true;
		try{
			conn = db.getConnection();
			conn.setAutoCommit(false);
			dao.insert(bean, conn);
			Store store = sdao.select(bean.getPid(), bean.getSid(), conn);
			if(store!=null){
				if(bean.getGread()==1){
					store.setQuantity(store.getQuantity()+bean.getQuantity());
					sdao.update(store, conn);
					conn.commit();
				}else{
					if(store.getQuantity()>=bean.getQuantity()){
						store.setQuantity(store.getQuantity()-bean.getQuantity());
						sdao.update(store, conn);
						conn.commit();
					}else{
						conn.rollback();
						flog = false;
					}
				}
			}else{
				if(bean.getGread()==1){
					store = new Store();
					store.setPid(bean.getPid());
					store.setSid(bean.getSid());
					store.setQuantity(bean.getQuantity());
					sdao.insert(store, conn);
					conn.commit();
				}else{
					conn.rollback();
					flog = false;
				}
			}
		}catch(Exception ex){
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(ex.getMessage());
		}finally{
			this.close();
		}
		return flog;
	}
	public boolean removeOpstore(int id){
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
	public boolean modifyOpstore(Opstore opstore) {;
		try{
			conn = db.getConnection();
			if(dao.update(opstore,conn)){
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
	public Opstore getOpstore(int id){
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
	public List<Opstore> findOpstore(int g){
		try{
			conn = db.getConnection();
			return dao.select(g,conn);
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
	public int getQuantity(int pid, int sid) {
		try{
			conn = db.getConnection();
			Store bean = sdao.select(pid,sid,conn);
			if(bean!=null){
				return bean.getQuantity();
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return 0;
		}finally{
			this.close();
		}
		return 0;
	}
	@Override
	public List<Map<String, Object>> outstate(String year) {
		try{
			conn = db.getConnection();
			return this.dao.outstate(year, conn);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}finally{
			this.close();
		}
	}
}
