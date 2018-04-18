package cse.underdog.org.underdog_client.speech;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import java.util.ArrayList;

public class SttService {
    private static SttService instance = new SttService();
    private static Intent intent;
    private static RecognitionListener listener;
    private static String result = "no";

    private SttService() {
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR"); //Locale.getDefault()
        listener =  new RecognitionListener() {
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
                String key = "";
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
    }

    public static SttService getInstance() {
        return instance;
    }

    public static Intent getIntent() {
        return intent;
    }

    public static void setStt(String getPackageName) {
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName);
    }

    public static RecognitionListener getListener() {
        return listener;
    }

    public static String getResult() {
        return result;
    }

}
