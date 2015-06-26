package com.example.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class settinglist extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settinglist);
		String str ="系统设置列表";
		setTitle(str);
		// 指定ListView
		ListView settingview = (ListView) findViewById(R.id.settingList);
		// 添加ListItem，设置事件响应
		settingview.setAdapter(new MyListAdapter());
		settingview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int index, long id) {
				// TODO Auto-generated method stub
				onListItemClick(index);
				// finish();
			}

		});
	}

	protected void onListItemClick(int index) {
		// TODO Auto-generated method stub
		Intent intent = null;
		intent = new Intent(settinglist.this, demos[index].demoClass);
		this.startActivity(intent);
		settinglist.this.finish();
	}

	private static final DemoInfo[] demos = {
			// new DemoInfo(R.string.demo_title_basemap,
			// R.string.demo_desc_basemap,
			// BaseMapDemo.class),
			new DemoInfo(R.string.setlayers, R.string.det_setlayers,
					SettingLayers.class),
			new DemoInfo(R.string.searchingbus, R.string.det_searchingbus,
					SearchingBusLine.class),
			new DemoInfo(R.string.settingUI, R.string.det_settingUI,
					settingUI.class),
			new DemoInfo(R.string.citysearch, R.string.det_citysearch, 
					searchview.class),
			new DemoInfo(R.string.poisearch, R.string.det_poisearch, 
					PoiSearch.class),
			new DemoInfo(R.string.direction, R.string.det_direction,
					direction.class)
			
	// new DemoInfo(R.string.poisearch, R.string.det_poisearch,
	// PoiSearch.class)
	};

	private class MyListAdapter extends BaseAdapter {

		public MyListAdapter() {
			super();
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return demos.length;
		}

		@Override
		public Object getItem(int index) {
			// TODO Auto-generated method stub
			return demos[index];
		}

		@Override
		public long getItemId(int id) {
			// TODO Auto-generated method stub
			return id;
		}

		@Override
		public View getView(int index, View convertView, ViewGroup parent) {
			convertView = View.inflate(settinglist.this,
					R.layout.settinglistform, null);
			TextView title = (TextView) convertView.findViewById(R.id.title);
			TextView desc = (TextView) convertView.findViewById(R.id.desc);
			title.setText(demos[index].title);
			desc.setText(demos[index].desc);			
			return convertView;

		}

	}

	private static class DemoInfo {
		private final int title;
		private final int desc;
		private final Class<? extends android.app.Activity> demoClass;

		public DemoInfo(int title, int desc,
				Class<? extends android.app.Activity> demoClass) {
			this.title = title;
			this.desc = desc;
			this.demoClass = demoClass;
		}
	}
}
