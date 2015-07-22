package com.example.classify;

public class Type {
	
	private long tid;
	private String typename;
	private long iid;
	private String itemtext;
	
	public Type(long tid,String typename,long iid,String itemtext){
		
		super();
		this.tid = tid;
		this.typename = typename;
		this.iid= iid;
		this.itemtext = itemtext;
		
	}
	
	
	@Override
	public String toString() {
		
		return "Be{" +
                "tid=" + tid +
                ", typename='" + typename + '\'' +
                ", iid=" + iid +
                ", itemtext='" + itemtext + '\'' +
                '}';
	}



	public long getTid(){
		return tid;
	}
	public void setTid(long tid){
		this.tid = tid;	
	}
	
	
	public String getTypename(){
		return typename;
	}
	public void setTypename(String typename){
		this.typename = typename;
	}
	
	public long getIid(){
		return iid;
	}
	public void setIid(long iid){
		this.iid =iid;
	}
	
	public String getItemtext(){
		return itemtext;		
	}
	public void setItemtext(String itemtext){
		this.itemtext = itemtext;
	}
	

}
