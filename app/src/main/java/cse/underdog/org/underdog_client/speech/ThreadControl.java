package cse.underdog.org.underdog_client.speech;

import android.content.Intent;
import android.util.Log;

import cse.underdog.org.underdog_client.etc.EtcActivity;

public class ThreadControl extends Thread{
    EtcActivity activity;
    Intent intent;

    public ThreadControl(EtcActivity activity) {
        this.activity = activity;
    }

    @Override
    synchronized public void run() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("컨트롤러", "notify됨");
//        activity.doYoutube();
        Log.i("컨트롤러", "유튜브실행");
    }
}
