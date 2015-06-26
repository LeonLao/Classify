package com.example.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.MKMapTouchListener;
import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.PoiOverlay;
import com.baidu.mapapi.map.MyLocationOverlay.LocationMode;
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

public class MainActivity extends Activity {
	
	

	// 定义变量
	/** 地图引擎管理类 */
	public static BMapManager MapManager = null;

	/** 显示地图的View */
	// MyLocationMapView mMapView = null;
	MapView mMapView = null;

	/** 地图控制器 */
	private MapController mMapController = null;

	/** 搜索服务，用于位置检索，周边检索，范围检索 */
	private MKSearch MKSearch = null;

	/** MKMapViewListener 用于处理地图事件回调 */
	MKMapViewListener mMapListener = null;

	/** 用于截获屏坐标 */
	MKMapTouchListener mapTouchListener = null;

	/** 当前地点击点 */
	private GeoPoint currentPt = null;
	private GeoPoint mypoint = null;

	private String touchType = null;
	
	GeoPoint markpoint = null;
	private String addr = "";

	// 定位图层
	// locationOverlay myLocationOverlay = null;
	private MyLocationOverlay myLocationOverlay;
	private MyLocationListener locationlistener;
	private LocationClient locClient;
	private LocationData locData;
	private BDLocation curr_location;

	private MyOverlayItem myOverlayItem;

	private enum E_BUTTON_TYPE {
		LOC, COMPASS, FOLLOW
	}

	private E_BUTTON_TYPE mCurBtnType;
	// 弹出泡泡图层
//	private PopupOverlay pop = null;// 弹出泡泡图层，浏览节点时使用
//	private TextView popupText = null;// 泡泡view
//	private View viewCache = null;

	// UI相关
	private Button requestLocButton = null;
	boolean isRequest = false;// 是否手动触发请求定位
	boolean isFirstLoc = true;// 是否首次定位

	private Button fushiButton = null;
	private Button screenButton = null;
	private Button settingButton = null;

	private MyHandler handler = null;
	private static final int CHANGED = 0x0010;
	private static final int CHANGED2 = 0x0020;
	private static final int CHANGED3 = 0x0030;
	private static final int CHANGED4 = 0x0040;

	private static final int UICHANGED = 00001;
	private static final int UICHANGED2 = 00002;
	private static final int UICHANGED3 = 00003;
	private static final int UICHANGED4 = 00004;
	private static final int UICHANGED5 = 00005;
	private static final int UICHANGED6 = 00006;

	private static final int UICHANGED1 = 00011;
	private static final int UICHANGED21 = 00012;
	private static final int UICHANGED31 = 00013;
	private static final int UICHANGED41 = 00014;
	private static final int UICHANGED51 = 00015;
	private static final int UICHANGED61 = 00016;

	public static final int REQUEST = 1;

	private MyApplication app = null;

	private long exitTime = 0;

	private static StringBuilder sb;
	
	private PoiOverLayTest poiOverlay ;
	
	private GeoPoint searchpoint = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		

		app = (MyApplication) this.getApplication();
		handler = new MyHandler();

		MapManager = new BMapManager(this.getApplication());
		MapManager.init("H6pXpqlNhDbFBpRExHTWsUnG", new MKGeneralListener() {

			@Override
			public void onGetNetworkState(int Error) {
				// TODO Auto-generated method stub
				if (Error == MKEvent.ERROR_NETWORK_CONNECT) {
					Toast.makeText(MainActivity.this.getApplicationContext(),
							"您的网络出现问题了！", Toast.LENGTH_LONG).show();
				}
			}

			@Override
			// 授权错误报告
			public void onGetPermissionState(int Error) {
				// TODO Auto-generated method stub
				if (Error == MKEvent.ERROR_PERMISSION_DENIED) {
					Toast.makeText(MainActivity.this.getApplicationContext(),
							"授权出现异常，请检查授权Key是否正确！", Toast.LENGTH_LONG).show();
				}
			}

		});

		// 调用setContentView前需初始化BMapManager,否则报错
		setContentView(R.layout.activity_main);
		
//		initGPS();
//		initGPSopen();
		

		requestLocButton = (Button) findViewById(R.id.myloc);
		mCurBtnType = E_BUTTON_TYPE.LOC;
		OnClickListener btnClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (mCurBtnType) {
				case LOC:
					// 手动定位请求
					requestLocClick();
					break;
				case COMPASS:
					myLocationOverlay.setLocationMode(LocationMode.NORMAL);
					requestLocButton.setBackgroundResource(R.drawable.myloc);
					// requestLocButton.setText("定位");
					mCurBtnType = E_BUTTON_TYPE.LOC;
					Toast.makeText(MainActivity.this, "已进入普通定位模式", Toast.LENGTH_SHORT).show();
					break;
				case FOLLOW:
					myLocationOverlay.setLocationMode(LocationMode.COMPASS);
					requestLocButton.setBackgroundResource(R.drawable.compass);
					// requestLocButton.setText("罗盘");
					mCurBtnType = E_BUTTON_TYPE.COMPASS;
					Toast.makeText(MainActivity.this, "已进入罗盘定位模式", Toast.LENGTH_SHORT).show();
					break;
				}

			}

		};
		requestLocButton.setOnClickListener(btnClickListener);

		// 通过ID获取代表地图显示组件的MapView对象
		mMapView = (MapView) findViewById(R.id.bmapsView);

		// 设置启用内置的缩放控件
		mMapView.setBuiltInZoomControls(true);

		// 设置地图相应点击事件
		mMapView.getController().enableClick(true);

		// 得到mMapView的控制权,可以用它控制和驱动平移和缩放
		mMapController = mMapView.getController();

		// 用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
		GeoPoint mypoint = new GeoPoint((int) (22.5981131 * 1E6),
				(int) (113.0873609 * 1E6));

		// mMapController.setCenter(mypoint);// 设置地图中心点

		mMapController.setZoom(15);// 设置地图zoom级别,范围[3.18]f

		// 设置指南针显示在左上角
		mMapController.setCompassMargin(50, 50);
		// mMapController.setCompassMargin(mMapView.getWidth() - 100, 100);

		// 显示实时交通信息
		// mMapView.setTraffic(true);

		// 卫星图
		// mMapView.setSatellite(true);

		/** 初始化地图事件监听 */
		initListener();

		// 创建 弹出泡泡图层
		// createPaopao();

		// jie

		// 定位初始化
		locClient = new LocationClient(this);
		locData = new LocationData();
		locationlistener = new MyLocationListener();
		locClient.registerLocationListener(locationlistener);
		LocationClientOption option = new LocationClientOption();
		// option.setOpenGps(true);//打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		locClient.setLocOption(option);
		locClient.start();

		// 定位图层初始化
		myLocationOverlay = new MymyLocationOverlay(mMapView);
		// 设置定位数据
		myLocationOverlay.setData(locData);
		// 添加定位图层
		mMapView.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableCompass();
		// 修改定位数据后刷新图层生效
		mMapView.refresh();

		// locData = new LocationData();
		// //
		// 手动将位置源置为天安门，在实际应用中，请使用百度定位SDK获取位置信息，要在SDK中显示一个位置，需要使用百度经纬度坐标（bd09ll）
		// locData.latitude = 39.945;
		// locData.longitude = 116.404;
		// locData.direction = 2.0f;
		//
		// myLocationOverlay = new locationOverlay(mMapView);
		// myLocationOverlay.setData(locData);
		// //myLocationOverlay.enableCompass();
		// mMapView.getOverlays().add(myLocationOverlay);
		//
		// mMapView.refresh();
		//
		// locationlistener = new MyLocationListener();
		// locClient = new LocationClient(this);
		// LocationClientOption options = new LocationClientOption();
		// options.setCoorType("bd09ll");// 返回的定位结果是百度经纬度，默认值gcj02
		// options.setScanSpan(1000);// 设置发起定位请求的间隔时间为5000ms
		// locClient.setLocOption(options);
		// locClient.registerLocationListener(locationlistener);
		// locClient.start();

		// 获取用作标记的图标
		Drawable drawable = this.getResources().getDrawable(R.drawable.mapmark);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		// 创建覆盖物对象并且添加到覆盖物列表中
//		double mlat1 = 22.603745;
//		double mlon1 = 113.093303;
//		GeoPoint geopoint1 = new GeoPoint((int) (mlat1 * 1e6),
//				(int) (mlon1 * 1e6));
//		myOverlayItem = new MyOverlayItem(drawable, mMapView);
//		OverlayItem item = new OverlayItem(geopoint1, "12", "123");
//		myOverlayItem.addItem(item);
		// mMapView.getOverlays().add(myOverlayItem);
		// mMapView.refresh();
		
		// 设置每页返回的POI数，默认为10，取值范围1-50  
//        MKSearch.setPoiPageCapacity(10);
      //每次搜索前先前sb中的内容清空  
//        sb = new StringBuilder();
		// 通过初始化MKSearch类,注册监听对象MKSearchListener
		MKSearch = new MKSearch();
		MKSearch.init(MapManager, new MKSearchListener() {

			@Override
			public void onGetAddrResult(MKAddrInfo result, int error) {
				// 返回地址信息搜索结果
//				 if(result == null){
//				 return;
//				 }
//				 StringBuffer sb = new StringBuffer();
//				 sb.append(result.strAddr).append("/n");
//				
//				 if (null != result.poiList){
//				 for(MKPoiInfo poiInfo : result.poiList){
//				 sb.append("-------------").append("/n");
//				 sb.append("名称：").append(poiInfo.name).append("/n");
//				 sb.append("地址：").append(poiInfo.address).append("/n");
//				 sb.append("经度：").append(poiInfo.pt.getLongitudeE6() /
//				 1000000.0f).append("/n");
//				 sb.append("纬度：").append(poiInfo.pt.getLatitudeE6() /
//				 1000000.0f).append("/n");
//				 sb.append("电话：").append(poiInfo.phoneNum).append("/n");
//				 sb.append("邮编：").append(poiInfo.postCode).append("/n");
//				 // poi类型，0：普通点，1：公交站，2：公交线路，3：地铁站，4：地铁线路
//				 sb.append("类型：").append(poiInfo.ePoiType).append("/n");
//				 }
//				 }
//				 Toast.makeText(MainActivity.this, "not1",
//				 Toast.LENGTH_SHORT).show();
//				 return;
			}

			@Override
			public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
				// 返回公交车详情信息搜索结果

			}

			@Override
			public void onGetDrivingRouteResult(MKDrivingRouteResult arg0,
					int arg1) {
				// 返回成家路线搜索结果

			}

			@Override
			public void onGetPoiResult(MKPoiResult result, int type, int error) {
				// 返回Poi搜索结果
				if (error != 0 || result == null) {
					Toast.makeText(MainActivity.this, "抱歉，尚未找到结果，换个条件试试？",
							Toast.LENGTH_SHORT).show();
					return;
				}

				
				if (result.getCurrentNumPois() > 0) {
					mMapView.getOverlays().remove(poiOverlay);
					 poiOverlay = new PoiOverLayTest(
							MainActivity.this, mMapView, MKSearch);
					ArrayList<MKPoiInfo> mkpois ;
					mkpois = new ArrayList<MKPoiInfo>();
					mkpois = result.getAllPoi();
					poiOverlay.setData(mkpois);
					mMapView.getOverlays().add(poiOverlay);
					mMapView.refresh();
					
					
				
					// 当PoiType未2（公交路线）或4（铁线路线）时，poi坐标为空
					for (MKPoiInfo mkPois : result.getAllPoi()) {
						if (mkPois.pt != null) {
							// 地图中心移动到搜索结果第一个的坐标点上
							mMapView.getController().animateTo(mkPois.pt);
						}
						
					}
				

				} else if (result.getCityListNum() > 0) {
					// 当本市没搜索结果时，但其他城市找到，返回包含该关键字信息的城市列表
					String strInfo = "在";
					for (int i = 0; i < result.getCityListNum(); i++) {
						strInfo += result.getCityListInfo(i).city;
						strInfo += ",";
					}
					strInfo += "搜索结果";
					Toast.makeText(MainActivity.this, strInfo,
							Toast.LENGTH_LONG).show();
				}

				// 创建POI内置的Overlay对象
				// PoiOverlay poiOverlay = new PoiOverlay(MainActivity.this,
				// mMapView);

				// 符合搜索条件的所有点
				// poiOverlay.setData(result.getAllPoi());

				// 往覆盖物列表中添加覆盖物对象PoiOverlay
				// mMapView.getOverlays().add(poiOverlay);

				// 刷新地图
				// mMapView.refresh();

				// 当执行完POI检索后，会得到一个POI的列表
				// ArrayList<MKPoiInfo> mkPois = result.getAllPoi();
				// 获取POI列表中的第二个元素
				// MKPoiInfo mkPoi = mkPois.get(1);

				/*
				 * 每个POI节点都有个uid属性，我们可以根据这个uid获取关于这个poi的一些更详细的信息。
				 * 比如：评论、图片、商户描述等。
				 */

				// 发起查看详细信息的请求 det
				// MKSearch.poiDetailSearch(mkPoi.uid);

			}

			@Override
			public void onGetPoiDetailSearchResult(int type, int error) {
				// 获取POI搜素到的详细信息
				// type-值未GeoSearchManagerGEO_SEARCH_DETAILS
				if (error != 0) {
					Toast.makeText(MainActivity.this, "未找到结果",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MainActivity.this, "详细信息搜索结果返回",
							Toast.LENGTH_SHORT).show();
				}

			}

			@Override
			public void onGetShareUrlResult(MKShareUrlResult arg0, int arg1,
					int arg2) {
				// 在此处理短串请求返回结果

			}

			// ListView mSuggestionList = (ListView)
			// findViewById(R.id.textview);
			@Override
			public void onGetSuggestionResult(MKSuggestionResult res, int error) {
				// 返回联想词信息搜索结果
				if (res == null || res.getAllSuggestions() == null) {
					return;
				}

			}

			@Override
			public void onGetTransitRouteResult(MKTransitRouteResult arg0,
					int arg1) {
				// 返回公交搜索结果

			}

			@Override
			public void onGetWalkingRouteResult(MKWalkingRouteResult arg0,
					int arg1) {
				// 返回步行路线搜索结果

			}
		});

		// 范围检索
		// 指在给定的一个矩形区域内，根据开发者设定的指定关键字，搜索兴趣点信息，
		// 所使用的方法为：
		// poiSearchInbounds(String key, GeoPoint ptLB, GeoPoint ptRT)；
		// 如要检索北京西站与北京北站为顶点所确定的距形区域内的KFC餐厅，使用以下代码发起检索：
		// 北京西站
		// GeoPoint ptLB = new GeoPoint( (int)(39.901375 *
		// 1E6),(int)(116.329099 * 1E6));
		// 北京北站
		// GeoPoint ptRT = new GeoPoint( (int)(39.949404 *
		// 1E6),(int)(116.360719 * 1E6));
		// mMKSearch.poiSearchInbounds("KFC", ptLB, ptRT);

		// 城市检索
		// 城市检索，即在某一城市内搜索兴趣点信息。所使用的方法是：poiSearchInCity(String city,
		// String key)；核心代码如下：

		// 如要检索江门的KFC餐厅，使用以下代码发起检索：
		// MKSearch.poiSearchInCity("江门", "KFC");

		// 周边检索
		// 周边检索指的是以指定坐标点为圆心，根据给定关键字查询一定半径范围内的全部兴趣点。使用方法：poiSearchNearBy(String
		// key, GeoPoint pt, int radius)；
		// 核心代码如下：
		// 检索天安门周边5000米之内的KFC餐厅：
		// mMKSearch.poiSearchNearBy("KFC", new GeoPoint((int)
		// (39.915 * 1E6), (int) (116.404 * 1E6)), 5000);

		// 根据中心点，半径和检索词发起周边检索
		// 参数列表：关键词，中心点地理坐标，半径，单位：米
		// MKSearch.poiSearchNearBy("餐厅", mypoint, 1500);
		
		
		 
		// 城市检索(城市名，搜索目标)
//		String city = "江门";
//		String key = "餐厅";
//		MKSearch.poiSearchInCity(city, key);

		// 在线建议查询:suggestionSearch(String key, String city)
		// MKSearch.suggestionSearch("快餐", "江门");

		// Intent intent = new Intent();
		// startActivityForResult(intent, REQUSET);

		
		myOverlayItem = new MyOverlayItem(drawable, mMapView);
		mMapView.getOverlays().add(myOverlayItem);
		mMapView.refresh();
		
		
		Button clear = (Button) findViewById(R.id.clear);
		clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mMapView.getOverlays().remove(poiOverlay);
				myOverlayItem.removeAll();
				markpoint = null;
				mMapView.refresh();
				
			}
		});
	}
	
	


//	@Override
//	public void onConfigurationChanged(Configuration newConfig) {
//		// TODO Auto-generated method stub
//		super.onConfigurationChanged(newConfig);
//	
//		if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
//			//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//			setContentView(R.layout.activity_main);
//		}
//		else if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
//			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//		}
//	}

	


	


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
		case RESULT_OK:
			int type = data.getExtras().getInt("type");
			if(type == 1){
			String city = data.getExtras().getString("intentcity");
			String key = data.getExtras().getString("intentkey");
			MKSearch.poiSearchInCity(city, key);
			
			}
			else if(type == 2){
				
				searchpoint = new GeoPoint(
						(int) (locData.latitude * 1e6),
						(int) (locData.longitude * 1e6));
				
				int ray = data.getExtras().getInt("strray");
				String key = data.getExtras().getString("intentkey");
				MKSearch.poiSearchNearBy(key, searchpoint, ray);
				mMapView.refresh();
			}
		}
	}

	/**
	 * 实现Handler处理消息更新UI
	 */
	class MyHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			if (msg.what == CHANGED) {
				mMapView.setTraffic(false);
				mMapView.setSatellite(true);
				mMapView.refresh();
			} else if (msg.what == CHANGED2) {
				mMapView.setTraffic(false);
				mMapView.setSatellite(false);
				mMapView.refresh();
			} else if (msg.what == CHANGED3) {
				mMapView.setTraffic(true);
				mMapView.setSatellite(true);
				mMapView.refresh();
			} else if (msg.what == CHANGED4) {
				mMapView.setTraffic(true);
				mMapView.setSatellite(false);
				mMapView.refresh();
			} else if (msg.what == UICHANGED) {
				mMapController.setZoomGesturesEnabled(false);
				mMapView.refresh();
			}

			else if (msg.what == UICHANGED1) {
				mMapController.setZoomGesturesEnabled(true);
				mMapView.refresh();
			}

			else if (msg.what == UICHANGED2) {
				mMapController.setScrollGesturesEnabled(false);
				mMapView.refresh();
			}

			else if (msg.what == UICHANGED21) {
				mMapController.setScrollGesturesEnabled(true);
				mMapView.refresh();
			}

			else if (msg.what == UICHANGED3) {
				mMapView.setDoubleClickZooming(false);
				mMapView.refresh();
			}

			else if (msg.what == UICHANGED31) {
				mMapView.setDoubleClickZooming(true);
				mMapView.refresh();
			}

			else if (msg.what == UICHANGED4) {
				mMapController.setRotationGesturesEnabled(false);
				mMapView.refresh();
			}

			else if (msg.what == UICHANGED41) {
				mMapController.setRotationGesturesEnabled(true);
				mMapView.refresh();
			}

			else if (msg.what == UICHANGED5) {
				mMapController.setOverlookingGesturesEnabled(false);
				mMapView.refresh();
			}

			else if (msg.what == UICHANGED51) {
				mMapController.setOverlookingGesturesEnabled(true);
				mMapView.refresh();
			}

			else if (msg.what == UICHANGED6) {
				mMapView.setBuiltInZoomControls(false);
				mMapView.refresh();
			}

			else if (msg.what == UICHANGED61) {
				mMapView.setBuiltInZoomControls(true);
				mMapView.refresh();
			}

		}

	}

	/**
	 * 手动触发一次定位请求
	 */
	public void requestLocClick() {
		isRequest = true;
		locClient.requestLocation();
		Toast.makeText(MainActivity.this, "定位中...", Toast.LENGTH_SHORT).show();
	}

	/**
	 * 创建弹出泡泡图层
	 */
	// public void createPaopao(){
	// viewCache = getLayoutInflater().inflate(R.layout.textview, null);
	// popupText =(TextView) viewCache.findViewById(R.id.textcache);
	// //泡泡点击响应回调
	// PopupClickListener popListener = new PopupClickListener(){
	//
	// @Override
	// public void onClickedPopup(int index) {
	// Log.v("click", "clickapoapo");
	// }
	// };
	// pop = new PopupOverlay(mMapView,popListener);
	// MyLocationMapView.pop = pop;
	// }

	private void initListener() {
		/**
		 * 设置地图点击事件监听
		 */
		mapTouchListener = new MKMapTouchListener() {

			@Override
			public void onMapClick(GeoPoint point) {
				touchType = "单击";
				currentPt = point;
				updateMapState();

			}

			@Override
			public void onMapDoubleClick(GeoPoint point) {
				touchType = "双击";
				currentPt = point;
				updateMapState();

			}

			@Override
			public void onMapLongClick(GeoPoint point) {
				touchType = "长按";
				currentPt = point;
				updateMapState();
				
				

			}

		};
		mMapView.regMapTouchListner(mapTouchListener);
		/**
		 * 设置地图事件监听
		 */
		mMapListener = new MKMapViewListener() {

			@Override
			public void onClickMapPoi(MapPoi arg0) {
				/**
				 * 在此处理底图poi点击事件 显示底图poi名称并移动至该点 设置过：
				 * mMapController.enableClick(true); 时，此回调才能被触发
				 * 
				 */

			}

			@Override
			public void onGetCurrentMap(Bitmap b) {
				/**
				 * 当调用过 mMapView.getCurrentMap()后，此回调会被触发 可在此保存截图至存储设备
				 */
				File file = new File("/mnt/sdcard/test.png");
				FileOutputStream out;
				try {
					out = new FileOutputStream(file);
					if (b.compress(Bitmap.CompressFormat.PNG, 70, out)) {
						out.flush();
						out.close();
					}
					Toast.makeText(MainActivity.this,
							"成功截屏，图片存放于：" + file.toString(), Toast.LENGTH_SHORT)
							.show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onMapAnimationFinish() {
				/**
				 * 地图完成带动画的操作（如: animationTo()）后，此回调被触发
				 */
				updateMapState();

			}

			@Override
			public void onMapLoadFinish() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onMapMoveFinish() {
				/**
				 * 在此处理地图移动完成回调 缩放，平移等操作完成后，此回调被触发
				 */
				updateMapState();

			}

		};
		mMapView.regMapViewListener(MapManager, mMapListener);

		/**
		 * 设置菜单键监听
		 * */
		settingButton = (Button) findViewById(R.id.setting);
		settingButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				app.setHandler(handler);
				Intent intent = new Intent(MainActivity.this, settinglist.class);
				startActivity(intent);

			}
		});

		/**
		 * 设置按键监听
		 */
		fushiButton = (Button) findViewById(R.id.fushi);
		screenButton = (Button) findViewById(R.id.screen);

		// fushiButton = (Button)findViewById(R.id.fushi);
		// screenButton =(Button)findViewById(R.id.screen);
		OnClickListener onClickListener = new OnClickListener() {

			@Override
			public void onClick(View view) {
				if (view.equals(fushiButton)) {
					perfomOverlook();
				} else if (view.equals(screenButton)) {
					mMapView.getCurrentMap();
					Toast.makeText(MainActivity.this, "正在截取屏幕...",
							Toast.LENGTH_SHORT).show();
				}
				updateMapState();
			}

		};
		fushiButton.setOnClickListener(onClickListener);
		screenButton.setOnClickListener(onClickListener);
	}

	/**
	 * 处理俯视 俯角范围： -45 ~ 0 , 单位： 度
	 */
	protected void perfomOverlook() {
		try {
			int overlookAngle = -30;
			mMapController.setOverlooking(overlookAngle);
		} catch (NumberFormatException e) {
			Toast.makeText(this, "请输入正确的俯角", Toast.LENGTH_SHORT).show();
		}
	}

	protected void updateMapState() {
		
		if(touchType == "长按"){
			Drawable drawable = this.getResources().getDrawable(R.drawable.mapmark);
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			
			// 创建覆盖物对象并且添加到覆盖物列表中
			double mlat1 = currentPt.getLatitudeE6()*1E-6;
			double mlon1 = currentPt.getLongitudeE6()*1E-6;
			markpoint = new GeoPoint((int)(mlat1*1e6),(int)(mlon1*1e6));
			
			OverlayItem item = new OverlayItem(markpoint, "", "");
			myOverlayItem.addItem(item);
			mMapView.refresh();
		}

		// if ( mStateBar == null){
		// return ;
		// }
		// String state = "";
		// if ( currentPt == null ){
		// state = "点击、长按、双击地图以获取经纬度和地图状态";
		// }
		// else{
		// state =
		// String.format(touchType+",当前经度 ： %f 当前纬度：%f",currentPt.getLongitudeE6()*1E-6,currentPt.getLatitudeE6()*1E-6);
		// }
		// state += "\n";
		// state +=
		// String.format("zoom level= %.1f    rotate angle= %d   overlaylook angle=  %d",
		// mMapView.getZoomLevel(),
		// mMapView.getMapRotation(),
		// mMapView.getMapOverlooking()
		// );
		// mStateBar.setText(state);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(MainActivity.this, CitySearch.class);
		// startActivity(intent);
		startActivityForResult(intent, REQUEST);
		return super.onOptionsItemSelected(item);

	}

	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// TODO Auto-generated method stub
			if (location == null)
				return;

			// GeoPoint mypoint = new
			// GeoPoint((int)(location.getLatitude()*1e6),
			// (int)(location.getLongitude()*1e6));
			// mMapView.getController().animateTo(mypoint);

			// mMapView.getController().animateTo(
			// new GeoPoint ((int) (location.getLatitude() * 1E6),
			// (int) (location.getLongitude() * 1E6)));

			// curr_location = location;
			locData.latitude = location.getLatitude();
			locData.longitude = location.getLongitude();
			addr = location.getAddrStr();
			// 精确度圈，取消将accuracy赋值为0即可
			locData.accuracy = location.getRadius();
			// 此处可以设置 locData的方向信息, 如果定位 SDK 未返回方向信息，用户可以自己实现罗盘功能添加方向信息。
			locData.direction = location.getDerect();
			myLocationOverlay.setData(locData);
			mMapView.refresh();
			// 是手动触发请求或首次定位时，移动到定位点
			if (isRequest || isFirstLoc) {
				// 移动地图到定位点
				Log.d("LocationOverlay", "receive location, animate to it");
				mMapController.animateTo(new GeoPoint(
						(int) (locData.latitude * 1e6),
						(int) (locData.longitude * 1e6)));
				isRequest = false;
				myLocationOverlay.setLocationMode(LocationMode.FOLLOWING);
				requestLocButton.setBackgroundResource(R.drawable.walking);
				// requestLocButton.setText("跟随");
				mCurBtnType = E_BUTTON_TYPE.FOLLOW;
				Toast.makeText(MainActivity.this, "已进入跟随定位模式", Toast.LENGTH_SHORT).show();
			}

			// 首次定位完成
			isFirstLoc = false;
		}

		@Override
		public void onReceivePoi(BDLocation poiLocation) {
			if (poiLocation == null) {
				return;
			}

		}

	}
	
	class MymyLocationOverlay extends MyLocationOverlay{

	public MymyLocationOverlay(MapView mMapView) {
			super(mMapView);
			// TODO Auto-generated constructor stub
			
			
		}

	@Override
	protected boolean dispatchTap() {
		// TODO Auto-generated method stub
		if(addr == null){
			addr ="尚未有目前坐标地址信息";
		}
		Toast.makeText(MainActivity.this, "经度:"+locData.longitude * 1e6+"\n"
				+"纬度"+locData.latitude * 1e6+"\n"
				+"地址："+addr, Toast.LENGTH_LONG).show();
		return super.dispatchTap();
	}
	}

	// 继承MyLocationOverlay重写dispatchTap实现点击处理
	// public class locationOverlay extends MyLocationOverlay{
	//
	// public locationOverlay(MapView mMapView) {
	// super(mMapView);
	// // TODO Auto-generated constructor stub
	// }
	// protected boolean dispatchTap() {
	// //处理点击事件,弹出泡泡
	// popupText.setBackgroundResource(R.drawable.popup);
	// popupText.setText("我的位置");
	// pop.showPopup(BMapUtil.getBitmapFromView(popupText),
	// new GeoPoint((int)(locData.latitude*1e6), (int)(locData.longitude*1e6)),
	// 8);
	// return true;
	//
	// }
	// }

	class MyOverlayItem extends ItemizedOverlay<OverlayItem> {
		public MyOverlayItem(Drawable mapmark, MapView mMapView) {
			super(mapmark, mMapView);
		}

		protected boolean onTap(int i) {
//			double mlat1 = currentPt.getLatitudeE6()*1E-6;
//			double mlon1 = currentPt.getLongitudeE6()*1E-6;
//			markpoint = new GeoPoint((int)(mlat1*1e6),(int)(mlon1*1e6));
			
			Toast.makeText(MainActivity.this.getApplicationContext(),"经度：" +markpoint.getLongitudeE6()*1E-6+"\n"
					+"纬度："+markpoint.getLatitudeE6()*1E-6,
					Toast.LENGTH_LONG).show();
			return true;
		}

		public boolean onTap(GeoPoint geopoint1, MapView mMapView) {
			// 在此处理MapView的点击事件，当返回 true时
			super.onTap(geopoint1, mMapView);
			return false;
		}
	}

	class PoiOverLayTest extends PoiOverlay {
		MKSearch MKSearch;
		
		public PoiOverLayTest(Activity MainActivity, MapView mMapView,
				MKSearch search) {
			super(MainActivity, mMapView);
			MKSearch = search;
		}
		
		
		@Override
		protected boolean onTap(int i) {
			super.onTap(i);
			MKPoiInfo mkPoi = getPoi(i);	
			if (mkPoi.hasCaterDetails) {
				// MKSearch.poiDetailSearch(info.uid);
				MKSearch.poiDetailSearch(mkPoi.uid);
			}
			return true;
			
		}
	
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	// 重写Activity的生命周期回调方法onResume()、onPause()和onDestroy()，
	// 管理地图引擎管理类对象和显示对象生命周期
	@Override
	protected void onResume() {
		mMapView.onResume();
		if (MapManager != null) {
			MapManager.start();
		}
		super.onResume();
	}

	protected void onPause() {
		mMapView.onPause();
		if (MapManager != null) {
			// 这里不注释的话，公交搜索按钮无效，待究
			// MapManager.stop();
		}
		super.onPause();
	}

	protected void onDestroy() {
		mMapView.destroy();
		if (MapManager != null) {
			MapManager.destroy();
			MapManager = null;
		}
		super.onDestroy();
		// System.exit(0);
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mMapView.onSaveInstanceState(outState);

	}

	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mMapView.onRestoreInstanceState(savedInstanceState);
	}
}
/**
 * 继承MapView重写onTouchEvent实现泡泡处理操作
 */
// class MyLocationMapView extends MapView{
// static PopupOverlay pop = null;//弹出泡泡图层，点击图标使用
// public MyLocationMapView(Context context) {
// super(context);
// // TODO Auto-generated constructor stub
// }
// public MyLocationMapView(Context context, AttributeSet attrs){
// super(context,attrs);
// }
// public MyLocationMapView(Context context, AttributeSet attrs, int defStyle){
// super(context, attrs, defStyle);
// }
// public boolean onTouchEvent(MotionEvent event){
// if (!super.onTouchEvent(event)){
// //消隐泡泡
// if (pop != null && event.getAction() == MotionEvent.ACTION_UP)
// pop.hidePop();
// }
// return true;
// }
// }
