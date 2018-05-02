package cse.underdog.org.underdog_client.memo;

import android.Manifest;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

import cse.underdog.org.underdog_client.BuildConfig;
import cse.underdog.org.underdog_client.CalendarUtils;
import cse.underdog.org.underdog_client.EditActivity;
import cse.underdog.org.underdog_client.MainActivity;
import cse.underdog.org.underdog_client.R;
import cse.underdog.org.underdog_client.content.CalendarCursor;
import cse.underdog.org.underdog_client.content.EventCursor;
import cse.underdog.org.underdog_client.content.EventsQueryHandler;
import cse.underdog.org.underdog_client.weather.WeatherSyncService;
import cse.underdog.org.underdog_client.widget.AgendaAdapter;
import cse.underdog.org.underdog_client.widget.AgendaView;
import cse.underdog.org.underdog_client.widget.CalendarSelectionView;
import cse.underdog.org.underdog_client.widget.EventCalendarView;

public class MemoFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memo, null);

        return view;
    }

}