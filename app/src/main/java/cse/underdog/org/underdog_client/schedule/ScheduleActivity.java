package cse.underdog.org.underdog_client.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cse.underdog.org.underdog_client.R;
import cse.underdog.org.underdog_client.application.ApplicationController;
import cse.underdog.org.underdog_client.login.LoginInfo;
import cse.underdog.org.underdog_client.network.NetworkService;
import cse.underdog.org.underdog_client.schedule.calendar.OneDayDecorator;
import cse.underdog.org.underdog_client.schedule.calendar.SaturdayDecorator;
import cse.underdog.org.underdog_client.schedule.calendar.SundayDecorator;
<<<<<<< HEAD
import cse.underdog.org.underdog_client.timeline.TimelineActivity;
=======
import cse.underdog.org.underdog_client.speech.SttService;
>>>>>>> b223e028edb084389a9ed860d7144f6b5ee9e5d1
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleActivity extends AppCompatActivity {
    String selectedDate;
    static CalendarDay selectedDay = null;
    static boolean Selected;
    SttService stt;
    Intent i;
    SpeechRecognizer mRecognizer;
    String result;

    @BindView(R.id.calendarView)
    MaterialCalendarView calendar;

    @BindView(R.id.recyclerView)
    RecyclerView schedules;

    private NetworkService service;

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    public void setStt() {
         stt = SttService.getInstance();
         i = SttService.getIntent();
         stt.setStt(getPackageName());
         mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
         mRecognizer.setRecognitionListener(stt.getListener());
         // mRecognizer.startListening(i); // start sttService
        //  String result = stt.getResult;
    }

    public void setRecyclerView() {

    }

    public void setCalendar() {
        calendar.state().edit() //materialCalendarView 세팅 : 달력의 시작과 끝을 지정
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2010, 0, 1))
                .setMaximumDate(CalendarDay.from(2050, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();//View);

        calendar.addDecorators( // 오늘, 일요일, 토요일 날짜에 데코
                new SundayDecorator(),
                new SaturdayDecorator(),
                new OneDayDecorator());
<<<<<<< HEAD
=======

        /*Call<ScheduleResult> getSchedule = service.getSchedule(); // server와 connect

        getSchedule.enqueue(new Callback<ScheduleResult>() {
            @Override
            public void onResponse(Call<ScheduleResult> call, Response<ScheduleResult> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "response successed" , Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getBaseContext(), "response failed" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ScheduleResult> call, Throwable t) {

            }
        });*/

>>>>>>> b223e028edb084389a9ed860d7144f6b5ee9e5d1
        calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                getSchedule();
                if(selectedDay == date){
                    selected = false;
                    Selected = selected;
                }
                else{
                    selected = true;
                    Selected = selected;
                }
                selectedDay = date;
                String year = String.valueOf(selectedDay.getYear());
                String month, day;
                if(selectedDay.getMonth() <10) {
                    month = String.valueOf(selectedDay.getMonth()+1);
                }
                else month = String.valueOf(selectedDay.getMonth());

                if(selectedDay.getDay() < 10) {
                    day = "0" + String.valueOf(selectedDay.getDay());
                }
                else day = String.valueOf(selectedDay.getDay());

<<<<<<< HEAD
                selectedDate = year+"-"+month+"-"+day;


                //Toast.makeText(getBaseContext(), selectedDate , Toast.LENGTH_SHORT).show();

                /*
                DATE = selectedDay.toString();
                String[] parsedDATA = DATE.split("[{]");
                parsedDATA = parsedDATA[1].split("[}]");
                parsedDATA = parsedDATA[0].split("-");
                year = Integer.parseInt(parsedDATA[0]);
                month = Integer.parseInt(parsedDATA[1])+1;
                day = Integer.parseInt(parsedDATA[2]);

                arrayList = new ArrayList<String>();

                for(int i=0; i<Day_data.size(); i++){
                    if(Day_data.get(i).getDay() == day){
                        arrayList.add(Day_data.get(i).getText_schedule());
                    }
                }
                updateScheduleList();
                */
=======
                String selectedDate = year+"-"+month+"-"+day; // yyyy-mm-dd

                Toast.makeText(getBaseContext(), selectedDate , Toast.LENGTH_SHORT).show();
>>>>>>> b223e028edb084389a9ed860d7144f6b5ee9e5d1
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        ButterKnife.bind(this);
        service = ApplicationController.getInstance().getNetworkService();

        setCalendar();
        setRecyclerView();


    }
    public void getSchedule(){
        Call<ScheduleResult> getSchedule = service.getSchedule();
        System.out.println("리스폰스");
        getSchedule.enqueue(new Callback<ScheduleResult>() {
            @Override
            public void onResponse(Call<ScheduleResult> call, Response<ScheduleResult> response) {
               // System.out.println("리스폰스"+response);
               if (response.isSuccessful()) {
                    if (response.body().stat.equals("success")) {
                        Toast.makeText(getBaseContext(), "response success" , Toast.LENGTH_SHORT).show();

                    }

               }
            }

            @Override
            public void onFailure(Call<ScheduleResult> call, Throwable t) {
                Log.e("fail", t.getMessage());
            }
        });
    }

}
