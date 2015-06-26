package com.example.map;

import com.example.map.R.drawable;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.widget.TextView;

public class direction extends Activity{
	
	TextView qingchu = null;
	TextView shoushi = null;
	TextView shengming = null;
	TextView guanyu = null;
	
	String str1 = "1.1、清除地图标记：";
	String str2 = "1.2、进入俯视模式，俯角为-30°：";
	String str3 = "1.3、截图按钮：";
	String str4 = "1.4、定位模式按钮，分别有“定位”、“跟随”和“罗盘”模式：";
	String str5 = "1.5、设置按钮：";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.direction);
		String title = "ApMap相关说明";
		setTitle(title);
		
		setupView();
		
//		qingchu.setText(str1+"\n"+
//				str2+"\n"+
//				str3+"\n"+
//				str4+"\n"+
//				str5);
	}
	private void setupView(){
		qingchu = (TextView) findViewById(R.id.textView2);
		qingchu.setText(str1);
		qingchu.append(Html.fromHtml("<img src='"+R.drawable.clear+"'/>", imageGetter, null));
		
		TextView fushi = (TextView) findViewById(R.id.textView9);
		fushi.setText(str2);
		fushi.append(Html.fromHtml("<img src='"+R.drawable.fushi+"'/>", imageGetter, null));
		
		TextView jietu = (TextView) findViewById(R.id.textView8);
		jietu.setText(str3);
		jietu.append(Html.fromHtml("<img src='"+R.drawable.screen+"'/>", imageGetter, null));
		
		TextView dingwei = (TextView) findViewById(R.id.textView10);
		dingwei.setText(str4);
		dingwei.append(Html.fromHtml("<img src='"+R.drawable.myloc+"'/>", imageGetter, null));
		dingwei.append(Html.fromHtml("<img src='"+R.drawable.walking+"'/>", imageGetter, null));
		dingwei.append(Html.fromHtml("<img src='"+R.drawable.compass+"'/>", imageGetter, null));
		
		TextView shezhi = (TextView) findViewById(R.id.textView11);
		shezhi.setText(str5);
		shezhi.append(Html.fromHtml("<img src='"+R.drawable.setting+"'/>", imageGetter, null));
		
		TextView zhinanzhen = (TextView) findViewById(R.id.textView12);
		zhinanzhen.setText("1.6、指南针按钮，以90°角和正北方向向上的模式显示地图：");
		zhinanzhen.append(Html.fromHtml("<img src='"+R.drawable.zhinanzhen+"'/>", imageGetter, null));
		
		TextView soushuo = (TextView) findViewById(R.id.textView14);
		soushuo.setText("1.7、进入搜索页面：");
		soushuo.append(Html.fromHtml("<img src='"+R.drawable.search+"'/>", imageGetter, null));
		
		TextView shoushi = (TextView) findViewById(R.id.textView4);
		shoushi.setText("2.1、多点触控操作手势： ");
		shoushi.append(Html.fromHtml("<img src='"+R.drawable.shoushi+"'/>", imageGetter, null));
		
		TextView dianji = (TextView) findViewById(R.id.textView13);
		dianji.setText("一般操作手势："+"\n"
						+"2.2、在地图上长按，能标记长按点，点击标记点，能显示坐标信息"+"\n"
						+"2.3、单击地图上的搜索标记，能查看详细信息"+"\n"
						+"2.4、单击搜索列表选项，能查看详细信息"+"\n"
						+"2.5、长按搜索列表选项，能发起分享"+"\n");
		
		TextView about = (TextView) findViewById(R.id.textView6);
		about.setText("作者：劳世杰"+"\n"
						+"软件性质：毕业设计"+"\n"
						+"联系作者：leon_lao@126.com"+"\n"
						+"时间：2014-5-30");
		
		TextView shengming = (TextView) findViewById(R.id.textView15);
		shengming.setText("该软件所用图标均来源网络，如有雷同，实属巧合"+"\n"
							+"该软件的接口提供方：百度地图API"+"\n"
							+"该软件暂时不作营利性质使用");
	}
	ImageGetter imageGetter = new ImageGetter() {
		
		@Override
		public Drawable getDrawable(String source) {
			// TODO Auto-generated method stub
			int id = Integer.parseInt(source);
			Drawable drawable = getResources().getDrawable(id);
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
			
			return drawable;
		}
	};
		
	

}
