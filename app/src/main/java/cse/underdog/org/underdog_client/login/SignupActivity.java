package cse.underdog.org.underdog_client.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cse.underdog.org.underdog_client.MainActivity;
import cse.underdog.org.underdog_client.R;
import cse.underdog.org.underdog_client.application.ApplicationController;
import cse.underdog.org.underdog_client.network.NetworkService;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "Signup";

    @BindView(R.id.edittext_signup_password)
    EditText editTextPassword;
    @BindView(R.id.edittext_signup_email)
    EditText editTextEmail;
    @BindView(R.id.button_signup_email)
    EditText buttonEmail;
    @BindView(R.id.edittext_signup_nickname)
    EditText editTextNickname;
    @BindView(R.id.button_signup_nickname)
    EditText buttonNickname;
    @BindView(R.id.edittext_signup_passwordre)
    EditText editTextPasswordRe;
    @BindView(R.id.button_signup)
    Button buttonSignup;

    private NetworkService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        service = ApplicationController.getInstance().getNetworkService();

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkSignup();
                if (isValid()) {
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        /*agreementImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, AgreeActivity.class);
                startActivity(intent);

            }
        });*/
    }

    private boolean isValid() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String rePassword = editTextPasswordRe.getText().toString();

        String regEmail = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";

        if (email.equals("")) {
            //이메일 미입력
            Toast.makeText(getBaseContext(), "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.equals("") || rePassword.equals("")) {
            //비밀번호 미입력
            Toast.makeText(getBaseContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!email.matches(regEmail)) {
            //이메일 형식을 맞추지 않았을 때,
            Toast.makeText(getBaseContext(), "이메일 형식을 올바르게 입력해주세요.", Toast.LENGTH_SHORT).show();
            editTextEmail.requestFocus();
            return false;
        }
        if (!password.equals(rePassword)) {
            //두 비밀번호가 같지 않을 때,
            Toast.makeText(getBaseContext(), "비밀번호가 일치하지 않습니다. 다시 한번 확인해주세요.", Toast.LENGTH_SHORT).show();
            editTextPasswordRe.requestFocus();
            return false;
        }

        return true;
    }
}
