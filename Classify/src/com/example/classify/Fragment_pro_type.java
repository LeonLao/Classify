package com.example.classify;


import java.util.ArrayList;

import com.example.adapter.Pro_type_adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.tonicartos.widget.stickygridheaders.*;

public class Fragment_pro_type extends Fragment{
	private ArrayList<Type> list;
	private ImageView hint_img;
	private GridView listView;
	private Pro_type_adapter adapter;
	private Type type;
	private ProgressBar progressBar;
	private String typename;
	private MyGridView myGridView;
	private LinearLayout layout;
	private Long itemID;
	private TextView textview ;
	StickyGridHeadersGridView gridHeadersGridView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_pro_type, null);
		
		gridHeadersGridView = (StickyGridHeadersGridView) view.findViewById(R.id.gridview);
		
		
				
		
		//hint_img = (ImageView)view.findViewById(R.id.hint_img);
		//listView = (GridView)view.findViewById(R.id.listView);
		
		
		typename = getArguments().getString("typename");		
		//((TextView)view.findViewById(R.id.toptype)).setText(typename);
		
		
		GetTypeList();
		
		
		adapter = new Pro_type_adapter(getActivity(),list, R.layout.list_pro_type_item, R.layout.list_pro_type_item);
		gridHeadersGridView.setAdapter(adapter);
		listView.setAdapter(adapter);
		gridHeadersGridView.setAreHeadersSticky(true);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}
		});
		return view;
	}
	

	private void GetTypeList() {
		list = new ArrayList<Type>();
		for(int i = 1 ;i<20;i++){
			 type = new Type(i, typename+i, i, typename+i);
			 list.add(type);
		}
		
		
		}
		
		
	
	
	
	
//	private void GetTypeList() {
//	list = new ArrayList<Type>();
//	for(int i = 1;i<20;i++){
//		type = new Type(i,typename+i,"");
//		list.add(type);
//	}
//	progressBar.setVisibility(View.GONE);
//}
	
}
