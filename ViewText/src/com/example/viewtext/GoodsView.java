package com.example.viewtext;


import java.util.ArrayList;



import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;


public class GoodsView extends Fragment{
	private GridView listView;
	private String typename;
	private Type type;
	private ArrayList<Type> list;
	private ShopAdapter adapter;
	
	
	//public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		View view = inflater.inflate(R.layout.goods_view, null);
//		typename = getArguments().getString("typename");
//		listView = (GridView)view.findViewById(R.id.gridView1);
//		((TextView)view.findViewById(R.id.textView1)).setText(typename);
//		GetTypeList();
//		adapter = new ShopAdapter(getActivity(),list);
//		listView.setAdapter(adapter);
//		listView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//	
//		return ;
//		
//	}

//	private void GetTypeList() {
//		list = new ArrayList<Type>();
//		for(int i = 1;i<20;i++){
//			type = new Type(i,typename+i,"");
//			list.add(type);
//		}
//		
//	}

	
	

}
