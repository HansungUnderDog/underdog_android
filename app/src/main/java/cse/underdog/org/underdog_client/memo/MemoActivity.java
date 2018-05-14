package cse.underdog.org.underdog_client.memo;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import cse.underdog.org.underdog_client.BottomNavigationViewHelper;
import cse.underdog.org.underdog_client.MainActivity;
import cse.underdog.org.underdog_client.R;
import cse.underdog.org.underdog_client.etc.EtcActivity;
import cse.underdog.org.underdog_client.timeline.TimelineActivity;

public class MemoActivity extends ListActivity {
    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;

    private static final int DELETE_ID = Menu.FIRST;
    private int mNoteNumber = 1;

    private MemoDbAdapter mDbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        mDbHelper = new MemoDbAdapter (this);
        mDbHelper.open();
        fillData();
        registerForContextMenu(getListView());
        Button addnote = (Button)findViewById(R.id.addnotebutton);
        addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNote();
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.ic_android:
                        Intent intent1 = new Intent(MemoActivity.this, TimelineActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_books:
                        Intent intent2 = new Intent(MemoActivity.this, MainActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_center_focus:

                        break;

                    case R.id.ic_backup:
                        Intent intent4 = new Intent(MemoActivity.this, EtcActivity.class);
                        startActivity(intent4);
                        break;
                }


                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.notelist_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:

                AlertDialog.Builder dialog = new AlertDialog.Builder(MemoActivity.this);
                dialog.setTitle("About");
                /*dialog.setMessage("Hello! I'm Heng, the creator of this application. This application is created based on learning." +
                        " Used it on trading or any others activity that is related to business is strictly forbidden."
                        +"If there is any bug is found please freely e-mail me. "+
                        "\n\tedisonthk@gmail.com"
                );*/
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createNote() {
        Intent i = new Intent(this, MemoEdit.class);
        startActivityForResult(i, ACTIVITY_CREATE);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent i = new Intent(this, MemoEdit.class);
        i.putExtra(MemoDbAdapter.KEY_ROWID, id);
        startActivityForResult(i, ACTIVITY_EDIT);
    }

    private void fillData() {
        // Get all of the notes from the database and create the item list
        Cursor notesCursor = mDbHelper.fetchAllNotes();
        startManagingCursor(notesCursor);


        String[] from = new String[] { MemoDbAdapter.KEY_TITLE ,MemoDbAdapter.KEY_DATE};
        int[] to = new int[] { R.id.text1 ,R.id.date_row};

        // Now create an array adapter and set it to display using our row
        SimpleCursorAdapter notes =
                new SimpleCursorAdapter(this, R.layout.memos_row, notesCursor, from, to);
        setListAdapter(notes);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, DELETE_ID, 0, R.string.menu_delete);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case DELETE_ID:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                mDbHelper.deleteNote(info.id);
                fillData();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        fillData();
    }
}
