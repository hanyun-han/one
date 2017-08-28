package com.orilore.entitys;
public class Product{
	private Integer id;
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return this.id;
	}
	private String name;
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
	private String kind;
	public void setKind(String kind){
		this.kind=kind;
	}
	public String getKind(){
		return this.kind;
	}
	private Float price;
	public void setPrice(Float price){
		this.price=price;
	}
	public Float getPrice(){
		return this.price;
	}
	private String factory;
	public void setFactory(String factory){
		this.factory=factory;
	}
	public String getFactory(){
		return this.factory;
	}
	private String trem;
	public void setTrem(String trem){
		this.trem=trem;
	}
	public String getTrem(){
		return this.trem;
	}
	private String info;
	public void setInfo(String info){
		this.info=info;
	}
	public String getInfo(){
		return this.info;
	}
}