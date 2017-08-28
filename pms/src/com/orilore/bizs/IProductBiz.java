package com.orilore.bizs;
import com.orilore.entitys.*;

import java.util.*;
public interface IProductBiz{
	public int addProduct(Product product);
	public boolean removeProduct(int id);
	public boolean modifyProduct(Product product);
	public Product getProduct(int id);
	public List<Product> findProduct(Map<String,String> cond);
	public int getPages(Map<String,String> cond);
	public List<List<String>> getInfo();
	public void close();
}