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
	
	String str1 = "1.1�������ͼ��ǣ�";
	String str2 = "1.2�����븩��ģʽ������Ϊ-30�㣺";
	String str3 = "1.3����ͼ��ť��";
	String str4 = "1.4����λģʽ��ť���ֱ��С���λ���������桱�͡����̡�ģʽ��";
	String str5 = "1.5�����ð�ť��";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.direction);
		String title = "ApMap���˵��";
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
		zhinanzhen.setText("1.6��ָ���밴ť����90��Ǻ������������ϵ�ģʽ��ʾ��ͼ��");
		zhinanzhen.append(Html.fromHtml("<img src='"+R.drawable.zhinanzhen+"'/>", imageGetter, null));
		
		TextView soushuo = (TextView) findViewById(R.id.textView14);
		soushuo.setText("1.7����������ҳ�棺");
		soushuo.append(Html.fromHtml("<img src='"+R.drawable.search+"'/>", imageGetter, null));
		
		TextView shoushi = (TextView) findViewById(R.id.textView4);
		shoushi.setText("2.1����㴥�ز������ƣ� ");
		shoushi.append(Html.fromHtml("<img src='"+R.drawable.shoushi+"'/>", imageGetter, null));
		
		TextView dianji = (TextView) findViewById(R.id.textView13);
		dianji.setText("һ��������ƣ�"+"\n"
						+"2.2���ڵ�ͼ�ϳ������ܱ�ǳ����㣬�����ǵ㣬����ʾ������Ϣ"+"\n"
						+"2.3��������ͼ�ϵ�������ǣ��ܲ鿴��ϸ��Ϣ"+"\n"
						+"2.4�����������б�ѡ��ܲ鿴��ϸ��Ϣ"+"\n"
						+"2.5�����������б�ѡ��ܷ������"+"\n");
		
		TextView about = (TextView) findViewById(R.id.textView6);
		about.setText("���ߣ�������"+"\n"
						+"������ʣ���ҵ���"+"\n"
						+"��ϵ���ߣ�leon_lao@126.com"+"\n"
						+"ʱ�䣺2014-5-30");
		
		TextView shengming = (TextView) findViewById(R.id.textView15);
		shengming.setText("���������ͼ�����Դ���磬������ͬ��ʵ���ɺ�"+"\n"
							+"������Ľӿ��ṩ�����ٶȵ�ͼAPI"+"\n"
							+"�������ʱ����Ӫ������ʹ��");
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
