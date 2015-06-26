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
	
	

	// �������
	/** ��ͼ��������� */
	public static BMapManager MapManager = null;

	/** ��ʾ��ͼ��View */
	// MyLocationMapView mMapView = null;
	MapView mMapView = null;

	/** ��ͼ������ */
	private MapController mMapController = null;

	/** ������������λ�ü������ܱ߼�������Χ���� */
	private MKSearch MKSearch = null;

	/** MKMapViewListener ���ڴ����ͼ�¼��ص� */
	MKMapViewListener mMapListener = null;

	/** ���ڽػ������� */
	MKMapTouchListener mapTouchListener = null;

	/** ��ǰ�ص���� */
	private GeoPoint currentPt = null;
	private GeoPoint mypoint = null;

	private String touchType = null;
	
	GeoPoint markpoint = null;
	private String addr = "";

	// ��λͼ��
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
	// ��������ͼ��
//	private PopupOverlay pop = null;// ��������ͼ�㣬����ڵ�ʱʹ��
//	private TextView popupText = null;// ����view
//	private View viewCache = null;

	// UI���
	private Button requestLocButton = null;
	boolean isRequest = false;// �Ƿ��ֶ���������λ
	boolean isFirstLoc = true;// �Ƿ��״ζ�λ

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
							"����������������ˣ�", Toast.LENGTH_LONG).show();
				}
			}

			@Override
			// ��Ȩ���󱨸�
			public void onGetPermissionState(int Error) {
				// TODO Auto-generated method stub
				if (Error == MKEvent.ERROR_PERMISSION_DENIED) {
					Toast.makeText(MainActivity.this.getApplicationContext(),
							"��Ȩ�����쳣��������ȨKey�Ƿ���ȷ��", Toast.LENGTH_LONG).show();
				}
			}

		});

		// ����setContentViewǰ���ʼ��BMapManager,���򱨴�
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
					// �ֶ���λ����
					requestLocClick();
					break;
				case COMPASS:
					myLocationOverlay.setLocationMode(LocationMode.NORMAL);
					requestLocButton.setBackgroundResource(R.drawable.myloc);
					// requestLocButton.setText("��λ");
					mCurBtnType = E_BUTTON_TYPE.LOC;
					Toast.makeText(MainActivity.this, "�ѽ�����ͨ��λģʽ", Toast.LENGTH_SHORT).show();
					break;
				case FOLLOW:
					myLocationOverlay.setLocationMode(LocationMode.COMPASS);
					requestLocButton.setBackgroundResource(R.drawable.compass);
					// requestLocButton.setText("����");
					mCurBtnType = E_BUTTON_TYPE.COMPASS;
					Toast.makeText(MainActivity.this, "�ѽ������̶�λģʽ", Toast.LENGTH_SHORT).show();
					break;
				}

			}

		};
		requestLocButton.setOnClickListener(btnClickListener);

		// ͨ��ID��ȡ�����ͼ��ʾ�����MapView����
		mMapView = (MapView) findViewById(R.id.bmapsView);

		// �����������õ����ſؼ�
		mMapView.setBuiltInZoomControls(true);

		// ���õ�ͼ��Ӧ����¼�
		mMapView.getController().enableClick(true);

		// �õ�mMapView�Ŀ���Ȩ,�����������ƺ�����ƽ�ƺ�����
		mMapController = mMapView.getController();

		// �ø����ľ�γ�ȹ���һ��GeoPoint����λ��΢�� (�� * 1E6)
		GeoPoint mypoint = new GeoPoint((int) (22.5981131 * 1E6),
				(int) (113.0873609 * 1E6));

		// mMapController.setCenter(mypoint);// ���õ�ͼ���ĵ�

		mMapController.setZoom(15);// ���õ�ͼzoom����,��Χ[3.18]f

		// ����ָ������ʾ�����Ͻ�
		mMapController.setCompassMargin(50, 50);
		// mMapController.setCompassMargin(mMapView.getWidth() - 100, 100);

		// ��ʾʵʱ��ͨ��Ϣ
		// mMapView.setTraffic(true);

		// ����ͼ
		// mMapView.setSatellite(true);

		/** ��ʼ����ͼ�¼����� */
		initListener();

		// ���� ��������ͼ��
		// createPaopao();

		// jie

		// ��λ��ʼ��
		locClient = new LocationClient(this);
		locData = new LocationData();
		locationlistener = new MyLocationListener();
		locClient.registerLocationListener(locationlistener);
		LocationClientOption option = new LocationClientOption();
		// option.setOpenGps(true);//��gps
		option.setCoorType("bd09ll"); // ������������
		option.setScanSpan(1000);
		locClient.setLocOption(option);
		locClient.start();

		// ��λͼ���ʼ��
		myLocationOverlay = new MymyLocationOverlay(mMapView);
		// ���ö�λ����
		myLocationOverlay.setData(locData);
		// ��Ӷ�λͼ��
		mMapView.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableCompass();
		// �޸Ķ�λ���ݺ�ˢ��ͼ����Ч
		mMapView.refresh();

		// locData = new LocationData();
		// //
		// �ֶ���λ��Դ��Ϊ�찲�ţ���ʵ��Ӧ���У���ʹ�ðٶȶ�λSDK��ȡλ����Ϣ��Ҫ��SDK����ʾһ��λ�ã���Ҫʹ�ðٶȾ�γ�����꣨bd09ll��
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
		// options.setCoorType("bd09ll");// ���صĶ�λ����ǰٶȾ�γ�ȣ�Ĭ��ֵgcj02
		// options.setScanSpan(1000);// ���÷���λ����ļ��ʱ��Ϊ5000ms
		// locClient.setLocOption(options);
		// locClient.registerLocationListener(locationlistener);
		// locClient.start();

		// ��ȡ������ǵ�ͼ��
		Drawable drawable = this.getResources().getDrawable(R.drawable.mapmark);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		// �����������������ӵ��������б���
//		double mlat1 = 22.603745;
//		double mlon1 = 113.093303;
//		GeoPoint geopoint1 = new GeoPoint((int) (mlat1 * 1e6),
//				(int) (mlon1 * 1e6));
//		myOverlayItem = new MyOverlayItem(drawable, mMapView);
//		OverlayItem item = new OverlayItem(geopoint1, "12", "123");
//		myOverlayItem.addItem(item);
		// mMapView.getOverlays().add(myOverlayItem);
		// mMapView.refresh();
		
		// ����ÿҳ���ص�POI����Ĭ��Ϊ10��ȡֵ��Χ1-50  
//        MKSearch.setPoiPageCapacity(10);
      //ÿ������ǰ��ǰsb�е��������  
//        sb = new StringBuilder();
		// ͨ����ʼ��MKSearch��,ע���������MKSearchListener
		MKSearch = new MKSearch();
		MKSearch.init(MapManager, new MKSearchListener() {

			@Override
			public void onGetAddrResult(MKAddrInfo result, int error) {
				// ���ص�ַ��Ϣ�������
//				 if(result == null){
//				 return;
//				 }
//				 StringBuffer sb = new StringBuffer();
//				 sb.append(result.strAddr).append("/n");
//				
//				 if (null != result.poiList){
//				 for(MKPoiInfo poiInfo : result.poiList){
//				 sb.append("-------------").append("/n");
//				 sb.append("���ƣ�").append(poiInfo.name).append("/n");
//				 sb.append("��ַ��").append(poiInfo.address).append("/n");
//				 sb.append("���ȣ�").append(poiInfo.pt.getLongitudeE6() /
//				 1000000.0f).append("/n");
//				 sb.append("γ�ȣ�").append(poiInfo.pt.getLatitudeE6() /
//				 1000000.0f).append("/n");
//				 sb.append("�绰��").append(poiInfo.phoneNum).append("/n");
//				 sb.append("�ʱࣺ").append(poiInfo.postCode).append("/n");
//				 // poi���ͣ�0����ͨ�㣬1������վ��2��������·��3������վ��4��������·
//				 sb.append("���ͣ�").append(poiInfo.ePoiType).append("/n");
//				 }
//				 }
//				 Toast.makeText(MainActivity.this, "not1",
//				 Toast.LENGTH_SHORT).show();
//				 return;
			}

			@Override
			public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
				// ���ع�����������Ϣ�������

			}

			@Override
			public void onGetDrivingRouteResult(MKDrivingRouteResult arg0,
					int arg1) {
				// ���سɼ�·���������

			}

			@Override
			public void onGetPoiResult(MKPoiResult result, int type, int error) {
				// ����Poi�������
				if (error != 0 || result == null) {
					Toast.makeText(MainActivity.this, "��Ǹ����δ�ҵ�����������������ԣ�",
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
					
					
				
					// ��PoiTypeδ2������·�ߣ���4������·�ߣ�ʱ��poi����Ϊ��
					for (MKPoiInfo mkPois : result.getAllPoi()) {
						if (mkPois.pt != null) {
							// ��ͼ�����ƶ������������һ�����������
							mMapView.getController().animateTo(mkPois.pt);
						}
						
					}
				

				} else if (result.getCityListNum() > 0) {
					// ������û�������ʱ�������������ҵ������ذ����ùؼ�����Ϣ�ĳ����б�
					String strInfo = "��";
					for (int i = 0; i < result.getCityListNum(); i++) {
						strInfo += result.getCityListInfo(i).city;
						strInfo += ",";
					}
					strInfo += "�������";
					Toast.makeText(MainActivity.this, strInfo,
							Toast.LENGTH_LONG).show();
				}

				// ����POI���õ�Overlay����
				// PoiOverlay poiOverlay = new PoiOverlay(MainActivity.this,
				// mMapView);

				// �����������������е�
				// poiOverlay.setData(result.getAllPoi());

				// ���������б�����Ӹ��������PoiOverlay
				// mMapView.getOverlays().add(poiOverlay);

				// ˢ�µ�ͼ
				// mMapView.refresh();

				// ��ִ����POI�����󣬻�õ�һ��POI���б�
				// ArrayList<MKPoiInfo> mkPois = result.getAllPoi();
				// ��ȡPOI�б��еĵڶ���Ԫ��
				// MKPoiInfo mkPoi = mkPois.get(1);

				/*
				 * ÿ��POI�ڵ㶼�и�uid���ԣ����ǿ��Ը������uid��ȡ�������poi��һЩ����ϸ����Ϣ��
				 * ���磺���ۡ�ͼƬ���̻������ȡ�
				 */

				// ����鿴��ϸ��Ϣ������ det
				// MKSearch.poiDetailSearch(mkPoi.uid);

			}

			@Override
			public void onGetPoiDetailSearchResult(int type, int error) {
				// ��ȡPOI���ص�����ϸ��Ϣ
				// type-ֵδGeoSearchManagerGEO_SEARCH_DETAILS
				if (error != 0) {
					Toast.makeText(MainActivity.this, "δ�ҵ����",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MainActivity.this, "��ϸ��Ϣ�����������",
							Toast.LENGTH_SHORT).show();
				}

			}

			@Override
			public void onGetShareUrlResult(MKShareUrlResult arg0, int arg1,
					int arg2) {
				// �ڴ˴���̴����󷵻ؽ��

			}

			// ListView mSuggestionList = (ListView)
			// findViewById(R.id.textview);
			@Override
			public void onGetSuggestionResult(MKSuggestionResult res, int error) {
				// �����������Ϣ�������
				if (res == null || res.getAllSuggestions() == null) {
					return;
				}

			}

			@Override
			public void onGetTransitRouteResult(MKTransitRouteResult arg0,
					int arg1) {
				// ���ع����������

			}

			@Override
			public void onGetWalkingRouteResult(MKWalkingRouteResult arg0,
					int arg1) {
				// ���ز���·���������

			}
		});

		// ��Χ����
		// ָ�ڸ�����һ�����������ڣ����ݿ������趨��ָ���ؼ��֣�������Ȥ����Ϣ��
		// ��ʹ�õķ���Ϊ��
		// poiSearchInbounds(String key, GeoPoint ptLB, GeoPoint ptRT)��
		// ��Ҫ����������վ�뱱����վΪ������ȷ���ľ��������ڵ�KFC������ʹ�����´��뷢�������
		// ������վ
		// GeoPoint ptLB = new GeoPoint( (int)(39.901375 *
		// 1E6),(int)(116.329099 * 1E6));
		// ������վ
		// GeoPoint ptRT = new GeoPoint( (int)(39.949404 *
		// 1E6),(int)(116.360719 * 1E6));
		// mMKSearch.poiSearchInbounds("KFC", ptLB, ptRT);

		// ���м���
		// ���м���������ĳһ������������Ȥ����Ϣ����ʹ�õķ����ǣ�poiSearchInCity(String city,
		// String key)�����Ĵ������£�

		// ��Ҫ�������ŵ�KFC������ʹ�����´��뷢�������
		// MKSearch.poiSearchInCity("����", "KFC");

		// �ܱ߼���
		// �ܱ߼���ָ������ָ�������ΪԲ�ģ����ݸ����ؼ��ֲ�ѯһ���뾶��Χ�ڵ�ȫ����Ȥ�㡣ʹ�÷�����poiSearchNearBy(String
		// key, GeoPoint pt, int radius)��
		// ���Ĵ������£�
		// �����찲���ܱ�5000��֮�ڵ�KFC������
		// mMKSearch.poiSearchNearBy("KFC", new GeoPoint((int)
		// (39.915 * 1E6), (int) (116.404 * 1E6)), 5000);

		// �������ĵ㣬�뾶�ͼ����ʷ����ܱ߼���
		// �����б��ؼ��ʣ����ĵ�������꣬�뾶����λ����
		// MKSearch.poiSearchNearBy("����", mypoint, 1500);
		
		
		 
		// ���м���(������������Ŀ��)
//		String city = "����";
//		String key = "����";
//		MKSearch.poiSearchInCity(city, key);

		// ���߽����ѯ:suggestionSearch(String key, String city)
		// MKSearch.suggestionSearch("���", "����");

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
	 * ʵ��Handler������Ϣ����UI
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
	 * �ֶ�����һ�ζ�λ����
	 */
	public void requestLocClick() {
		isRequest = true;
		locClient.requestLocation();
		Toast.makeText(MainActivity.this, "��λ��...", Toast.LENGTH_SHORT).show();
	}

	/**
	 * ������������ͼ��
	 */
	// public void createPaopao(){
	// viewCache = getLayoutInflater().inflate(R.layout.textview, null);
	// popupText =(TextView) viewCache.findViewById(R.id.textcache);
	// //���ݵ����Ӧ�ص�
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
		 * ���õ�ͼ����¼�����
		 */
		mapTouchListener = new MKMapTouchListener() {

			@Override
			public void onMapClick(GeoPoint point) {
				touchType = "����";
				currentPt = point;
				updateMapState();

			}

			@Override
			public void onMapDoubleClick(GeoPoint point) {
				touchType = "˫��";
				currentPt = point;
				updateMapState();

			}

			@Override
			public void onMapLongClick(GeoPoint point) {
				touchType = "����";
				currentPt = point;
				updateMapState();
				
				

			}

		};
		mMapView.regMapTouchListner(mapTouchListener);
		/**
		 * ���õ�ͼ�¼�����
		 */
		mMapListener = new MKMapViewListener() {

			@Override
			public void onClickMapPoi(MapPoi arg0) {
				/**
				 * �ڴ˴����ͼpoi����¼� ��ʾ��ͼpoi���Ʋ��ƶ����õ� ���ù���
				 * mMapController.enableClick(true); ʱ���˻ص����ܱ�����
				 * 
				 */

			}

			@Override
			public void onGetCurrentMap(Bitmap b) {
				/**
				 * �����ù� mMapView.getCurrentMap()�󣬴˻ص��ᱻ���� ���ڴ˱����ͼ���洢�豸
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
							"�ɹ�������ͼƬ����ڣ�" + file.toString(), Toast.LENGTH_SHORT)
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
				 * ��ͼ��ɴ������Ĳ�������: animationTo()���󣬴˻ص�������
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
				 * �ڴ˴����ͼ�ƶ���ɻص� ���ţ�ƽ�ƵȲ�����ɺ󣬴˻ص�������
				 */
				updateMapState();

			}

		};
		mMapView.regMapViewListener(MapManager, mMapListener);

		/**
		 * ���ò˵�������
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
		 * ���ð�������
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
					Toast.makeText(MainActivity.this, "���ڽ�ȡ��Ļ...",
							Toast.LENGTH_SHORT).show();
				}
				updateMapState();
			}

		};
		fushiButton.setOnClickListener(onClickListener);
		screenButton.setOnClickListener(onClickListener);
	}

	/**
	 * ������ ���Ƿ�Χ�� -45 ~ 0 , ��λ�� ��
	 */
	protected void perfomOverlook() {
		try {
			int overlookAngle = -30;
			mMapController.setOverlooking(overlookAngle);
		} catch (NumberFormatException e) {
			Toast.makeText(this, "��������ȷ�ĸ���", Toast.LENGTH_SHORT).show();
		}
	}

	protected void updateMapState() {
		
		if(touchType == "����"){
			Drawable drawable = this.getResources().getDrawable(R.drawable.mapmark);
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			
			// �����������������ӵ��������б���
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
		// state = "�����������˫����ͼ�Ի�ȡ��γ�Ⱥ͵�ͼ״̬";
		// }
		// else{
		// state =
		// String.format(touchType+",��ǰ���� �� %f ��ǰγ�ȣ�%f",currentPt.getLongitudeE6()*1E-6,currentPt.getLatitudeE6()*1E-6);
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
			// ��ȷ��Ȧ��ȡ����accuracy��ֵΪ0����
			locData.accuracy = location.getRadius();
			// �˴��������� locData�ķ�����Ϣ, �����λ SDK δ���ط�����Ϣ���û������Լ�ʵ�����̹�����ӷ�����Ϣ��
			locData.direction = location.getDerect();
			myLocationOverlay.setData(locData);
			mMapView.refresh();
			// ���ֶ�����������״ζ�λʱ���ƶ�����λ��
			if (isRequest || isFirstLoc) {
				// �ƶ���ͼ����λ��
				Log.d("LocationOverlay", "receive location, animate to it");
				mMapController.animateTo(new GeoPoint(
						(int) (locData.latitude * 1e6),
						(int) (locData.longitude * 1e6)));
				isRequest = false;
				myLocationOverlay.setLocationMode(LocationMode.FOLLOWING);
				requestLocButton.setBackgroundResource(R.drawable.walking);
				// requestLocButton.setText("����");
				mCurBtnType = E_BUTTON_TYPE.FOLLOW;
				Toast.makeText(MainActivity.this, "�ѽ�����涨λģʽ", Toast.LENGTH_SHORT).show();
			}

			// �״ζ�λ���
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
			addr ="��δ��Ŀǰ�����ַ��Ϣ";
		}
		Toast.makeText(MainActivity.this, "����:"+locData.longitude * 1e6+"\n"
				+"γ��"+locData.latitude * 1e6+"\n"
				+"��ַ��"+addr, Toast.LENGTH_LONG).show();
		return super.dispatchTap();
	}
	}

	// �̳�MyLocationOverlay��дdispatchTapʵ�ֵ������
	// public class locationOverlay extends MyLocationOverlay{
	//
	// public locationOverlay(MapView mMapView) {
	// super(mMapView);
	// // TODO Auto-generated constructor stub
	// }
	// protected boolean dispatchTap() {
	// //�������¼�,��������
	// popupText.setBackgroundResource(R.drawable.popup);
	// popupText.setText("�ҵ�λ��");
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
			
			Toast.makeText(MainActivity.this.getApplicationContext(),"���ȣ�" +markpoint.getLongitudeE6()*1E-6+"\n"
					+"γ�ȣ�"+markpoint.getLatitudeE6()*1E-6,
					Toast.LENGTH_LONG).show();
			return true;
		}

		public boolean onTap(GeoPoint geopoint1, MapView mMapView) {
			// �ڴ˴���MapView�ĵ���¼��������� trueʱ
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
				Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�����",
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

	// ��дActivity���������ڻص�����onResume()��onPause()��onDestroy()��
	// �����ͼ���������������ʾ������������
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
			// ���ﲻע�͵Ļ�������������ť��Ч������
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
 * �̳�MapView��дonTouchEventʵ�����ݴ������
 */
// class MyLocationMapView extends MapView{
// static PopupOverlay pop = null;//��������ͼ�㣬���ͼ��ʹ��
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
// //��������
// if (pop != null && event.getAction() == MotionEvent.ACTION_UP)
// pop.hidePop();
// }
// return true;
// }
// }
