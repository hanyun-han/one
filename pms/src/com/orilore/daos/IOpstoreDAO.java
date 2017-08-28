package com.orilore.daos;
import com.orilore.entitys.Opstore;
import java.util.*;
import java.sql.*;
public interface IOpstoreDAO{
	public boolean insert(Opstore opstore,Connection conn) throws Exception;
	public boolean delete(int id,Connection conn) throws Exception;
	public boolean update(Opstore opstore,Connection conn) throws Exception;
	public Opstore selectOne(int id,Connection conn) throws Exception;
	public List<Opstore> select(int gread,Connection conn) throws Exception;
	public List<Map<String,Object>> outstate(String year,Connection conn) throws Exception;
	public void close() throws Exception;
}