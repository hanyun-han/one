package com.orilore.bizs;
import com.orilore.entitys.*;
import java.util.*;
public interface IStoreBiz{
	public boolean addStore(Store store);
	public List<Map<String,Object>> storeState();
	public void close();
}