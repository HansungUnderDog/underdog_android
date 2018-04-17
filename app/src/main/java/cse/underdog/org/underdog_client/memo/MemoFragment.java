package cse.underdog.org.underdog_client.memo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cse.underdog.org.underdog_client.R;
import cse.underdog.org.underdog_client.schedule.ScheduleActivity;
import cse.underdog.org.underdog_client.timeline.TimelineActivity;
/*
public class MemoFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getActivity(), MemoActivity.class);
        startActivity(intent);
    }
}*/

public class MemoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_schedule,container,false);
        Intent intent = new Intent(this.getActivity(), MemoActivity.class);
        startActivity(intent);
        return view;
    }

}