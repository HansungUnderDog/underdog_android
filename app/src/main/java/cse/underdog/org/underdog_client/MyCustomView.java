package cse.underdog.org.underdog_client;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class MyCustomView extends LinearLayout {

    public MyCustomView(Context context){
        super(context);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.activity_main, this);
    }


}
