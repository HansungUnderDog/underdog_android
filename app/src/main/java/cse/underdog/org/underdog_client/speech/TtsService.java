package cse.underdog.org.underdog_client.speech;

import android.app.Activity;
import android.os.Build;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class TtsService {
    private TextToSpeech tts;

    public TtsService(Activity activity) {
        tts = new TextToSpeech(activity, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                tts.setLanguage(Locale.getDefault());
            }
        });
    }

    public void sttStart(String value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(value, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            tts.speak(value, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}
