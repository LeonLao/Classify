package com.example.classifytext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

public class MainActivity extends Activity {
	StickyGridHeadersGridView mGridView;
	MyAdapter adapter;
	List<Type> list;
	String[] lists={"1","2","13","4","15","16","17","81","13","14","1","2","13","4","15","16","17","81","13","14"};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mGridView = (StickyGridHeadersGridView)findViewById(R.id.asset_grid);
		list = new ArrayList<Type>();
		showData();
		adapter = new MyAdapter(this, list, R.layout.title_text, R.layout.item);
		mGridView.setAdapter(adapter);
		mGridView.setAreHeadersSticky(true);
		
		
	}
	


	private void showData() {
//		Type type = new Type();
//		type.setTid(1);
//		type.setTitle(""+1);
		
		for(int i=0;i<10;i++){
			Type type = new Type();
			type.setTid(1);
			type.setTitle(""+1);
			type.setIid(i);
			type.setItem(lists[i]);
			list.add(type);
		}
		for(int i=0;i<20;i++){
			Type type = new Type();
			type.setTid(2);
			type.setTitle(""+2);
			type.setIid(i);
			type.setItem(lists[i]);
			list.add(type);
		}
				
		
//		for(int i=0;i<20;i++){
//			Type type = new Type();
//			type.setTid(i/10);
//			type.setTitle(""+i/10);
//			type.setIid(i);
//			type.setItem(lists[i]);
//			list.add(type);
//		}
		
	}

	
}
