package cse.underdog.org.underdog_client.timeline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cse.underdog.org.underdog_client.R;
import cse.underdog.org.underdog_client.TabPagerAdapter;
import cse.underdog.org.underdog_client.speech.SttService;
import cse.underdog.org.underdog_client.speech.TtsService;

public class TimelineFragment extends Fragment {
    String result;
    SttService stt;
    TtsService tts;
    Button sttBtn;
    Button ttsBtn;
    TextView tv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, null);

        sttBtn = (Button) view.findViewById(R.id.stt_btn);
        ttsBtn = (Button) view.findViewById(R.id.tts_btn);
        tv = (TextView) view.findViewById(R.id.tv);
        stt = new SttService();
        tts = new TtsService(getActivity());

        ttsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.ttsStart(tv.getText().toString());
            }
        });
        sttBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(stt.getIntent(), stt.getREQ());
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        result = stt.getResult(requestCode, resultCode, resultCode, data);
        tv.setText(result);
    }

    @Override
    public void onDestroy() {
        tts.ttsExit();
        super.onDestroy();
    }
}