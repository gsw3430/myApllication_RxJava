package com.vqsxb.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
	  
	   ArrayList<Fragment> list;
	   private List<String> titles;
	   
	    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
	        super(fm);  
	        this.list = list;  
	          
	    }  
    
	    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list, List<String> tltles) {
	        super(fm);  
	        this.list = list;  
	        this.titles = tltles;   
	    } 
	    
	    
	    
	    @Override
	    public int getCount() {  
	        return list.size();  
	    }  
	      
	    @Override
	    public Fragment getItem(int arg0) {
	        return list.get(arg0);  
	    }
        
	    @Override
	    public CharSequence getPageTitle(int position) {
	    	
	    	return titles.get(position);
	    }
	    
	@Override
		public Object instantiateItem(ViewGroup container, int position) {
			if(list.size()<= position){
				  position = position % list.size();
			  }
			   
			   Object obj = super.instantiateItem(container, position);
			        return obj;
		}
	    
	    
}
