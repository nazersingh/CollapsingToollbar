package com.nazer.collapsingtoollbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by nazer_pc on 10/22/2016.
 */
public class ViewpagerAdapter extends FragmentStatePagerAdapter {

    String Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    ArrayList<Fragment> list; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created

    public ViewpagerAdapter(FragmentManager fm, String mTitles[], ArrayList<Fragment> mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.list = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        return list.get(position);

    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return list.size();
    }
}