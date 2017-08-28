package com.orilore.daos;
import com.orilore.entitys.Opstore;
import java.sql.*;
import java.util.*;
public class OpstoreDAO implements IOpstoreDAO{
	private PreparedStatement pstmt;
	private ResultSet rs;
	public boolean insert(Opstore opstore,Connection conn) throws Exception{
		String sql="insert into opstore(indate,pid,sid,quantity,person,gread) values(?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,opstore.getIndate());		pstmt.setInt(2,opstore.getPid());		pstmt.setInt(3,opstore.getSid());		pstmt.setInt(4,opstore.getQuantity());		pstmt.setString(5,opstore.getPerson());		pstmt.setInt(6,opstore.getGread());		if(pstmt.executeUpdate()>0){
			this.close();
			return true;
		}else{
			this.close();
			return false;
		}
	}
	public boolean delete(int id,Connection conn) throws Exception{
		String sql = "delete from opstore where id=?";
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
	public boolean update(Opstore opstore,Connection conn) throws Exception{;
		String sql="update opstore set indate=?,pid=?,sid=?,quantity=?,person=?,gread=? where id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,opstore.getIndate());		pstmt.setInt(2,opstore.getPid());		pstmt.setInt(3,opstore.getSid());		pstmt.setInt(4,opstore.getQuantity());		pstmt.setString(5,opstore.getPerson());		pstmt.setInt(6,opstore.getGread());		pstmt.setInt(7,opstore.getId());		if(pstmt.executeUpdate()>0){
			this.close();
			return true;
		}else{
			this.close();
			return false;
		}
	}
	public Opstore selectOne(int id,Connection conn) throws Exception{
		String sql = "select * from opstore where id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,id);		rs = pstmt.executeQuery();
		Opstore opstore = new Opstore();
		if(rs.next()){
			opstore.setIndate(rs.getString("indate"));
			opstore.setPid(rs.getInt("pid"));
			opstore.setSid(rs.getInt("sid"));
			opstore.setQuantity(rs.getInt("quantity"));
			opstore.setPerson(rs.getString("person"));
			opstore.setGread(rs.getInt("gread"));
			opstore.setId(rs.getInt("id"));
		}
		this.close();
		return opstore;
	}
	public List<Opstore> select(int gread,Connection conn) throws Exception{
		String sql = " select a.id,a.pid,a.sid,a.indate,b.name,";
		sql+=" c.sname,a.quantity,a.person,a.gread ";
		sql+=" from opstore a,product b,size c ";
		sql+=" where a.pid=b.id and a.sid=c.id and a.gread=?";
		sql+=" order by indate desc";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, gread);
		
		rs=pstmt.executeQuery();
		List<Opstore> opstores = new ArrayList<Opstore>();
		while(rs.next()){
			Opstore opstore = new Opstore();
			opstore.setIndate(rs.getString("indate"));
			opstore.setPid(rs.getInt("pid"));
			opstore.setSid(rs.getInt("sid"));
			opstore.setName(rs.getString("name"));
			opstore.setSname(rs.getString("sname"));
			opstore.setQuantity(rs.getInt("quantity"));
			opstore.setPerson(rs.getString("person"));
			opstore.setGread(rs.getInt("gread"));
			opstore.setId(rs.getInt("id"));
			opstores.add(opstore);
		}
		this.close();
		return opstores;
	}
	public void close() throws Exception{
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
	}
	@Override
	public List<Map<String, Object>> outstate(String year, Connection conn)
			throws Exception {
		String sql = "select sum(quantity) as total,b.name,";
		sql+="'"+year+"年1月' as month";
		sql+=" from opstore a,product b where a.pid=b.id and gread=2 and ";
		sql+=" indate between '"+year+"-01-01' and '"+year+"-1-31'";
		sql+=" group by b.name,month";
		sql+=" union";
		sql+=" select sum(quantity) as total,b.name,";
		sql+=" '"+year+"年2月' as month";
		sql+=" from opstore a,product b where a.pid=b.id and gread=2 and ";
		sql+=" indate between '"+year+"-02-01' and last_day('"+year+"-02-01')";
		sql+=" group by b.name,month ";
		sql+=" union";
		sql+=" select sum(quantity) as total,b.name,";
		sql+=" '"+year+"年2月' as month";
		sql+=" from opstore a,product b where a.pid=b.id and gread=2 and ";
		sql+=" indate between '"+year+"-02-01' and last_day('"+year+"-02-01')";
		sql+=" group by b.name,month ";
		sql+=" union";
		sql+=" select sum(quantity) as total,b.name,";
		sql+=" '"+year+"年3月' as month";
		sql+=" from opstore a,product b where a.pid=b.id and gread=2 and ";
		sql+=" indate between '"+year+"-03-01' and last_day('"+year+"-03-01')";
		sql+=" group by b.name,month ";
		sql+=" union";
		sql+=" select sum(quantity) as total,b.name,";
		sql+=" '"+year+"年4月' as month";
		sql+=" from opstore a,product b where a.pid=b.id and gread=2 and ";
		sql+=" indate between '"+year+"-04-01' and last_day('"+year+"-04-01')";
		sql+=" group by b.name,month ";
		sql+=" union";
		sql+=" select sum(quantity) as total,b.name,";
		sql+=" '"+year+"年5月' as month";
		sql+=" from opstore a,product b where a.pid=b.id and gread=2 and ";
		sql+=" indate between '"+year+"-05-01' and last_day('"+year+"-05-01')";
		sql+=" group by b.name,month ";
		sql+=" union";
		sql+=" select sum(quantity) as total,b.name,";
		sql+=" '"+year+"年6月' as month";
		sql+=" from opstore a,product b where a.pid=b.id and gread=2 and ";
		sql+=" indate between '"+year+"-06-01' and last_day('"+year+"-06-01')";
		sql+=" group by b.name,month ";
		sql+=" union";
		sql+=" select sum(quantity) as total,b.name,";
		sql+=" '"+year+"年7月' as month";
		sql+=" from opstore a,product b where a.pid=b.id and gread=2 and ";
		sql+=" indate between '"+year+"-07-01' and last_day('"+year+"-07-01')";
		sql+=" group by b.name,month ";
		sql+=" union";
		sql+=" select sum(quantity) as total,b.name,";
		sql+=" '"+year+"年8月' as month";
		sql+=" from opstore a,product b where a.pid=b.id and gread=2 and ";
		sql+=" indate between '"+year+"-08-01' and last_day('"+year+"-08-01')";
		sql+=" group by b.name,month ";
		sql+=" union";
		sql+=" select sum(quantity) as total,b.name,";
		sql+=" '"+year+"年9月' as month";
		sql+=" from opstore a,product b where a.pid=b.id and gread=2 and ";
		sql+=" indate between '"+year+"-09-01' and last_day('"+year+"-09-01')";
		sql+=" group by b.name,month ";
		sql+=" union";
		sql+=" select sum(quantity) as total,b.name,";
		sql+=" '"+year+"年10月' as month";
		sql+=" from opstore a,product b where a.pid=b.id and gread=2 and ";
		sql+=" indate between '"+year+"-10-01' and last_day('"+year+"-10-01')";
		sql+=" group by b.name,month ";
		sql+=" union";
		sql+=" select sum(quantity) as total,b.name,";
		sql+=" '"+year+"年11月' as month";
		sql+=" from opstore a,product b where a.pid=b.id and gread=2 and ";
		sql+=" indate between '"+year+"-11-01' and last_day('"+year+"-11-01')";
		sql+=" group by b.name,month";
		sql+=" union";
		sql+=" select sum(quantity) as total,b.name,";
		sql+=" '"+year+"年12月' as month";
		sql+=" from opstore a,product b where a.pid=b.id and gread=2 and ";
		sql+=" indate between '"+year+"-12-01' and last_day('"+year+"-12-01')";
		sql+=" group by b.name,month";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		List<Map<String,Object>> rows = new ArrayList<Map<String,Object>>();
		while(rs.next()){
			Map<String,Object> row = new HashMap<String,Object>();
			row.put("name", rs.getString("name"));
			row.put("month", rs.getString("month"));
			row.put("total", rs.getInt("total"));
			rows.add(row);
		}
		this.close();
		return rows;
	}
}