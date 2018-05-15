package cse.underdog.org.underdog_client.memo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cse.underdog.org.underdog_client.MainActivity;
import cse.underdog.org.underdog_client.R;

public class CreateNote extends AppCompatActivity {

    EditText mTitleText;
    EditText mDescriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        mTitleText = (EditText) findViewById(R.id.txttitle);
        mDescriptionText = (EditText) findViewById(R.id.description);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMemoCreate);
        setSupportActionBar(toolbar);

    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, MemoActivity.class);
        startActivity(setIntent);
    }

    /*void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_save:
                String title = mTitleText.getText().toString();
                String detail = mDescriptionText.getText().toString();
                ContentValues cv = new ContentValues();
                /*cv.put(mDbHelper.TITLE, title);
                cv.put(mDbHelper.DETAIL, detail);
                cv.put(mDbHelper.TYPE, type);
                cv.put(mDbHelper.TIME, getString(R.string.Not_Set));*/


            case R.id.action_back:
                Intent openMainActivity = new Intent(this, MemoActivity.class);
                startActivity(openMainActivity);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
