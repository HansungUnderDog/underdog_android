package cse.underdog.org.underdog_client.timeline;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cse.underdog.org.underdog_client.R;
import cse.underdog.org.underdog_client.TabPagerAdapter;

public class TimelineActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        //Initializing the TabLayout;
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Timeline"));
        tabLayout.addTab(tabLayout.newTab().setText("Calendar"));
        tabLayout.addTab(tabLayout.newTab().setText("Memo"));
        tabLayout.addTab(tabLayout.newTab().setText("Etc"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //Initializing ViewPager
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        //Creating adapter
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
