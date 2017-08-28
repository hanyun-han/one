package com.orilore.daos;
import com.orilore.entitys.Product;
import java.sql.*;
import java.util.*;
public class ProductDAO implements IProductDAO{
	private PreparedStatement pstmt;
	private ResultSet rs;
	public int insert(Product product,Connection conn) throws Exception{
		int pid = 0;
		String sql="insert into product(name,kind,price,factory,trem,info) values(?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,product.getName());		pstmt.setString(2,product.getKind());		pstmt.setFloat(3,product.getPrice());		pstmt.setString(4,product.getFactory());		pstmt.setString(5,product.getTrem());		pstmt.setString(6,product.getInfo());		if(pstmt.executeUpdate()>0){
			sql = "select max(id) as pid from product";
			pstmt = conn.prepareStatement(sql);
		 	rs = pstmt.executeQuery();
			if(rs.next()){
				pid = rs.getInt("pid");
			}
		}
		this.close();
		return pid; 
	}
	public boolean delete(int id,Connection conn) throws Exception{
		String sql = "delete from product where id=?";
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
	public boolean update(Product product,Connection conn) throws Exception{;
		String sql="update product set name=?,kind=?,price=?,factory=?,trem=?,info=? where id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,product.getName());		pstmt.setString(2,product.getKind());		pstmt.setFloat(3,product.getPrice());		pstmt.setString(4,product.getFactory());		pstmt.setString(5,product.getTrem());		pstmt.setString(6,product.getInfo());		pstmt.setInt(7,product.getId());		if(pstmt.executeUpdate()>0){
			this.close();
			return true;
		}else{
			this.close();
			return false;
		}
	}
	public Product selectOne(int id,Connection conn) throws Exception{
		String sql = "select * from product where id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,id);		rs = pstmt.executeQuery();
		Product product = new Product();
		if(rs.next()){
			product.setName(rs.getString("name"));
			product.setKind(rs.getString("kind"));
			product.setPrice(rs.getFloat("price"));
			product.setFactory(rs.getString("factory"));
			product.setTrem(rs.getString("trem"));
			product.setInfo(rs.getString("info"));
			product.setId(rs.getInt("id"));
		}
		this.close();
		return product;
	}
	public List<Product> select(Map<String,String> cond,Connection conn) throws Exception{
		String sql = "select * from product where ";
		if(cond!=null){
			String name = cond.get("name");
			if(name!=null && !"".equals(name)){
				sql+=" name like '%"+name+"%' and ";
			}
			String kind = cond.get("kind");
			if(kind!=null && !"".equals(kind)){
				sql+=" kind='"+kind+"' and ";
			}
			String f = cond.get("factory");
			if(f!=null && !"".equals(f)){
				sql+=" factory='"+f+"' and ";
			}
			String p1 = cond.get("price1");
			String p2 = cond.get("price2");
			if(p1!=null && !"".equals(p1) && (p2==null || "".equals(p2))){
				sql+=" price>="+p1+" and ";
			}else if((p1==null || "".equals(p1)) && p2!=null && !"".equals(p2)){
				sql+=" price<="+p2+" and ";
			}else if(!"".equals(p1) && p1!=null && p2!=null && !"".equals(p2)){
				sql+=" price between "+p1+" and "+p2+" and ";
			}
		}
		String pg = cond.get("page");//Ò³Âë
		if(pg==null || "".equals(pg)){
			pg = "1";
		}
		
		String ct = cond.get("count");//Ã¿Ò³¼ÇÂ¼Êý
		if(ct==null || "".equals(ct)){
			ct = "10";
		}
		int begin = (Integer.parseInt(pg)-1)*Integer.parseInt(ct);
		sql+=" 2>1 order by id desc limit "+begin+","+ct;
		pstmt = conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		List<Product> products = new ArrayList<Product>();
		while(rs.next()){
			Product product = new Product();
			product.setName(rs.getString("name"));
			product.setKind(rs.getString("kind"));
			product.setPrice(rs.getFloat("price"));
			product.setFactory(rs.getString("factory"));
			product.setTrem(rs.getString("trem"));
			product.setInfo(rs.getString("info"));
			product.setId(rs.getInt("id"));
			products.add(product);
		}
		this.close();
		return products;
	}
	public void close() throws Exception{
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
	}
	@Override
	public List<List<String>> selectInfo(Connection conn) throws Exception {
		String sql = "select distinct kind from product";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		List<String> kinds = new ArrayList<String>();
		while(rs.next()){
			kinds.add(rs.getString(1));
		}
		
		sql = "select distinct factory from product";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		List<String> fas = new ArrayList<String>();
		while(rs.next()){
			fas.add(rs.getString(1));
		}
		
		List<List<String>> infos = new ArrayList<List<String>>();
		infos.add(kinds);
		infos.add(fas);
		this.close();
		return infos;
	}
	@Override
	public int selectPages(Map<String,String> cond, Connection conn) throws Exception {
		String sql = "select count(*) from product where ";
		if(cond!=null){
			String name = cond.get("name");
			if(name!=null && !"".equals(name)){
				sql+=" name like '%"+name+"%' and ";
			}
			String kind = cond.get("kind");
			if(kind!=null && !"".equals(kind)){
				sql+=" kind='"+kind+"' and ";
			}
			String f = cond.get("factory");
			if(f!=null && !"".equals(f)){
				sql+=" factory='"+f+"' and ";
			}
			String p1 = cond.get("price1");
			String p2 = cond.get("price2");
			if(p1!=null && !"".equals(p1) && (p2==null || "".equals(p2))){
				sql+=" price>="+p1+" and ";
			}else if((p1==null || "".equals(p1)) && p2!=null && !"".equals(p2)){
				sql+=" price<="+p2+" and ";
			}else if(!"".equals(p1) && p1!=null && p2!=null && !"".equals(p2)){
				sql+=" price between "+p1+" and "+p2+" and ";
			}
		}
		sql+=" 2>1 ";
		pstmt = conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		int pages = 0;
		if(rs.next()){
			int count = rs.getInt(1);
			int m = 10;
			String n = cond.get("count");
			if(n!=null && !"".equals(n)){
				m = Integer.parseInt(n);
			}
			if(count%m==0){
				pages = count/m;
			}else{
				pages = count/m+1;
			}
		}
		this.close();
		return pages;
	}
}