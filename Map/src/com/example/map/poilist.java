package com.example.map;

import java.util.ArrayList;
import java.util.List;

import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKShareUrlResult;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;

import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;


public class poilist extends Activity {
	
	
	
	/** ������������λ�ü������ܱ߼�������Χ���� */
	private MKSearch MKSearch = null;
	/** ��ͼ��������� */
	
	private ListView poilist = null;
	private String city = null;
	private String key = null;
	private List<MKPoiInfo> poiInfos = null;
	private String currentAddr = null;
	private String type = null;
	private GeoPoint searchpoint;
	private myLocationListener locationlistener;
	private LocationClient locClient;
	private LocationData locData;
	private Button next = null;
	private Button previous = null;
	private int load_index;
	private int autopage;
	private int ray;
	boolean frist = true;
	boolean near = false;
	
	private Handler handler = new Handler();
	private Runnable task;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poilist);
		CharSequence title = "��������б���";
		setTitle(title);
		
		MyApplication app = (MyApplication) this.getApplication();
		
		task = new Runnable() {
			
			@Override
			public void run() {
				if(near){
				// TODO Auto-generated method stub
				handler.postDelayed(this, 1000);
				MKSearch.poiSearchNearBy(key, searchpoint, ray);
				handler.removeCallbacks(task);
				}
			}
		};
		
		
		locData = new LocationData();
		// �ֶ���λ��Դ��Ϊ�찲�ţ���ʵ��Ӧ���У���ʹ�ðٶȶ�λSDK��ȡλ����Ϣ��Ҫ��SDK����ʾһ��λ�ã���Ҫʹ�ðٶȾ�γ�����꣨bd09ll��
		locData.latitude = 39.945;
		locData.longitude = 116.404;
		locData.direction = 2.0f;
		
		locationlistener = new myLocationListener();
		locClient = new LocationClient(this);
		LocationClientOption options = new LocationClientOption();
		options.setCoorType("bd09ll");// ���صĶ�λ����ǰٶȾ�γ�ȣ�Ĭ��ֵgcj02
		//options.setScanSpan(1000);// ���÷���λ����ļ��ʱ��Ϊ5000ms
		locClient.setLocOption(options);
		locClient.registerLocationListener(locationlistener);
		locClient.start();
		
		
//		locClient = new LocationClient(this);
//		LocationClientOption option = new LocationClientOption();
//		//option.setOpenGps(true);//��gps
//		option.setCoorType("bd09ll"); // ���÷���ֵ����������
//		option.setPriority(LocationClientOption.NetWorkFirst);// ���ö�λ���ȼ�
//		option.setProdName("mylocationdemo");//���ò�Ʒ�����ơ�ǿ�ҽ�����ʹ���Զ���Ĳ�Ʒ�����ƣ����������Ժ�Ϊ���ṩ����Ч׼ȷ�Ķ�λ����
//		//option.setScanSpan(1000);//��λʱ����
//		locClient.setLocOption(option);
//		
//		locData = new LocationData();
//		locationlistener = new myLocationListener();
//		locClient.registerLocationListener(locationlistener);
		
				
		previous = (Button)findViewById(R.id.previouspage);
		previous.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(load_index == 0){
//					if(near){
//					MKSearch.poiSearchNearBy(key, searchpoint, ray);
//					near = false;
//					}
//					else{
					Toast.makeText(poilist.this, "�Ѿ��ǵ�һҳ", Toast.LENGTH_SHORT).show();
//					}
				}
				else{
					MKSearch.goToPoiPage(--load_index);
					int index = load_index+1;
					Toast.makeText(poilist.this, "��"+autopage+"ҳ"+"\n"+"��"+index+"ҳ",
							Toast.LENGTH_SHORT).show();
				} 
			}
		});
		
		
		next = (Button)findViewById(R.id.nextpage);
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			try {
				int auto = autopage-1;
				if(load_index < auto){					
					MKSearch.goToPoiPage(++load_index);
					int index = load_index+1;
					Toast.makeText(poilist.this, "��"+autopage+"ҳ"+"\n"+"��"+index+"ҳ",
							Toast.LENGTH_SHORT).show();
				}
				else if(load_index == auto){
					
					Toast.makeText(poilist.this, "�Ѿ������һҳ", Toast.LENGTH_SHORT).show();
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				 
			}
				
			}
		});
		
			
		poilist = (ListView) findViewById(R.id.poilist);
		
		
		poilist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MKPoiInfo info = poiInfos.get(position);
				MKSearch.poiDetailSearch(info.uid);
				
			}
		});
		
		poilist.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				MKPoiInfo info  = poiInfos.get(position);
				currentAddr = info.address;
				MKSearch.poiDetailShareURLSearch(info.uid);
				return false;
			}
		});
		
		MKSearch = new MKSearch();
		MKSearch.init(app.mBMapManager, new MKSearchListener(){

			@Override
			public void onGetAddrResult(MKAddrInfo arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onGetDrivingRouteResult(MKDrivingRouteResult arg0,
					int arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onGetPoiDetailSearchResult(int type, int error) {
				// ��ȡPOI���ص�����ϸ��Ϣ
				// type-ֵδGeoSearchManagerGEO_SEARCH_DETAILS
				if (error != 0) {
					Toast.makeText(poilist.this, "δ�ҵ����",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(poilist.this, "��ϸ��Ϣ�����������",
							Toast.LENGTH_SHORT).show();
				}
				
			}

			@Override
			public void onGetPoiResult(MKPoiResult result, int type, int error) {
				// TODO Auto-generated method stub
				Log.d("poilist", "onGetPoiResult");
				load_index = result.getPageIndex();
				autopage = result.getNumPages();
				
				if (error != 0 || result == null) {
					Toast.makeText(poilist.this, "��Ǹ����δ�ҵ�����������������ԣ�",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (result.getCurrentNumPois() != 0){
//					HashMap<String, Object> map = new HashMap<String, Object>();
					poiInfos = result.getAllPoi();
					List<String> names = new ArrayList<String>();
					for (int i = 0; i < poiInfos.size(); i++) {
						names.add(poiInfos.get(i).name);
					}
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(poilist.this, android.R.layout.simple_list_item_1, names);
					poilist.setAdapter(adapter);
				}
			}

			@Override
			public void onGetShareUrlResult(MKShareUrlResult result, int type,
					int error) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.putExtra(Intent.EXTRA_TEXT, "��������ͨ��ApMap��������һ��λ��: "+
					       currentAddr+
					       " -- "+result.url);
				intent.setType("text/plain");
				startActivity(Intent.createChooser(intent, "���̴�����"));
			}

			@Override
			public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onGetTransitRouteResult(MKTransitRouteResult arg0,
					int arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onGetWalkingRouteResult(MKWalkingRouteResult arg0,
					int arg1) {
				// TODO Auto-generated method stub
				
			}
			
		});
		Intent intent = this.getIntent();
		type = intent.getStringExtra("type");
		int types = Integer.parseInt(type);
		key = intent.getStringExtra("intentkey");
		city = intent.getStringExtra("intentcity");	
	    ray = intent.getIntExtra("intentray", 500);
	    showpoi(types);
//		switch (types){
//		case 1:			
//			MKSearch.poiSearchInCity(city, key);
//			break;
//		case 2:
//			locClient.start();
//			
////			city = intent.getStringExtra("intentray");
////			int rays = Integer.parseInt(city);
//			MKSearch.poiSearchNearBy(key, searchpoint, ray);
//			break;
//		
//		}
		
//		if (types == 1){
//		city = intent.getStringExtra("intentcity");		
//		MKSearch.poiSearchInCity(city, key);
//		}
//		else if(types == 2){
//			if (locClient == null){
//				return;
//			}
//			locClient.start();
//			
//			city = intent.getStringExtra("intentray");
//			int rays = Integer.parseInt(city);
//			
//			MKSearch.poiSearchNearBy(key, searchpoint, rays);
//		}
				
	}
	private void showpoi(int i) {
		// TODO Auto-generated method stub
		switch(i){
		case 1:
			MKSearch.poiSearchInCity(city, key);
			break;
		case 2:
			if(frist) 
				near = true;
				handler.postDelayed(task, 1000);
				
				
				
//			Toast.makeText(poilist.this, "��Ǹ�����س�ʱ����������һҳ����ťˢ��", Toast.LENGTH_LONG).show();
//			frist = false;
			
//			new Thread (new Runnable(){
//				public void run(){
//					try {
//						Thread.sleep(5000);
//						
//						MKSearch.poiSearchNearBy(key, searchpoint, ray);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//				}
//			});
//			near= false;
			break;
		}
	}
	public class myLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// TODO Auto-generated method stub
			if(location == null){
				return;
			}
			locData.latitude = location.getLatitude();
			locData.longitude = location.getLongitude();
			searchpoint = new GeoPoint(
					(int) (locData.latitude * 1e6),
					(int) (locData.longitude * 1e6));
			
		}

		@Override
		public void onReceivePoi(BDLocation arg0) {
			// TODO Auto-generated method stub
			
		}

	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(locClient != null && locClient.isStarted()){
			locClient.stop();
			locClient = null;
			
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			poilist.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	
}
