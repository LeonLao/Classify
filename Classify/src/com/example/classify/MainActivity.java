package com.example.classify;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.TextView;




import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;

public class MainActivity extends TopFragmentBackActivity {
	private String toolsList[];//nanbaos[]
	private TextView toolsTextViews[];//btntext[]
	private View views[];//views[]
	private LayoutInflater inflater;
	private ScrollView scrollView;
	private int scrllViewWidth = 0, scrollViewMiddle = 0;
	//private ViewPager shop_pager;
	
	StickyGridHeadersGridView mGridView;
	MyAdapter myAdapter;
	List<Type> list;
	//private Type type;
	
	private int currentItem=0;
	//private ShopAdapter shopAdapter;
	
	private String typename;
	
	//private LinearLayout shop_pager;
	
	private int gridID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		scrollView = (ScrollView) findViewById(R.id.tools_scrlllview);
		//shopAdapter = new ShopAdapter(getSupportFragmentManager());
		inflater = LayoutInflater.from(this);
		
		mGridView = (StickyGridHeadersGridView)findViewById(R.id.goods_pager);
		list = new ArrayList<Type>();		
		initPager();
		myAdapter = new MyAdapter(this, list, R.layout.list_pro_type_item, R.layout.list_pro_type_item);
		//mGridView.setAdapter((ListAdapter) shopAdapter);
		mGridView.setAdapter(myAdapter);
	//	mGridView.setOnItemClickListener(listener);
		mGridView.setAreHeadersSticky(true);
		
		
		showToolsView();
		
		//showgoods();
	}
	



	/**
     * initPager<br/>
     * ��ʼ��ViewPager�ؼ��������
     */
	private void initPager() {
		for(int i=0;i<20;i++){			
			 Type type = new Type();
			 type.setTid(i/10);
			 type.setTitle(""+i/10);
			 type.setIid(i);
			 type.setItem(""+i);
			 list.add(type);
		}
		
//		shop_pager=(ViewPager)findViewById(R.id.goods_pager);
//	    shop_pager = new ViewPager(this);
//		shop_pager.setAdapter(shopAdapter);		
//		shop_pager.setOnPageChangeListener(onPageChangeListener);		
	}



	/**
	 * ��̬������ʾitems�е�textview
	 */
	private void showToolsView() {
		toolsList=new String[]{"����Ь��","��ױ����","�������","��Ʒ����","ĸӤ��Ʒ","�Ҿ�����","ʳƷ����"
				,"����Ь��","��ױ����","�������","��Ʒ����","ĸӤ��Ʒ","�Ҿ�����","ʳƷ����"};
		LinearLayout toolsLayout=(LinearLayout) findViewById(R.id.tools);
		//���ó���
		toolsTextViews=new TextView[toolsList.length];
		views=new View[toolsList.length];
		
		for (int i = 0; i < toolsList.length; i++) {
			View view=inflater.inflate(R.layout.item_b_top_nav_layout, null);
			view.setId(i);
			view.setOnClickListener(toolsItemListener);
			TextView textView=(TextView) view.findViewById(R.id.text);
			textView.setText(toolsList[i]);
			toolsLayout.addView(view);
			toolsTextViews[i]=textView;
			views[i]=view;
		}
		changeTextColor(0);
		
	}
	
	
	/**
	 * �ı�textView����ɫ
	 * @param id
	 */
	private void changeTextColor(int id) {
		for (int i = 0; i < toolsTextViews.length; i++) {
			if(i!=id){
			   toolsTextViews[i].setBackgroundResource(android.R.color.transparent);
			   toolsTextViews[i].setTextColor(0xff000000);
			}
		}
		toolsTextViews[id].setBackgroundResource(android.R.color.white);
		toolsTextViews[id].setTextColor(0xffff5d5e);
		
	}

	private View.OnClickListener toolsItemListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//shop_pager.setCurrentItem(v.getId());			
			gridID = v.getId();
			changeTextColor(gridID);
			changeTextLocation(gridID);
		}
	};




	/**
	 * OnPageChangeListener<br/>
	 * ����ViewPagerѡ��仯�µ��¼�
	 */
	private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int arg0) {
			
//			if(shop_pager.getCurrentItem()!=arg0)shop_pager.setCurrentItem(arg0);
//			if(currentItem!=arg0){
//				changeTextColor(arg0);
//				changeTextLocation(arg0);
//			}
			//currentItem=arg0;
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {			
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
						
		}
	};
	
	
	

	/**
	 * ViewPager ����ѡ�
	 * @author Administrator
	 *
	 */
//		private class ShopAdapter extends FragmentPagerAdapter {
//			public ShopAdapter(FragmentManager fm) {
//				super(fm);
//		}
//		@Override
//		public Fragment getItem(int arg0) {
//			Fragment fragment =new Fragment_pro_type();
//			Bundle bundle=new Bundle();
//			String str=toolsList[arg0];
//			//int id = arg0;
//			bundle.putString("typename",str);
//			//bundle.putLong("itemID", id);
//			fragment.setArguments(bundle);
//			return fragment;
//		}
//		
//		@Override
//		public int getCount() {
//			return toolsList.length;
//		}
//	}
			/**
			 * �ı���Ŀλ��
			 * 
			 * @param clickPosition
			 */		
			private void changeTextLocation(int clickPosition) {
				int x = (views[clickPosition].getTop() - getScrollViewMiddle() + (getViewheight(views[clickPosition]) / 2));
				scrollView.smoothScrollTo(0, x);
			}
			
			
			/**
			 * ����view�Ŀ��
			 * 
			 * @param view
			 * @return
			 */
			private int getViewheight(View view) {
				return view.getBottom() - view.getTop();
			}


			/**
			 * ����scrollview���м�λ��
			 * 
			 * @return
			 */
			private int getScrollViewMiddle() {
				if (scrollViewMiddle == 0)
					scrollViewMiddle = getScrollViewheight() / 2;
				return scrollViewMiddle;
			}
			
			
			/**
			 * ����ScrollView�Ŀ��
			 * 
			 * @return
			 */
			private int getScrollViewheight() {
				if (scrllViewWidth == 0)
					scrllViewWidth = scrollView.getBottom() - scrollView.getTop();
				return scrllViewWidth;
			}	
			
	
}
