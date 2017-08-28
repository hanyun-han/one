package com.orilore.daos;
import com.orilore.entitys.Store;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class StoreDAO implements IStoreDAO{
	private PreparedStatement pstmt;
	private ResultSet rs;
	public boolean insert(Store store,Connection conn) throws Exception{
		String sql="insert into store(pid,sid,quantity) values(?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,store.getPid());		pstmt.setInt(2,store.getSid());		pstmt.setInt(3,store.getQuantity());		if(pstmt.executeUpdate()>0){
			this.close();
			return true;
		}else{
			this.close();
			return false;
		}
	}
	public boolean update(Store store,Connection conn) throws Exception{;
		String sql="update store set quantity=? where pid=? and sid=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,store.getQuantity());
		pstmt.setInt(2,store.getPid());
		pstmt.setInt(3,store.getSid());		if(pstmt.executeUpdate()>0){
			this.close();
			return true;
		}else{
			this.close();
			return false;
		}
	}
	public void close() throws Exception{
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
	}
	@Override
	public Store select(int pid, int sid, Connection conn) throws Exception {
		String sql="select * from store where pid=? and sid=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,pid);
		pstmt.setInt(2,sid);
		rs = pstmt.executeQuery();
		Store bean = null;
		if(rs.next()){
			bean = new Store();
			bean.setPid(pid);
			bean.setSid(sid);
			bean.setQuantity(rs.getInt("quantity"));
		}
		this.close();
		return bean;
	}
	@Override
	public List<Map<String, Object>> state(Connection conn) throws Exception {
		String sql="select distinct a.name,sum(b.quantity) total ";
		sql+=" from product a left join store b on a.id=b.pid ";
		sql+=" group by a.name having sum(b.quantity)>0 ";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		List<Map<String, Object>> stores = new ArrayList<Map<String,Object>>();
		while(rs.next()){
			Map<String, Object> row = new HashMap<String,Object>();
			row.put("name", rs.getString("name"));
			row.put("total", rs.getInt("total"));
			stores.add(row);
		}
		this.close();
		return stores;
	}
}