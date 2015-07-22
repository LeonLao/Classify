package com.example.viewtext;


public class Type {
	//private int id;
	//private String typename;
	private String btntext;
	public Type(String btntext){
		super();
		
		//this.id=id;
		this.btntext = btntext;
		//this.typename = typename;
	}
	
	
	
//	public int getId(){
//		return id;
//	}
//	public void setId(int id){
//		this.id= id;
//	}
//	public String getTypename(){
//		return typename;
//	}
//	public void setTypename(String typename){
//		this.typename = typename;
//	}
	
	public String getBtntext(){
		return btntext;
	}
	public void setBtntext(String btntext){
		this.btntext = btntext;
	}

}
