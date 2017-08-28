package com.orilore.daos;
import com.orilore.entitys.Product;
import java.util.*;
import java.sql.*;
public interface IProductDAO{
	public int insert(Product product,Connection conn) throws Exception;
	public boolean delete(int id,Connection conn) throws Exception;
	public boolean update(Product product,Connection conn) throws Exception;
	public Product selectOne(int id,Connection conn) throws Exception;
	public List<Product> select(Map<String,String> cond,Connection conn) throws Exception;
	public List<List<String>> selectInfo(Connection conn) throws Exception;
	public int selectPages(Map<String,String> cond,Connection conn) throws Exception;
	public void close() throws Exception;
}