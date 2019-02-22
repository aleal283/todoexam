package com.ipersof.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class User {
	
	@javax.persistence.Id
	@GeneratedValue
	private Long Id;
	private String fname;
	private String lname;
	private String idfile;
	
	
	public String getIdfile() {
		return idfile;
	}
	public void setIdfile(String idfile) {
		this.idfile = idfile;
	}
	private boolean state;
	
	
	
	public User() {	
	}
	public User(String fname, String lname, String idFile, boolean state ) {
		this.fname = fname;
		this.lname = lname;
		this.state = state;
		this.idfile = idFile;
	}
	
	@Override
	public String toString() {
		return "User [Id=" + Id + ", fname=" + fname + ", lname=" + lname + ", idfile=" + idfile + ", state=" + state
				+ "]";
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	

}
