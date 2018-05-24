package cse.underdog.org.underdog_client.alarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Calendar;

import cse.underdog.org.underdog_client.MainActivity;
import cse.underdog.org.underdog_client.R;
import cse.underdog.org.underdog_client.timeline.TimelineActivity;

public class AlarmReceiver extends BroadcastReceiver{
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private boolean flag = false;
    private Context context;
    private AlarmManager mManager;
    private Calendar calendar;
    public static final int REPEAT_DAY = 1000 * 60 * 60 * 24;
    public static final int REPEAT_WEAK = REPEAT_DAY * 7;

    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotification;

    public void setAlarmClass(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("alarm", Activity.MODE_PRIVATE);
        editor = sp.edit();

        mNotification = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        mManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        calendar = Calendar.getInstance();
    }

    public void setDate(int year, int month, int date, int hour, int minute) {
        calendar.set(year, month, date, hour, minute);
    }

    public void setAlarm(int interval) {
        if (checkAlarm()) {
            while(System.currentTimeMillis() > calendar.getTimeInMillis()) calendar.setTimeInMillis(calendar.getTimeInMillis()+interval);
            mManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, pendingIntent());
            editor.putInt(Long.toString(calendar.getTimeInMillis()), (int)(calendar.getTimeInMillis() % ((long) Integer.MAX_VALUE)));
            editor.commit();
        } else Toast.makeText(context.getApplicationContext(), "Already register alaram", Toast.LENGTH_LONG).show();
    }
    public void setAlarm() {
/*        if (checkAlarm()) {
            mManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent());
            editor.putInt(Long.toString(calendar.getTimeInMillis()), (int)(calendar.getTimeInMillis() % ((long) Integer.MAX_VALUE)));
            editor.commit();
        }*/
        mManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent());
    }

    public boolean checkAlarm() {
        String key = Long.toString(calendar.getTimeInMillis());
        if((sp.getInt("key", -1)) == -1) return false;
        else return true;
    }

    public void resetAlarm() {
        mManager.cancel(pendingIntent());
        editor.remove(Long.toString(calendar.getTimeInMillis()));
        editor.commit();
    }

    private PendingIntent pendingIntent() {
        Intent intent = new Intent(context.getApplicationContext(), AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, (int)(calendar.getTimeInMillis() % ((long) Integer.MAX_VALUE)), intent, 0);
        return  pi;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context.getApplicationContext(), "run!!!!", Toast.LENGTH_LONG).show();

        NotificationManager mgr= (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        Notification.Builder note = new Notification.Builder(context);
        PendingIntent i=PendingIntent.getActivity(context, 0,new Intent(context, MainActivity.class),0);

        note.setTicker("스케쥴 알림");
        note.setAutoCancel(true);
        note.setSmallIcon(R.mipmap.app_logo_round);
        note.setNumber(1);
        note.setContentTitle("알림입니다.");
        note.setContentText("스케쥴 알림입니다.");
        note.setContentIntent(i);


        mgr.notify(1, note.getNotification());

/*        //노티피케이션에서 선택하면 표시를 없앨지 말지 설정

        notify.flags |= Notification.FLAG_AUTO_CANCEL;

        //진동 설정

        notify.vibrate = new long[] { 200, 200, 500, 300 };

        //사운드 설정

        // notify.sound=Uri.parse("file:/");

        notify.number++;



        //노티를 던진다!

        notifier.notify(1, notify);*/

    }
}

/*
AlarmReceiver mReceiver = new AlarmReceiver();
mReceiver.setAlarmClass(context);
mReceiver.setDate(year, month, date, hour, minute);
mReceiver.setAlarm(); or mReceiver.setAlarm(interval);
 */

/*
AlarmReceiver mReceiver = new AlarmReceiver();
mReceiver.setAlarmClass(context);
mReceiver.setDate(year, month, date, hour, minute);
mReceiver.resetAlarm();
 */