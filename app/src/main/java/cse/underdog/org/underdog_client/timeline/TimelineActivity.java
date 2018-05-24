package cse.underdog.org.underdog_client.timeline;

import android.Manifest;
import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cse.underdog.org.underdog_client.BottomNavigationViewHelper;
import cse.underdog.org.underdog_client.LoginUserInfo;
import cse.underdog.org.underdog_client.MainActivity;
import cse.underdog.org.underdog_client.R;
import cse.underdog.org.underdog_client.alarm.AlarmReceiver;
import cse.underdog.org.underdog_client.application.ApplicationController;
import cse.underdog.org.underdog_client.etc.EtcActivity;
import cse.underdog.org.underdog_client.location_GPS.Gps;
import cse.underdog.org.underdog_client.login.LoginActivity;
import cse.underdog.org.underdog_client.login.LoginInfo;
import cse.underdog.org.underdog_client.login.LoginResult;
import cse.underdog.org.underdog_client.login.LogoutResult;
import cse.underdog.org.underdog_client.memo.MemoActivity;
import cse.underdog.org.underdog_client.network.NetworkService;
import cse.underdog.org.underdog_client.speech.SttActivity;
import cse.underdog.org.underdog_client.speech.SttService;
import cse.underdog.org.underdog_client.speech.TtsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimelineActivity extends AppCompatActivity {
    // gps, stt, tts Test code
    private String result;
    private Gps gps;
    private SttService stt;
    private TtsService tts;
    private Button sttBtn;
    private Button ttsBtn;
    private Button gpsBtn;
    private Button alarmBtn;
    private TextView tv;
    private Context context;
    private Button logoutBtn;

    private TextView txtLat;
    private TextView txtLon;
    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;
    private boolean isAccessFineLocation = false;
    private boolean isAccessCoarseLocation = false;
    private boolean isPermission = false;
    private NetworkService service;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Intent sttIntent;

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        sttIntent = new Intent(this, SttActivity.class);
        context = this.getApplicationContext();
        sttBtn = (Button) findViewById(R.id.stt_btn);
        ttsBtn = (Button) findViewById(R.id.tts_btn);
        gpsBtn = (Button) findViewById(R.id.gps_btn);
        alarmBtn = (Button) findViewById(R.id.alarm_Btn);
        txtLat = (TextView) findViewById(R.id.tv_latitude);
        txtLon = (TextView) findViewById(R.id.tv_longtitude);
        tv = (TextView) findViewById(R.id.tv);
        stt = new SttService();
        tts = new TtsService(context);
        logoutBtn = (Button) findViewById(R.id.logout_btn);

        service = ApplicationController.getInstance().getNetworkService();

        alarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "alarm running", Toast.LENGTH_LONG).show();
                AlarmReceiver ar = new AlarmReceiver();
                ar.setAlarmClass(context);
                ar.setAlarm();
            }
        });

        gpsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isPermission) {
                    callPermission();
                    return;
                }

                gps = new Gps(getApplicationContext());

                if(gps.isGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longtitude = gps.getLongitude();

                    txtLat.setText(String.valueOf(latitude));
                    txtLon.setText(String.valueOf(longtitude));

                    Toast.makeText(getApplicationContext(), "당신의 위치 - \n위도: " + Double.toString(latitude) + "\n경도: " + Double.toString(longtitude), Toast.LENGTH_LONG).show();
                } else {gps.showSettingsAlert();}
            }
        });

        callPermission();

        ttsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.ttsStart(tv.getText().toString());
            }
        });
        sttBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivityForResult(stt.getIntent(), stt.getREQ());
                startActivityForResult(sttIntent, 100);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogout();
            }
        });


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_android:

                        break;

                    case R.id.ic_books:
                        Intent intent2 = new Intent(TimelineActivity.this, MainActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_center_focus:
                        Intent intent3 = new Intent(TimelineActivity.this, MemoActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_backup:
                        Intent intent4 = new Intent(TimelineActivity.this, EtcActivity.class);
                        startActivity(intent4);
                        break;
                }


                return false;
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_ACCESS_FINE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            isAccessFineLocation = true;

        } else if (requestCode == PERMISSIONS_ACCESS_COARSE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            isAccessCoarseLocation = true;
        }

        if (isAccessFineLocation && isAccessCoarseLocation) {
            isPermission = true;
        }
    }

    // 전화번호 권한 요청
    private void callPermission() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_ACCESS_FINE_LOCATION);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_ACCESS_COARSE_LOCATION);
        } else {
            isPermission = true;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        result = stt.getResult(requestCode, resultCode, resultCode, data);
//        tv.setText(result);
        result = SttActivity.RESULT;
        tv.setText(result);
    }

    @Override
    public void onDestroy() {
        tts.ttsExit();
        super.onDestroy();
    }

    public void checkLogout() {

        //회원 체크
        Call<LogoutResult> checkLogout = service.checkLogout();
        checkLogout.enqueue(new Callback<LogoutResult>() {
            @Override
            public void onResponse(Call<LogoutResult> call, Response<LogoutResult> response) {
                if (response.isSuccessful()) {


                    if (response.body().stat.equals("success")) {
                        pref = getSharedPreferences("users",MODE_PRIVATE);
                        editor = pref.edit();
                        editor.clear();
                        editor.commit();

                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(getBaseContext(), "로그아웃 실패", Toast.LENGTH_SHORT).show();
                }
            }



            @Override
            public void onFailure(Call<LogoutResult> call, Throwable t) {
                Toast.makeText(getBaseContext(), "통신 에러", Toast.LENGTH_SHORT).show();
                Log.e("fail", t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            finish();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "뒤로 가기 키를 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
