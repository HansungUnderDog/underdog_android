package cse.underdog.org.underdog_client.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cse.underdog.org.underdog_client.LoginUserInfo;
import cse.underdog.org.underdog_client.MainActivity;
import cse.underdog.org.underdog_client.R;
import cse.underdog.org.underdog_client.application.ApplicationController;
import cse.underdog.org.underdog_client.network.NetworkService;
import cse.underdog.org.underdog_client.timeline.TimelineActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_2 extends AppCompatActivity {
    private static final String TAG = "Splash";
    private NetworkService service;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        service = ApplicationController.getInstance().getNetworkService();


        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Handler handler = new Handler() {
                    public void handleMessage(Message msg) {
                        SharedPreferences userInfo;
                        userInfo = getSharedPreferences("user", MODE_PRIVATE);
                        String remainEmail = userInfo.getString("email", null);
                        if (remainEmail == null) {
                            Log.d(TAG, "no cookies received");
                            Log.d(TAG, "cookie : " + userInfo.getString("cookie", ""));
                            Log.d(TAG, "email : " + userInfo.getString("email", ""));


                            Intent intent = new Intent(getBaseContext(), LoginActivity.class); //LoginActivity 로 다시 바꿔야함

                            startActivity(intent);
                            finish();

                        } else {
                            checkLogin(remainEmail, userInfo.getString("password", null));
                        }*//*
                    }
                };

                handler.sendEmptyMessageDelayed(0, 3000);
*/

                Intent intent = new Intent(MainActivity_2.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


    }

    public void checkLogin(String email, String password) {

        if (email.equals("")) {
            //이메일 미입력
            Toast.makeText(getBaseContext(), "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.equals("")) {
            //이메일 미입력
            Toast.makeText(getBaseContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginInfo info = new LoginInfo(email, password);
        //회원 체크
        Call<LoginResult> checkLogin = service.checkLogin(info);
        checkLogin.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.isSuccessful()) {


                    if (response.body().stat.equals("success")) {
                        //로그인 성공 시
                        LoginResult userObj = new LoginResult(response.body().data, response.body().stat);
                        LoginUserInfo.getInstance().setUserInfo(userObj.data);
                        SharedPreferences userInfo;
                        userInfo = getSharedPreferences("user", MODE_PRIVATE);

                        SharedPreferences.Editor editor = userInfo.edit();

                        //sharedPreferences에 유저정보 객체로 저장


                        Intent intent = new Intent(getBaseContext(), TimelineActivity.class);


                        //activity stack 비우고 새로 시작하기
                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
                            //안드로이드 버전이 진저브레드가 아니면,
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        } else {
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        }

                        startActivity(intent);
                    } else{
                        Toast.makeText(getBaseContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                    /*System.out.println("failfaifli");
                    System.out.println(response.body());
                    if (response.body().stat.equals("nonidfail")) {
                        Toast.makeText(getBaseContext(), "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                    } else if (response.body().stat.equals("pwdfail")) {
                        Toast.makeText(getBaseContext(), "회원가입되지 않은 회원 정보입니다.", Toast.LENGTH_SHORT).show();
                    } */
                    }

                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {

            }
        });
    }
}
