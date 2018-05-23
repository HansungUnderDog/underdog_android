package cse.underdog.org.underdog_client.guide;

import android.content.Intent;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pm10.library.CircleIndicator;

import cse.underdog.org.underdog_client.R;
import cse.underdog.org.underdog_client.login.LoginActivity;

public class GuideActivity extends AppCompatActivity {
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        ViewPager viewPager = (ViewPager) findViewById(R.id.vp);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.circle_indicator);

        PagerAdapt mAdapter = new PagerAdapt(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
        indicator.setupWithViewPager(viewPager);
 //       mAdapter.registerDataSetObserver(indicator.getDataSetObserver());
    }

    private class PagerAdapt extends FragmentStatePagerAdapter {
        public PagerAdapt(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new Guide1Fragment();
                case 1:
                    return new Guide2Fragment();
                case 2:
                    return new Guide3Fragment();
                case 3:
                    return new Guide4Fragment();
                case 4:
                    return new GuideLastFragment();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 5;
        }
    }
}