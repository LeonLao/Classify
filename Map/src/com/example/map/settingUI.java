package com.example.map;

import com.example.map.MainActivity.MyHandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class settingUI extends Activity {

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

	private CheckBox cbsetzoom = null;
	private CheckBox cbsetmove = null;
	private CheckBox cbsetdoubleclick = null;
	private CheckBox cbsetrotate = null;
	private CheckBox cbsetoverlook = null;
	private CheckBox cbsetzoomcontrol = null;
	private Button btnsure = null;

	private MyApplication app = null;
	private MyHandler uihandler = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.settingui);
		String str = "用户界面控制";
		setTitle(str);

		app = (MyApplication) getApplication();
		// 获取共享变量
		uihandler = app.getHandler();

		cbsetzoom = (CheckBox) findViewById(R.id.setzoom);
		cbsetmove = (CheckBox) findViewById(R.id.setmove);
		cbsetdoubleclick = (CheckBox) findViewById(R.id.setdoubleclick);
		cbsetrotate = (CheckBox) findViewById(R.id.setrotate);
		cbsetoverlook = (CheckBox) findViewById(R.id.setoverlook);
		cbsetzoomcontrol = (CheckBox) findViewById(R.id.setzoomcontrol);
		btnsure = (Button) findViewById(R.id.sure);

		cbsetzoom.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {

				} else {
					uihandler.sendEmptyMessage(UICHANGED);
				}

			}
		});

		cbsetmove.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {

				} else {
					uihandler.sendEmptyMessage(UICHANGED2);
				}
			}
		});

		cbsetdoubleclick
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {

						} else {
							uihandler.sendEmptyMessage(UICHANGED3);
						}
					}
				});

		cbsetrotate.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {

				} else {
					uihandler.sendEmptyMessage(UICHANGED4);
				}
			}
		});

		cbsetoverlook.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {

				} else {
					uihandler.sendEmptyMessage(UICHANGED5);
				}
			}
		});

		cbsetzoomcontrol
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {

						} else {
							uihandler.sendEmptyMessage(UICHANGED6);
						}
					}
				});

		btnsure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (cbsetzoom.isChecked()) {
					uihandler.sendEmptyMessage(UICHANGED1);
				}
				if (cbsetmove.isChecked()) {
					uihandler.sendEmptyMessage(UICHANGED21);
				}
				if (cbsetdoubleclick.isChecked()) {
					uihandler.sendEmptyMessage(UICHANGED31);
				}
				if (cbsetrotate.isChecked()) {
					uihandler.sendEmptyMessage(UICHANGED41);
				}
				if (cbsetoverlook.isChecked()) {
					uihandler.sendEmptyMessage(UICHANGED51);
				}
				if (cbsetzoomcontrol.isChecked()) {
					uihandler.sendEmptyMessage(UICHANGED61);
				}
				settingUI.this.finish();

			}
		});

	}

}
