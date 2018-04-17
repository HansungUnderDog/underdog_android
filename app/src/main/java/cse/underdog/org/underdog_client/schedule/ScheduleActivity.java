package cse.underdog.org.underdog_client.schedule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import cse.underdog.org.underdog_client.R;
import cse.underdog.org.underdog_client.application.ApplicationController;
import cse.underdog.org.underdog_client.network.NetworkService;
import cse.underdog.org.underdog_client.schedule.calendar.OneDayDecorator;
import cse.underdog.org.underdog_client.schedule.calendar.SaturdayDecorator;
import cse.underdog.org.underdog_client.schedule.calendar.SundayDecorator;

public class ScheduleActivity extends AppCompatActivity {
    //
    static CalendarDay selectedDay = null;
    static boolean Selected;

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
 //   ArrayList<DayData> Day_data;

    EditText edit_schedule;
    ListView schedule_List;
    TimePicker timePicker;
    String DATE;

    int year;
    int month;
    int day;
    int hour;
    int min;
    String AMPM = "";
    String text_schedule = "";
    //
    @BindView(R.id.calendarView)
    MaterialCalendarView calendar;

    @BindView(R.id.recyclerView)
    RecyclerView schedules;

//    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    private NetworkService service;

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        ButterKnife.bind(this);
        service = ApplicationController.getInstance().getNetworkService();

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

        calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

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
                    month = "0" + String.valueOf(selectedDay.getMonth());
                }
                else month = String.valueOf(selectedDay.getMonth());

                if(selectedDay.getDay() < 10) {
                    day = "0" + String.valueOf(selectedDay.getDay());
                }
                else day = String.valueOf(selectedDay.getDay());

                String selectedDate = year+"-"+month+"-"+day;

                Toast.makeText(getBaseContext(), selectedDate , Toast.LENGTH_SHORT).show();

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
            }
        });

    }
}
