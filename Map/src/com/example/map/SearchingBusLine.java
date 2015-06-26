package com.example.map;

import java.util.ArrayList;
import java.util.List;


import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MKMapTouchListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.PopupClickListener;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.mapapi.map.RouteOverlay;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKRoute;
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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchingBusLine extends Activity {
	// UI���
	Button star = null;
	Button nextline = null;
	// ���·�߽ڵ����
	Button mBtnPre = null;// ��һ���ڵ�
	Button mBtnNext = null;// ��һ���ڵ�
	int nodeIndex = -2;// �ڵ�����,������ڵ�ʱʹ��
	MKRoute route = null;// ����ݳ�/����·�����ݵı�����������ڵ�ʱʹ��
	private PopupOverlay pop = null;// ��������ͼ�㣬����ڵ�ʱʹ��
	private TextView popupText = null;// ����view
	private View viewCache = null;
	private List<String> busLineIDList = null;
	int busLineIndex = 0;
	private RouteOverlay routeOverlay;

	// ��ͼ��أ�ʹ�ü̳�MapView��MyBusLineMapViewĿ������дtouch�¼�ʵ�����ݴ���
	// ���������touch�¼���������̳У�ֱ��ʹ��MapView����
	MapView mMapView = null; // ��ͼView
	// �������
	MKSearch mSearch = null; // ����ģ�飬Ҳ��ȥ����ͼģ�����ʹ��

	// ��λ���
	private MyLocationOverlay myLocationOverlay;
	private MyLocationListener locationlistener;
	private LocationClient locClient;
	private LocationData locData;

	boolean isFirstLoc = true;// �Ƿ��״ζ�λ

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		MyApplication app = (MyApplication) this.getApplication();

		setContentView(R.layout.searchingbusline);
		CharSequence titleLable = "���׹�����·��ѯ����";
		setTitle(titleLable);

		// ��ͼ��ʼ��
		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapView.getController().enableClick(true);
		mMapView.getController().setZoom(15);
		busLineIDList = new ArrayList<String>();

		locData = new LocationData();
		// �ֶ���λ��Դ��Ϊ�찲�ţ���ʵ��Ӧ���У���ʹ�ðٶȶ�λSDK��ȡλ����Ϣ��Ҫ��SDK����ʾһ��λ�ã���Ҫʹ�ðٶȾ�γ�����꣨bd09ll��
		locData.latitude = 39.945;
		locData.longitude = 116.404;
		locData.direction = 2.0f;

		myLocationOverlay = new MyLocationOverlay(mMapView);
		myLocationOverlay.setData(locData);
		// myLocationOverlay.enableCompass();
		mMapView.getOverlays().add(myLocationOverlay);

		mMapView.refresh();

		locationlistener = new MyLocationListener();
		locClient = new LocationClient(this);
		LocationClientOption options = new LocationClientOption();
		options.setCoorType("bd09ll");// ���صĶ�λ����ǰٶȾ�γ�ȣ�Ĭ��ֵgcj02
		options.setScanSpan(1000);// ���÷���λ����ļ��ʱ��Ϊ5000ms
		locClient.setLocOption(options);
		locClient.registerLocationListener(locationlistener);
		locClient.start();

		// ���� ��������ͼ��
		createPaopao();

		// ��ͼ����¼�����
		mMapView.regMapTouchListner(new MKMapTouchListener() {

			@Override
			public void onMapClick(GeoPoint point) {
				// �ڴ˴����ͼ����¼�
				// ����pop
				if (pop != null) {
					pop.hidePop();
				}

			}

			@Override
			public void onMapDoubleClick(GeoPoint point) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onMapLongClick(GeoPoint point) {
				// TODO Auto-generated method stub

			}

		});

		// ��ʼ������ģ�飬ע���¼�����
		mSearch = new MKSearch();
		mSearch.init(app.mBMapManager, new MKSearchListener() {

			@Override
			public void onGetAddrResult(MKAddrInfo res, int error) {
				// TODO Auto-generated method stub

			}

			/**
			 * ��ȡ����·�߽����չʾ������·
			 */
			@Override
			public void onGetBusDetailResult(MKBusLineResult result, int ierror) {
				if (ierror != 0 || result == null) {
					Toast.makeText(SearchingBusLine.this, "��Ǹ��δ�ҵ����",
							Toast.LENGTH_LONG).show();
					return;
				}
				routeOverlay = new RouteOverlay(SearchingBusLine.this, mMapView);
				// �˴���չʾһ��������Ϊʾ��
				routeOverlay.setData(result.getBusRoute());
				// �������ͼ��
				// mMapView.getOverlays().clear();
				// ���·��ͼ��
				mMapView.getOverlays().add(routeOverlay);
				// ˢ�µ�ͼʹ��Ч
				mMapView.refresh();
				// �ƶ���ͼ�����
				mMapView.getController().animateTo(
						result.getBusRoute().getStart());
				// ��·�����ݱ����ȫ�ֱ���
				route = result.getBusRoute();
				// ����·�߽ڵ��������ڵ����ʱʹ��
				nodeIndex = -1;
				mBtnPre.setVisibility(View.VISIBLE);
				mBtnNext.setVisibility(View.VISIBLE);
				Toast.makeText(SearchingBusLine.this, result.getBusName(),
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onGetDrivingRouteResult(MKDrivingRouteResult res,
					int error) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onGetPoiDetailSearchResult(int res, int error) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onGetPoiResult(MKPoiResult res, int type, int error) {
				if (error != 0 || res == null) {
					Toast.makeText(SearchingBusLine.this, "δ�ҵ����",
							Toast.LENGTH_LONG).show();
					return;
				}
				// �ҵ�����·��poi node
				MKPoiInfo curPoi = null;
				int totalPoiNum = res.getCurrentNumPois();
				// ��������poi���ҵ�����Ϊ������·��poi
				busLineIDList.clear();
				for (int idx = 0; idx < totalPoiNum; idx++) {
					if (2 == res.getPoi(idx).ePoiType) {
						// poi���ͣ�0����ͨ�㣬1������վ��2��������·��3������վ��4��������·
						curPoi = res.getPoi(idx);
						// ʹ��poi��uid���𹫽��������
						busLineIDList.add(curPoi.uid);
						System.out.println(curPoi.uid);
					}
				}
				SearchNextBusline();

				// û���ҵ�������Ϣ
				if (curPoi == null) {
					Toast.makeText(SearchingBusLine.this, "��Ǹ��δ�ҵ����",
							Toast.LENGTH_LONG).show();
				}
				route = null;
			}

			@Override
			public void onGetShareUrlResult(MKShareUrlResult arg0, int arg1,
					int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onGetTransitRouteResult(MKTransitRouteResult res,
					int error) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onGetWalkingRouteResult(MKWalkingRouteResult res,
					int error) {

			}
			// TODO Auto-generated method stub

		});
		// �趨������ť����Ӧ
		star = (Button) findViewById(R.id.star);
		nextline = (Button) findViewById(R.id.nextline);
		mBtnPre = (Button) findViewById(R.id.pre);
		mBtnNext = (Button) findViewById(R.id.next);
		mBtnPre.setVisibility(View.INVISIBLE);
		mBtnNext.setVisibility(View.INVISIBLE);

		OnClickListener clickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��������
				isFirstLoc = false;
				mMapView.getOverlays().remove(routeOverlay);
				SearchButtonProcess(v);
			}
		};

		OnClickListener nextLineClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// �����һ��·�ߵ�ͼ��
				mMapView.getOverlays().remove(routeOverlay);
				// ������һ��������
				SearchNextBusline();

			}
		};

		OnClickListener nodeClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ���·�߽ڵ�
				nodeClick(v);
			}
		};
		star.setOnClickListener(clickListener);
		nextline.setOnClickListener(nextLineClickListener);
		mBtnPre.setOnClickListener(nodeClickListener);
		mBtnNext.setOnClickListener(nodeClickListener);
	}

	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;

			locData.latitude = location.getLatitude();
			locData.longitude = location.getLongitude();
			// ��ȷ��Ȧ��ȡ����accuracy��ֵΪ0����
			locData.accuracy = location.getRadius();
			// �˴��������� locData�ķ�����Ϣ, �����λ SDK δ���ط�����Ϣ���û������Լ�ʵ�����̹�����ӷ�����Ϣ��
			locData.direction = location.getDerect();
			myLocationOverlay.setData(locData);
			if (isFirstLoc) {
				mMapView.getController().setCenter(
						new GeoPoint((int) (location.getLatitude() * 1e6),
								(int) (location.getLongitude() * 1e6)));
				// mMapView.getController().animateTo(new
				// GeoPoint((int)(location.getLatitude()*1e6),(int)(location.getLongitude()*1e6)));
			}

			mMapView.refresh();

		}

		@Override
		public void onReceivePoi(BDLocation arg0) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * �������
	 * 
	 * @param v
	 */
	void SearchButtonProcess(View v) {
		busLineIDList.clear();
		busLineIndex = 0;
		mBtnPre.setVisibility(View.INVISIBLE);
		mBtnNext.setVisibility(View.INVISIBLE);
		if (star.equals(v)) {
			EditText City = (EditText) findViewById(R.id.city);
			EditText line = (EditText) findViewById(R.id.line);
			// ����poi�������ӵõ�����poi���ҵ�������·���͵�poi����ʹ�ø�poi��uid���й�����������
			mSearch.poiSearchInCity(City.getText().toString(), line.getText()
					.toString());
		}
	}

	void SearchNextBusline() {
		if (busLineIndex >= busLineIDList.size()) {
			busLineIndex = 0;
		}
		if (busLineIndex >= 0 && busLineIndex < busLineIDList.size()
				&& busLineIDList.size() > 0) {
			mSearch.busLineSearch(((EditText) findViewById(R.id.city))
					.getText().toString(), busLineIDList.get(busLineIndex));
			busLineIndex++;
		}
	}

	/**
	 * ������������ͼ��
	 */
	public void createPaopao() {
		viewCache = getLayoutInflater().inflate(R.layout.textview, null);
		popupText = (TextView) viewCache.findViewById(R.id.textcache);
		// ���ݵ����Ӧ�ص�
		PopupClickListener popListener = new PopupClickListener() {

			@Override
			public void onClickedPopup(int index) {
				Log.v("click", "clickapoapo");
			}
		};
		pop = new PopupOverlay(mMapView, popListener);
	}

	/**
	 * �ڵ����ʾ��
	 * 
	 * @param v
	 */
	public void nodeClick(View v) {
		if (nodeIndex < -1 || route == null || nodeIndex >= route.getNumSteps())
			return;
		viewCache = getLayoutInflater().inflate(R.layout.textview, null);
		popupText = (TextView) viewCache.findViewById(R.id.textcache);
		// ��һ���ڵ�
		if (mBtnPre.equals(v) && nodeIndex > 0) {
			// ������
			nodeIndex--;
			// �ƶ���ָ������������
			mMapView.getController().animateTo(
					route.getStep(nodeIndex).getPoint());
			// ��������
			popupText.setText(route.getStep(nodeIndex).getContent());
			popupText.setBackgroundResource(R.drawable.popup);
			pop.showPopup(BMapUtil.getBitmapFromView(popupText),
					route.getStep(nodeIndex).getPoint(), 5);
		}
		// ��һ���ڵ�
		if (mBtnNext.equals(v) && nodeIndex < (route.getNumSteps() - 1)) {
			// ������
			nodeIndex++;
			// �ƶ���ָ������������
			mMapView.getController().animateTo(
					route.getStep(nodeIndex).getPoint());
			// ��������
			popupText.setText(route.getStep(nodeIndex).getContent());
			popupText.setBackgroundResource(R.drawable.popup);
			pop.showPopup(BMapUtil.getBitmapFromView(popupText),
					route.getStep(nodeIndex).getPoint(), 5);
		}

	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
//		if (locClient != null)
//			locClient.stop();
		mMapView.destroy();
		mSearch.destory();
		super.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mMapView.onSaveInstanceState(outState);

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mMapView.onRestoreInstanceState(savedInstanceState);
	}

}
