package com.example.map;

import com.example.map.MainActivity.MyHandler;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class SettingLayers extends Activity {

	private static final int CHANGED = 0x0010;
	private static final int CHANGED2 = 0x0020;
	private static final int CHANGED3 = 0x0030;
	private static final int CHANGED4 = 0x0040;

	private MyApplication app = null;
	private MyHandler mhandler = null;
	private Button btnsure = null;
	private RadioButton commonbtn = null;
	private RadioButton satellitebtn = null;
	private CheckBox trafficbtn = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.settinglayers);
		String str = "地图显示设置";
		setTitle(str);

		app = (MyApplication) getApplication();
		// 获得共享变量实例
		mhandler = app.getHandler();

		btnsure = (Button) findViewById(R.id.sure);
		commonbtn = (RadioButton) findViewById(R.id.common);
		satellitebtn = (RadioButton) findViewById(R.id.satellite);
		trafficbtn = (CheckBox) findViewById(R.id.traffic);

		btnsure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (satellitebtn.isChecked()) {
					if (trafficbtn.isChecked()) {
						mhandler.sendEmptyMessage(CHANGED3);
						SettingLayers.this.finish();
					} else {
						mhandler.sendEmptyMessage(CHANGED);
						SettingLayers.this.finish();
					}
				} else if (commonbtn.isChecked()) {
					if (trafficbtn.isChecked()) {
						mhandler.sendEmptyMessage(CHANGED4);
						SettingLayers.this.finish();
					} else {
						mhandler.sendEmptyMessage(CHANGED2);
						SettingLayers.this.finish();
					}
				}
			}

		});

	}

}
