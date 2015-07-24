package com.example.classify;

public class Type {
	
	private long tid;
	private String title;
	private long iid;
	private String item;
	
//	public Type(long tid,String typename,long iid,String itemtext){
//		
//		super();
//		this.tid = tid;
//		this.typename = typename;
//		this.iid= iid;
//		this.itemtext = itemtext;
//		
//	}
	public Type(){
		
	}
	
	
	@Override
	public String toString() {
		
		return "Type{" +
                "tid=" + tid +
                ", title='" + title + '\'' +
                ", iid=" + iid +
                ", item='" + item + '\'' +
                '}';
	}



	public long getTid(){
		return tid;
	}
	public void setTid(long tid){
		this.tid = tid;	
	}
	
	
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	
	public long getIid(){
		return iid;
	}
	public void setIid(long iid){
		this.iid =iid;
	}
	
	public String getItem(){
		return item;		
	}
	public void setItem(String item){
		this.item = item;
	}
	

}
