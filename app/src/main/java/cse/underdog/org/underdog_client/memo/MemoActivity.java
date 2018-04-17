package cse.underdog.org.underdog_client.memo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cse.underdog.org.underdog_client.R;

public class MemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getBaseContext(), "되라", Toast.LENGTH_SHORT).show();
    }
}


