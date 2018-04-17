package cse.underdog.org.underdog_client.login;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cse.underdog.org.underdog_client.R;
import cse.underdog.org.underdog_client.application.ApplicationController;
import cse.underdog.org.underdog_client.network.NetworkService;
import cse.underdog.org.underdog_client.timeline.TimelineActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = "Login";
    @BindView(R.id.edittext_login_email)
    EditText editTextEmail;

    @BindView(R.id.edittext_login_password)
    EditText editTextPassword;

    @BindView(R.id.imagebutton_login_login)
    Button imageButtonLogin;

    @BindView(R.id.imagebutton_login_signup)
    Button imageButtonSignup;

    private NetworkService service;

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        service = ApplicationController.getInstance().getNetworkService();

        imageButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

        imageButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SignupActivity.class);
                startActivity(intent);
            }
        });



    }
    public void checkLogin() {
        this.checkLogin(null, null, false);
    }

    public void checkLogin(String email, String pwd, final boolean isAuto) {
        //유효성 체크
        if (email == null) {
            email = editTextEmail.getText().toString();
        }
        if (pwd == null) {
            pwd = editTextPassword.getText().toString();
        }
        if (email.equals("")) {
            //이메일 미입력
            Toast.makeText(getBaseContext(), "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pwd.equals("")) {
            //이메일 미입력
            Toast.makeText(getBaseContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginInfo info = new LoginInfo(email, pwd);
        //회원 체크
        Call<LoginResult> checkLogin = service.checkLogin(info);
        checkLogin.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.isSuccessful()) {
                    System.out.println("response.body" + response.body().stat);
                    if (response.body().stat.equals("success")) {
                        System.out.println("hihihihi");
                        Intent intent = new Intent(getBaseContext(), TimelineActivity.class);
                        startActivity(intent);
                    }
                }else{
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



            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(getBaseContext(), "통신 에러", Toast.LENGTH_SHORT).show();
                Log.i("fail", t.getMessage());
            }
        });
    }

    //뒤로가기 버튼 클릭
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "뒤로 가기 키를 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }

    }
}
