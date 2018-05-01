package cse.underdog.org.underdog_client;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import cse.underdog.org.underdog_client.etc.EtcFragment;
import cse.underdog.org.underdog_client.memo.MemoFragment;
import cse.underdog.org.underdog_client.schedule.ScheduleFragment;
import cse.underdog.org.underdog_client.timeline.TimelineFragment;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        //Returning the current tabs
        switch (position){
            case 0:
                EtcFragment etcFragment = new EtcFragment();
                return etcFragment;
                /*TimelineFragment timelineFragment = new TimelineFragment();
                return timelineFragment;*/
            case 1:
                ScheduleFragment scheduleFragment = new ScheduleFragment();
                return scheduleFragment;
            case 2:
                MemoFragment memoFragment = new MemoFragment();
                return memoFragment;
            case 3:
                /*EtcFragment etcFragment = new EtcFragment();
                return etcFragment;*/
                TimelineFragment timelineFragment = new TimelineFragment();
                return timelineFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }




}
