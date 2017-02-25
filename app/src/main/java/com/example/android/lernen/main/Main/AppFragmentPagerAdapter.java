package com.example.android.lernen.main.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by alfredchang on 2017-02-20.
 */

public class AppFragmentPagerAdapter extends FragmentPagerAdapter {

    private int PAGE_COUNT = 4;

    public AppFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ScheduleFragment();
            case 1:
                return new CoursesFragment();
            case 2:
                return new ExamsFragment();
            case 3:
                return new NotesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
