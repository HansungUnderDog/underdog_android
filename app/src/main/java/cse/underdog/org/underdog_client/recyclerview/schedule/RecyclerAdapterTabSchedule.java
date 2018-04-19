package cse.underdog.org.underdog_client.recyclerview.schedule;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;

import cse.underdog.org.underdog_client.R;
import cse.underdog.org.underdog_client.application.ApplicationController;
import cse.underdog.org.underdog_client.network.NetworkService;
import cse.underdog.org.underdog_client.schedule.ScheduleInfo;
import cse.underdog.org.underdog_client.schedule.ScheduleResult;
import cse.underdog.org.underdog_client.schedule.calendar.OneDayDecorator;
import cse.underdog.org.underdog_client.schedule.calendar.SaturdayDecorator;
import cse.underdog.org.underdog_client.schedule.calendar.SundayDecorator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAdapterTabSchedule extends RecyclerView.Adapter<ScheduleViewHolder>{

    private ArrayList<ScheduleInfo> schedules;


    public RecyclerAdapterTabSchedule(ArrayList<ScheduleInfo> scheduleInfos){
        schedules = scheduleInfos;
    }

    public void setAdapter(ArrayList<ScheduleInfo> scheduleInfos) {
        this.schedules = schedules;
        notifyDataSetChanged();
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule_detail, parent, false);
        ScheduleViewHolder scheduleViewHolder = new ScheduleViewHolder(view);

        return scheduleViewHolder;
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {

        holder.textView.setText(schedules.get(position).app_person + " " + schedules.get(position).place + " " + schedules.get(position).content + " " + schedules.get(position).dates);

        /*holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // click 시 필요한 동작 정의
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return schedules == null ? 0 : schedules.size();
    }



}
