package com.example.viewtext;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

public class Fragment_pro_type extends Fragment {
	
	private ArrayList<Type>list;
	private GridView listView;
	private Pro_type_adapter adapter;
	private Type type;
	private String typename;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.from, null);
		listView = (GridView)view.findViewById(R.id.listView);
		typename = getArguments().getString("typename");
		((TextView)view.findViewById(R.id.toptype)).setText(typename);
		adapter = new Pro_type_adapter(getActivity(),list);
		listView.setAdapter(adapter);
		
		
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	
	
	

}
