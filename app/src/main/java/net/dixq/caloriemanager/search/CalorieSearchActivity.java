package net.dixq.caloriemanager.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.dixq.caloriemanager.R;

/**
 * Created by dixq on 2018/03/25.
 */

public class CalorieSearchActivity extends AppCompatActivity {

    public static final String INTENT_TAG_SEARCH_KEYWORD = "intent_tag_search_keyword";

    private WebView _webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_search);

        String keyword = getIntent().getStringExtra(INTENT_TAG_SEARCH_KEYWORD);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
//      getSupportActionBar().setTitle("["+keyword+" カロリー]を検索中");
        getSupportActionBar().setTitle("");

        _webView = findViewById(R.id.webview);
        _webView.setWebViewClient(new WebViewClient());
        _webView.loadUrl("https://google.com/search?q="+keyword+"+"+"カロリー");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean result = true;
        switch (id) {
            case android.R.id.home:
                finish();
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }
        return result;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode != KeyEvent.KEYCODE_BACK){
            return super.onKeyDown(keyCode, event);
        } else if(_webView.canGoBack()) {
            _webView.goBack();
        } else {
            finish();
        }
        return false;
    }

}
