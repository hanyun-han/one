package com.orilore.bizs;
import com.orilore.entitys.*;
import java.util.*;
public interface ISizeBiz{
	public boolean addSize(Size size);
	public boolean removeSize(int id);
	public boolean modifySize(Size size);
	public Size getSize(int id);
	public List<Size> findSize(int pid);
	public boolean process(List<Size> sizes);
	public void close();
}