package cse.underdog.org.underdog_client.etc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cse.underdog.org.underdog_client.R;
import cse.underdog.org.underdog_client.speech.SttService;

public class SearchFragment extends Fragment {
    String result;
    SttService stt;
    String search;

    @BindView(R.id.searchWeb)
    WebView searchWebView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // stt = new SttService();
       // startActivityForResult(stt.getIntent(), stt.getREQ());
        getSearch();

        System.out.println("위에서 호출됨"+search);
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        Bundle extara = getArguments();
        search = extara.getString("search");
        searchWebView.loadUrl("https://m.search.naver.com/search.naver?query=" + search + "&where=m&sm=mtp_hty");

        // Enable Javascript
        WebSettings webSettings = searchWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        searchWebView.setWebViewClient(new WebViewClient());
        System.out.println("아래에서 호출됨");

        return view;
    }

    public void getSearch(){
        search = getArguments().getString("search");
        System.out.println("메소드에선됨?" + search);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        result = stt.getResult(requestCode, resultCode, resultCode, data);
        //tv.setText(result);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
