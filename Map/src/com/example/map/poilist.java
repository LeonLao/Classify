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
	
	
	
	/** 搜索服务，用于位置检索，周边检索，范围检索 */
	private MKSearch MKSearch = null;
	/** 地图引擎管理类 */
	
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
		CharSequence title = "检索结果列表返回";
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
		// 手动将位置源置为天安门，在实际应用中，请使用百度定位SDK获取位置信息，要在SDK中显示一个位置，需要使用百度经纬度坐标（bd09ll）
		locData.latitude = 39.945;
		locData.longitude = 116.404;
		locData.direction = 2.0f;
		
		locationlistener = new myLocationListener();
		locClient = new LocationClient(this);
		LocationClientOption options = new LocationClientOption();
		options.setCoorType("bd09ll");// 返回的定位结果是百度经纬度，默认值gcj02
		//options.setScanSpan(1000);// 设置发起定位请求的间隔时间为5000ms
		locClient.setLocOption(options);
		locClient.registerLocationListener(locationlistener);
		locClient.start();
		
		
//		locClient = new LocationClient(this);
//		LocationClientOption option = new LocationClientOption();
//		//option.setOpenGps(true);//打开gps
//		option.setCoorType("bd09ll"); // 设置返回值的坐标类型
//		option.setPriority(LocationClientOption.NetWorkFirst);// 设置定位优先级
//		option.setProdName("mylocationdemo");//设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
//		//option.setScanSpan(1000);//定位时间间隔
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
					Toast.makeText(poilist.this, "已经是第一页", Toast.LENGTH_SHORT).show();
//					}
				}
				else{
					MKSearch.goToPoiPage(--load_index);
					int index = load_index+1;
					Toast.makeText(poilist.this, "共"+autopage+"页"+"\n"+"第"+index+"页",
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
					Toast.makeText(poilist.this, "共"+autopage+"页"+"\n"+"第"+index+"页",
							Toast.LENGTH_SHORT).show();
				}
				else if(load_index == auto){
					
					Toast.makeText(poilist.this, "已经是最后一页", Toast.LENGTH_SHORT).show();
					
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
				// 获取POI搜素到的详细信息
				// type-值未GeoSearchManagerGEO_SEARCH_DETAILS
				if (error != 0) {
					Toast.makeText(poilist.this, "未找到结果",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(poilist.this, "详细信息搜索结果返回",
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
					Toast.makeText(poilist.this, "抱歉，尚未找到结果，换个条件试试？",
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
				intent.putExtra(Intent.EXTRA_TEXT, "您的朋友通过ApMap与您分享一个位置: "+
					       currentAddr+
					       " -- "+result.url);
				intent.setType("text/plain");
				startActivity(Intent.createChooser(intent, "将短串分享到"));
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
				
				
				
//			Toast.makeText(poilist.this, "抱歉，加载超时，请点击“上一页”按钮刷新", Toast.LENGTH_LONG).show();
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
