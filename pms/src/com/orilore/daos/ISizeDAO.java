package com.orilore.daos;
import com.orilore.entitys.Size;
import java.util.*;
import java.sql.*;
public interface ISizeDAO{
	public boolean insert(Size size,Connection conn) throws Exception;
	public boolean delete(int id,Connection conn) throws Exception;
	public boolean update(Size size,Connection conn) throws Exception;
	public Size selectOne(int id,Connection conn) throws Exception;
	public List<Size> select(Connection conn) throws Exception;
	public List<Size> select(int pid,Connection conn) throws Exception;
	public void close() throws Exception;
}