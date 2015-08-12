package com.example.classifytext;

import android.R.string;

public class Type {
	long tid;
	String title;
	long iid;
	String item;
	
	public Type(){
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Be{" +
        "iid=" + tid +
        ", title='" + title + '\'' +
        ", iid=" + iid +
        ", item='" + item + '\'' +
        '}';
	}
	
	public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getIid() {
        return iid;
    }

    public void setIid(long iid) {
        this.iid = iid;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    } 
	

}
