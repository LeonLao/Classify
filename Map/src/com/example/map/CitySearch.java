package com.example.map;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class CitySearch extends Activity {
	
	private EditText city = null;
	private EditText key = null;
	private Button mapbtn = null;
	private Button listbtn = null;
	private String strcity =null;
	private String strkey = null;
	
	private Integer strray = null;
	private String strray2 = null;
	
	TextView textview1 = null;
	TextView textview2 = null;
	TextView textview4 = null;
	
	private RadioGroup searchgroup = null;
	
	CharSequence titleLable1 = "城市搜索";
	CharSequence titleLable2 = "周边搜索";
	
	RadioButton rad0 = null;
	RadioButton rad1 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.citysearch);
		
		setTitle(titleLable1);

		
		city = (EditText) findViewById(R.id.city);
		key = (EditText) findViewById(R.id.key);
		mapbtn = (Button) findViewById(R.id.btnmap);
		listbtn = (Button) findViewById(R.id.btnlist);
		
		rad0 = (RadioButton) findViewById(R.id.radio0);
		rad1 = (RadioButton) findViewById(R.id.radio1);
		
		mapbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(rad0.isChecked()){
				strcity = city.getText().toString();
				strkey = key.getText().toString();
				Intent intent = new Intent();
				intent.putExtra("intentcity", strcity);
				intent.putExtra("intentkey", strkey);
				intent.putExtra("type", 1);
				CitySearch.this.setResult(RESULT_OK, intent);
				//startActivity(intent);
				CitySearch.this.finish();
				}
				else if(rad1.isChecked()){
					strray = Integer.parseInt(city.getText().toString());
					strkey = key.getText().toString();
					Intent intent = new Intent();
					intent.putExtra("strray", strray);
					intent.putExtra("intentkey", strkey);
					intent.putExtra("type", 2);
					CitySearch.this.setResult(RESULT_OK, intent);
					CitySearch.this.finish();
				}
			}
		});
		listbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(rad0.isChecked()){
				strcity = city.getText().toString();
				strkey = key.getText().toString();
				Intent intent = new Intent(CitySearch.this,poilist.class);
				intent.putExtra("intentcity", strcity);
				intent.putExtra("intentkey", strkey);
				String type1 = "1";
				intent.putExtra("type", type1);
				startActivity(intent);
				CitySearch.this.finish();
				}
				else if(rad1.isChecked()){
					strray2 = city.getText().toString();
					strkey = key.getText().toString();
					Intent intent = new Intent(CitySearch.this,poilist.class);
					intent.putExtra("intentray", strray2);
					intent.putExtra("intentkey", strkey);
					String type2 = "2";
					intent.putExtra("type", type2);
					startActivity(intent);
					CitySearch.this.finish();
				} 
			}
		});
		
		searchgroup = (RadioGroup) findViewById(R.id.radioGroup1);
		textview1 = (TextView) findViewById(R.id.textView1);
		textview2 = (TextView) findViewById(R.id.textView2);
		textview4 = (TextView) findViewById(R.id.textView4);
		
		
		searchgroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId == R.id.radio0){
					textview1.setText(getString(R.string.citysearch));
					textview2.setText(getString(R.string.city));
					textview4.setText("");
					
					city.setText("江门");
					city.setInputType(InputType.TYPE_CLASS_TEXT);
					CitySearch.this.setTitle(titleLable1);
				}
				else if(checkedId == R.id.radio1){
					textview1.setText(getString(R.string.poisearch));
					textview2.setText(getString(R.string.radii));
					textview4.setText("（米）");
					
					city.setText("500");
					city.setInputType(InputType.TYPE_CLASS_NUMBER);
					
					CitySearch.this.setTitle(titleLable2);
				}
			}
		});
	}

}
