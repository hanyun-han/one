package com.orilore.daos;
import com.orilore.entitys.Size;
import java.sql.*;
import java.util.*;
public class SizeDAO implements ISizeDAO{
	private PreparedStatement pstmt;
	private ResultSet rs;
	public boolean insert(Size size,Connection conn) throws Exception{
		String sql="insert into size(pid,sname) values(?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,size.getPid());		pstmt.setString(2,size.getSname());		if(pstmt.executeUpdate()>0){
			this.close();
			return true;
		}else{
			this.close();
			return false;
		}
	}
	public boolean delete(int id,Connection conn) throws Exception{
		String sql = "delete from size where id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,id);
		if(pstmt.executeUpdate()>0){
			this.close();
			return true;
		}else{
			this.close();
			return false;
		}
	}
	public boolean update(Size size,Connection conn) throws Exception{;
		String sql="update size set pid=?,sname=? where id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,size.getPid());		pstmt.setString(2,size.getSname());		pstmt.setInt(3,size.getId());		if(pstmt.executeUpdate()>0){
			this.close();
			return true;
		}else{			this.close();
			return false;
		}
	}
	public Size selectOne(int id,Connection conn) throws Exception{
		String sql = "select * from size where id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,id);		rs = pstmt.executeQuery();
		Size size = new Size();
		if(rs.next()){
			size.setPid(rs.getInt("pid"));
			size.setSname(rs.getString("sname"));
			size.setId(rs.getInt("id"));
		}
		this.close();
		return size;
	}
	
	public List<Size> select(int pid,Connection conn) throws Exception{
		String sql = "select * from size where pid=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pid);
		rs=pstmt.executeQuery();
		List<Size> sizes = new ArrayList<Size>();
		while(rs.next()){
			Size size = new Size();
			size.setPid(rs.getInt("pid"));
			size.setSname(rs.getString("sname"));
			size.setId(rs.getInt("id"));
			sizes.add(size);
		}
		this.close();
		return sizes;
	}
	
	public List<Size> select(Connection conn) throws Exception{
		String sql = "select * from size";
		pstmt = conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		List<Size> sizes = new ArrayList<Size>();
		while(rs.next()){
			Size size = new Size();
			size.setPid(rs.getInt("pid"));
			size.setSname(rs.getString("sname"));
			size.setId(rs.getInt("id"));
			sizes.add(size);
		}
		this.close();
		return sizes;
	}
	public void close() throws Exception{
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
	}
}