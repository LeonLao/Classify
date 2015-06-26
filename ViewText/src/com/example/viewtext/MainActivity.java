package com.example.viewtext;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ScrollView;

public class MainActivity extends Activity {
	private String toolslist[];
	private ScrollView scrollView;
	private Button toolsButton[];
	private ShopAdapter shopAdapter; 
 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		scrollView = (ScrollView) findViewById(R.id.tools_scrlllview);
		shopAdapter = new ShopAdapter(getSupportFragmentManager());
		
		
		
		
	}
	
	
	

	private class ShopAdapter extends FragmentPagerAdapter{

		public ShopAdapter(FragmentManager fm) {
			super(fm);
			
		}

		@Override
		public Fragment getItem(int arg0) {
			Fragment fragment =new Fragment_pro_type();
			Bundle bundle=new Bundle();
			String str=toolsList[arg0];
			bundle.putString("typename",str);
			fragment.setArguments(bundle);
			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}

	
}
