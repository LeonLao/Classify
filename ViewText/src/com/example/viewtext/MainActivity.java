package com.example.viewtext;


import java.util.ArrayList;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;



public class MainActivity extends Activity {
	private String toolslist[];
//	private ScrollView scrollView;
	
	private ShopAdapter shopAdapter;
	private LayoutInflater inflater;
//	private View views[];
//	private ViewPager list_pager;
	private Type type;
	private ArrayList<Type> list;
	private  String nanbaos[] ;//= {"Ǯ��","Կ�װ�","�����","˫���","�����","����","���İ�","б���","�ְ�","��Ƭ/����"};
	//private GoodsView goodsview;
	private View views[];
	private TextView toolsTextViews[];
	private MyGridView myGridView;
	
	
	
	
 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//LinearLayout layout = new LinearLayout(this);
		//������з�ʽ
		//layout.setOrientation(LinearLayout.VERTICAL);
		
		LinearLayout layout =(LinearLayout)findViewById(R.id.tools);
		//���ֹ���������
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT);
		
		//����Ĳ��ֲ���
		LinearLayout.LayoutParams textparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		
		
		
		for(int i = 0;i<10;i++){
		//TextView textview = (TextView)findViewById(R.id.toptype);
		//GridView gridview = (GridView)findViewById(R.id.listView);
		TextView textview = new TextView(this);
		MyGridView gridview = new MyGridView(this);
		gridview.setNumColumns(3);
		//ScrollView scrollview = new ScrollView(this);
		
				
		textview.setLayoutParams(textparams);
		gridview.setLayoutParams(textparams);
		textview.setText("�а�");
		
		list = new ArrayList<Type>();
		
		list.add(new Type("Ǯ��"));
		list.add(new Type("Կ�װ�"));
		list.add(new Type("�����"));
		list.add(new Type("˫���"));
		list.add(new Type("�����"));
		list.add(new Type("�����"));
		list.add(new Type("�����"));
		list.add(new Type("�����"));
		
		shopAdapter = new ShopAdapter(this);
		shopAdapter.setText(list);
		
		gridview.setAdapter(shopAdapter);
		
			layout.addView(textview, textparams);
			layout.addView(gridview,textparams);
			
			
			
//			super.setContentView(layout,params);
		
		}
		
		
		//RelativeLayout layout = new RelativeLayout(this);
		//layout.setOrientation(LinearLayout.VERTICAL);
		
		
		
		
		
		
		
//		inflater = LayoutInflater.from(this);
//		shopAdapter = new ShopAdapter(this);
//		shopAdapter.setText(list);
//		
//		
//		View view = inflater.inflate(R.layout.from, null);
//		TextView textView = (TextView) view.findViewById(R.id.toptype);
//		
//		GridView goodView = (GridView) view.findViewById(R.id.listView);
//		
//		list = new ArrayList<Type>();
//		
//		list.add(new Type("Ǯ��"));
//		list.add(new Type("Կ�װ�"));
//		list.add(new Type("�����"));
//		list.add(new Type("˫���"));
//		list.add(new Type("�����"));
//		
//		textView.setText("�а�");
//		goodView.setAdapter(shopAdapter);
//		goodView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		layout.addView(view);
		
		
		
		
		
		
		
		
		//setItem();
		//showView();
		
//		 LinearLayout layout = new LinearLayout(this);
//		layout.setOrientation(LinearLayout.VERTICAL);
		

//		setContentView(layout);
		
		
//		ListView showlist = (ListView)findViewById(R.id.listView1);
//		showlist.setAdapter(new MyListAdapter());
//	//	setview();
		
		//shopAdapter =new ShopAdapter(getSupportFragmentManager());
		
	}














//	private void setItem() {
//		list = new ArrayList<Type>();
//		
//		list.add(new Type(1, "Ǯ��"));
//		list.add(new Type(2, "Կ�װ�"));
//		list.add(new Type(3, "�����"));
//		list.add(new Type(4, "˫���"));
//		list.add(new Type(5, "�����"));
		
		
		
		
		
		
//		for(int i = 0;i<toolsTextViews.length;i++){			
//			
//		if(nanbaos[i].contains("Ǯ��")){
//		//	������type��gettypenameӦ��תΪtoolsTextViews[i]��
//			list = new ArrayList<Type>();
//		//	type = new Type(i, btntext)
//			list.add(new Type(1, "Ǯ��"));
//			list.add(new Type(2, "Կ�װ�"));
//			list.add(new Type(3, "�����"));
//			list.add(new Type(4, "˫���"));
//			list.add(new Type(5, "�����"));			
//		}
//		else{
//			list = new ArrayList<Type>();
//			
//			list.add(new Type(1, "Ǯ��"));
//			list.add(new Type(2, "Կ�װ�"));
//			list.add(new Type(3, "�����"));
//			list.add(new Type(4, "˫���"));
//			list.add(new Type(5, "�����"));
//		}		
//		}
//	}







//	private void showView() {
//		nanbaos  = new String[]{"Ǯ��","Կ�װ�","�����","˫���","�����","����","���İ�","б���","�ְ�","��Ƭ/����"};
//		nanbaos  = new String[]{"Ǯ��",};
//		LinearLayout layout = new LinearLayout(this);
//		layout.setOrientation(LinearLayout.VERTICAL);
//		
//		//���ó���
//		toolsTextViews = new Button[nanbaos.length];
//		views = new View[nanbaos.length];
//		
//		for (int i=0; i<nanbaos.length;i++){
//			View view = inflater.inflate(R.layout.from, null);
//			//view.setId(i);
//			TextView textView = (TextView) view.findViewById(R.id.toptype);
//			GridView goodView = (GridView) view.findViewById(R.id.listView);
//			
//			//textView.setText(nanbaos[i]);
//			textView.setText("�а�");
//			goodView.setAdapter(shopAdapter);
//			goodView.setOnItemClickListener(new OnItemClickListener() {
//
//				@Override
//				public void onItemClick(AdapterView<?> parent, View view,
//						int position, long id) {
//					// TODO Auto-generated method stub
//					
//				}
//			});
//			layout.addView(view);
//			//toolsTextViews[i]= textView;
//			//views[i]=view;
//			
//		}		
//	}
	
	
//	private class MyListAdapter extends BaseAdapter{
//		public MyListAdapter(){
//			super();
//		}
//
//		@Override
//		public int getCount() {
//			
//			return showlist.length;
//		}
//
//		@Override
//		public Object getItem(int position) {
//			
//			return showlist[position];
//		}
//
//		@Override
//		public long getItemId(int position) {
//			
//			return position;
//		}
//
//		@Override
//		public View getView(int index, View convertView, ViewGroup parent) {
//			
//						
//			convertView = View.inflate(MainActivity.this, R.layout.from, null);
//			TextView toptype = (TextView) convertView.findViewById(R.id.toptype);
//			GridView listgridview = (GridView)convertView.findViewById(R.id.listView);
//			//convertView.setTag(listview);
//			
//			toptype.setText(showlist[index].title);
			
			
			
			
		//	 shopAdapter =new ShopAdapter(this);
			
			
			
			
//			listgridview.setAdapter(shopAdapter);
//			
//			
//			return convertView;
//			
//		}
//		
//	}
//	
	
	
//	private void setview(){
//	nanbao = new Button[10];
//	
//	String[] nanbaos = {"Ǯ��","Կ�װ�","�����","˫���","�����","����","���İ�","б���","�ְ�","��Ƭ/����"};
//	
//	for(int i = 0;i<nanbao.length;i++){
//		nanbao[i].setText(nanbaos[i]);
//	}
//	}
//	
	
	
//	private static final ShowList[] showlist ={
//		new ShowList("Ʒ���а�", nanbaos),
//		new ShowList("Ʒ��Ů��", null),
//		new ShowList("�������", null),
//		new ShowList("��Ь", null),
//		new ShowList("ŮЬ", null)
//		
//	};
//	
//	
//	private static class ShowList{
//		private final String title;
//		private final String[] names;
//		
//		public ShowList(String title,String[] names){
//			this.title = title;
//			this.names = names;
//		}
//	}
//	
	



	
}
