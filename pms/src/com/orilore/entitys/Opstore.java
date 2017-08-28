package com.orilore.entitys;
public class Opstore{
	private String name;
	private String sname;
	private Integer id;
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return this.id;
	}
	private String indate;
	public void setIndate(String indate){
		this.indate=indate;
	}
	public String getIndate(){
		return this.indate;
	}
	private Integer pid;
	public void setPid(Integer pid){
		this.pid=pid;
	}
	public Integer getPid(){
		return this.pid;
	}
	private Integer sid;
	public void setSid(Integer sid){
		this.sid=sid;
	}
	public Integer getSid(){
		return this.sid;
	}
	private Integer quantity;
	public void setQuantity(Integer quantity){
		this.quantity=quantity;
	}
	public Integer getQuantity(){
		return this.quantity;
	}
	private String person;
	public void setPerson(String person){
		this.person=person;
	}
	public String getPerson(){
		return this.person;
	}
	private Integer gread;
	public void setGread(Integer gread){
		this.gread=gread;
	}
	public Integer getGread(){
		return this.gread;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
}