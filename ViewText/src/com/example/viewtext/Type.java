package com.example.viewtext;


public class Type {
	
	private String typename;
	private String btnname;
	public Type(String typename,String btnname){
		super();
		
		this.typename = btnname;
		this.typename = typename;
	}
	
	
	
	
	public String getTypename(){
		return typename;
	}
	public void setTypename(String typename){
		this.typename = typename;
	}
	
	public String getBtnname(){
		return btnname;
	}
	public void setBtnname(String btnname){
		this.btnname = btnname;
	}

}
