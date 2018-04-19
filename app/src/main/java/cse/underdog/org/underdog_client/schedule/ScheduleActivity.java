package cse.underdog.org.underdog_client.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import cse.underdog.org.underdog_client.R;
import cse.underdog.org.underdog_client.application.ApplicationController;
import cse.underdog.org.underdog_client.network.NetworkService;
import cse.underdog.org.underdog_client.schedule.calendar.OneDayDecorator;
import cse.underdog.org.underdog_client.schedule.calendar.SaturdayDecorator;
import cse.underdog.org.underdog_client.schedule.calendar.SundayDecorator;
<<<<<<< HEAD
import cse.underdog.org.underdog_client.timeline.TimelineActivity;
import cse.underdog.org.underdog_client.speech.SttService;
=======
>>>>>>> 7c50d16c774e286c118f89b6aa87c44d5feb702e
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HEAD;

public class ScheduleActivity extends AppCompatActivity {
<<<<<<< HEAD
    String selectedDate;
    List<ScheduleInfo> schedules;
=======
>>>>>>> 7c50d16c774e286c118f89b6aa87c44d5feb702e
    static CalendarDay selectedDay = null;
    static boolean Selected;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    RecognitionListener listener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle bundle) {

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float v) {

        }

        @Override
        public void onBufferReceived(byte[] bytes) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int i) {

        }

        @Override
        public void onResults(Bundle bundle) {
            String key= "";
            key = SpeechRecognizer.RESULTS_RECOGNITION;
            ArrayList<String> mResult = bundle.getStringArrayList(key);
            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);
            result = rs[0];
        }

        @Override
        public void onPartialResults(Bundle bundle) {

        }

        @Override
        public void onEvent(int i, Bundle bundle) {

        }
    };
    String result = "no";
    Intent i;
    SpeechRecognizer mRecognizer;

    @BindView(R.id.calendarView)
    MaterialCalendarView calendar;

<<<<<<< HEAD
    @BindView(R.id.recyclerView)
    RecyclerView schedule;
=======
    @BindView(R.id.stt_button)
    Button btn;

//    @BindView(R.id.recyclerView)
    RecyclerView schedules;
>>>>>>> 7c50d16c774e286c118f89b6aa87c44d5feb702e

    private NetworkService service;


    public void setRecyclerView() {
        getSchedule();
        for(ScheduleInfo scheduleInfo : schedules){

        }
    }

    public void setStt() {
        i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "말해주세요");
        startActivityForResult(i, REQ_CODE_SPEECH_INPUT);
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

=======

>>>>>>> 7c50d16c774e286c118f89b6aa87c44d5feb702e
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

=======
>>>>>>> 7c50d16c774e286c118f89b6aa87c44d5feb702e
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
<<<<<<< HEAD

=======
>>>>>>> 7c50d16c774e286c118f89b6aa87c44d5feb702e
                String selectedDate = year+"-"+month+"-"+day; // yyyy-mm-dd

                Toast.makeText(getBaseContext(), selectedDate , Toast.LENGTH_SHORT).show();
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



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.stt_button) {
                    System.out.println("in button listener");
                    setStt();
                }
            }
        });


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
<<<<<<< HEAD
                        schedules=response.body().data;
=======
                        System.out.println(response.body().data);
>>>>>>> 7c50d16c774e286c118f89b6aa87c44d5feb702e
                    }
               }
            }

            @Override
            public void onFailure(Call<ScheduleResult> call, Throwable t) {
                Log.e("fail", t.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    btn.setText(result.get(0));
                }
                break;
            }

        }
    }
}
