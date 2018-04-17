package cse.underdog.org.underdog_client.timeline;

<<<<<<< HEAD
import android.content.Context;
=======
>>>>>>> b223e028edb084389a9ed860d7144f6b5ee9e5d1
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

import cse.underdog.org.underdog_client.R;

/*public class TimelineFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getActivity(), TimelineActivity.class);
        startActivity(intent);
    }
}*/

public class TimelineFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    } // all method is test

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        /*View view = inflater.inflate(R.layout.fragment_timeline, container, false);
        Intent intent = new Intent(this.getActivity(), TimelineActivity.class);
        startActivity(intent);

        return view;*/

        return inflater.inflate(R.layout.activity_timeline,container,false);
    }

}


