package com.orilore.daos;
import com.orilore.entitys.Store;
import java.sql.*;
import java.util.List;
import java.util.Map;
public interface IStoreDAO{
	public boolean insert(Store store,Connection conn) throws Exception;
	public boolean update(Store store,Connection conn) throws Exception;
	public Store select(int pid,int sid,Connection conn) throws Exception;
	public List<Map<String,Object>> state(Connection conn) throws Exception;
	public void close() throws Exception;
}