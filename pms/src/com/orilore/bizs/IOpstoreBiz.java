package com.orilore.bizs;
import com.orilore.entitys.*;

import java.util.*;
public interface IOpstoreBiz{
	public boolean addOpstore(Opstore opstore);
	public boolean removeOpstore(int id);
	public boolean modifyOpstore(Opstore opstore);
	public Opstore getOpstore(int id);
	public List<Opstore> findOpstore(int g);
	public int getQuantity(int pid,int sid);
	public List<Map<String,Object>> outstate(String year);
	public void close();
}