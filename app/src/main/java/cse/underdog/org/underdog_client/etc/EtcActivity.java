package cse.underdog.org.underdog_client.etc;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cse.underdog.org.underdog_client.BottomNavigationViewHelper;
import cse.underdog.org.underdog_client.MainActivity;
import cse.underdog.org.underdog_client.R;
import cse.underdog.org.underdog_client.memo.MemoActivity;
import cse.underdog.org.underdog_client.speech.SttService;
import cse.underdog.org.underdog_client.timeline.TimelineActivity;

public class EtcActivity extends AppCompatActivity{
    String result;
    SttService stt;
    String search;

    @BindView(R.id.searchBtn)
    Button searchButton;

    @BindView(R.id.musicBtn)
    Button musicButton;

    @BindView(R.id.weatherBtn)
    Button weatherButton;

    @BindView(R.id.goBtn)
    Button goButton;

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etc);

        ButterKnife.bind(this);

        stt = new SttService();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(stt.getIntent(), stt.getREQ());
            }
        });

        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(stt.getIntent(), stt.getREQ());
            }
        });

        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(stt.getIntent(), stt.getREQ());
            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*View search = getLayoutInflater().inflate(R.layout.fragment_etc, null);
                Bundle bundle = new Bundle();
                bundle.putString("search", search);
                Fragment searchFragment = new SearchFragment();
                searchFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.etcLayout, searchFragment);
                transaction.addToBackStack(null);
                transaction.commit();*/
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_android:
                        Intent intent1 = new Intent(EtcActivity.this, TimelineActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_books:
                        Intent intent2 = new Intent(EtcActivity.this, MainActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_center_focus:
                        Intent intent3 = new Intent(EtcActivity.this, MemoActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_backup:
                        break;
                }


                return false;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        result = stt.getResult(requestCode, resultCode, resultCode, data);
        //int count=0;
        //String search="";
        if(result.contains("검색")){
            /*String tmp[] = result.split("");
            for(int i=0; i<tmp.length; i++){
                search+=tmp[i];
                if(tmp[i].equals("검색")||tmp[i].equals("검색해")){
                    System.out.println("브렉문"+search);
                    break;
                }
                System.out.println("포문"+search);
            }
            System.out.println("이프"+search);*/
            int idx = result.indexOf("검색");
            System.out.println("인덱"+idx);
            search = result.substring(0, idx);
            System.out.println("서치" + search);
        }else{
            //System.out.println("엘스"+search);
        }
    }
}
