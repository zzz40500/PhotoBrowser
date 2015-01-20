package com.mingle.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class ViewpagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> allList = null;
    List<CharSequence> titles = null;

    public ViewpagerAdapter(Fragment fragment, List<Fragment> allList,
                            List<CharSequence> titles) {
        super(fragment.getChildFragmentManager());
        this.allList = allList;
        this.titles = titles;
        // TODO Auto-generated constructor stub

    }

    public ViewpagerAdapter(android.support.v4.app.FragmentManager manager, List<Fragment> allList,
                            List<CharSequence> titles) {
        super(manager);
        this.allList = allList;
        this.titles = titles;
        // TODO Auto-generated constructor stub

    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return allList.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return allList.size();
    }

    // @Override
    // public int getItemPosition(Object object) {
    // return POSITION_NONE;
    // }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles.get(position);
    }

}
