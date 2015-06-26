package com.example.map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class welcome extends Activity{
	boolean frist = true;
	private ImageView view = null;
	
	private Handler handler = new Handler();
	private Runnable run ;
	
	private CheckBox box;
	
	private String yesno;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		SharedPreferences share = getSharedPreferences("nomore", Activity.MODE_PRIVATE);
		yesno = share.getString("yesno", "no");
		
		
		box =(CheckBox)findViewById(R.id.yesno); 
		
		
		
		run = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(frist){					
					Intent intent = new Intent(welcome.this,MainActivity.class);				
					startActivity(intent);
					frist = false;
					handler.removeCallbacks(run);
					welcome.this.finish();
				}
			}
		};
		
		
		
		
				
		view = (ImageView)findViewById(R.id.imageView1);
		
		view.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(welcome.this,MainActivity.class);
				startActivity(intent);
				welcome.this.finish();
				return false;
			}
		});
		
		if(frist){			
			tips();
			if(yesno == "no"){
			initGPS();
			}
		}
		else{
		initGPSopen();
		}
		
		
	}

	private void tips() {
		// TODO Auto-generated method stub
		Toast.makeText(welcome.this, "ApMap启动中...", Toast.LENGTH_SHORT).show();
	}

	private void initGPS() {
		// TODO Auto-generated method stub
		LocationManager manager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
//		LocationManager manager = (LocationManager)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//判断是否开启了GPS
		//没开启
		if(!manager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)){
			AlertDialog.Builder builder = new Builder(welcome.this);
//			LinearLayout linearlayout = (LinearLayout)getLayoutInflater().inflate(R.layout.checkbox, null);
			builder.setTitle("是否打开GPS提高定位精确度？");
//			builder.setMessage("检查到你未代开GPS"+"\n"+"打开能提高定位精确度"+"\n"+"是否打开？");
			builder.setMultiChoiceItems(new String[]{"不再提示"}, new boolean[]{false}, new OnMultiChoiceClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which, boolean isChecked) {
					// TODO Auto-generated method stub
					SharedPreferences share = getSharedPreferences("yseon", Activity.MODE_PRIVATE);
					SharedPreferences.Editor edit = share.edit();
					edit.putString("yesno", "yes");
					edit.commit();
				}
			});

//			builder.setView(linearlayout);
			builder.setPositiveButton("前往打开",new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
										
					Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivityForResult(intent, 0);
				}
			})
			.setNegativeButton("跳过", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
										
					frist = true;
					handler.postDelayed(run, 1000);
					
//					Intent intent = new Intent(welcome.this,MainActivity.class);
//					startActivity(intent);
//					finish();
				}
			})
			.show();
		}
		else{
			Intent intent = new Intent(welcome.this,MainActivity.class);
			startActivity(intent);
			finish();
		}
		frist = false;
	}

	private void initGPSopen() {
		// TODO Auto-generated method stub
		LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		boolean status = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
		String st ="";
		if(status){
			st +="GPS已开启";
			
		}
		else {
			st += "GPS未开启";
		}
		Toast.makeText(welcome.this, st, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode == 0){
			if(!frist){
			Intent intent = new Intent(welcome.this,MainActivity.class);
			startActivity(intent);
			finish();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}



























