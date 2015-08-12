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
     * 初始化ViewPager控件相关内容
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
	 * 动态生成显示items中的textview
	 */
	private void showToolsView() {
		toolsList=new String[]{"名牌鞋包","美妆个护","首饰配件","名品服饰","母婴用品","家具生活","食品保健"
				,"名牌鞋包","美妆个护","首饰配件","名品服饰","母婴用品","家具生活","食品保健"};
		LinearLayout toolsLayout=(LinearLayout) findViewById(R.id.tools);
		//设置长度
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
	 * 改变textView的颜色
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
	 * 监听ViewPager选项卡变化事的事件
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
	 * ViewPager 加载选项卡
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
			 * 改变栏目位置
			 * 
			 * @param clickPosition
			 */		
			private void changeTextLocation(int clickPosition) {
				int x = (views[clickPosition].getTop() - getScrollViewMiddle() + (getViewheight(views[clickPosition]) / 2));
				scrollView.smoothScrollTo(0, x);
			}
			
			
			/**
			 * 返回view的宽度
			 * 
			 * @param view
			 * @return
			 */
			private int getViewheight(View view) {
				return view.getBottom() - view.getTop();
			}


			/**
			 * 返回scrollview的中间位置
			 * 
			 * @return
			 */
			private int getScrollViewMiddle() {
				if (scrollViewMiddle == 0)
					scrollViewMiddle = getScrollViewheight() / 2;
				return scrollViewMiddle;
			}
			
			
			/**
			 * 返回ScrollView的宽度
			 * 
			 * @return
			 */
			private int getScrollViewheight() {
				if (scrllViewWidth == 0)
					scrllViewWidth = scrollView.getBottom() - scrollView.getTop();
				return scrllViewWidth;
			}	
			
	
}
