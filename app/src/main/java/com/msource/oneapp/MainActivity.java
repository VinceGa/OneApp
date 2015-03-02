package com.msource.oneapp;

import android.app.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WebView wv = (WebView) findViewById(R.id.webView1);
        wv.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUriLoading(WebView view, String uri)
            {
                view.loadUrl(uri);
                return true;
            }
        });
        wv.addJavascriptInterface(new BLEInterface(this), "BLE");
        final String url = "file:///android_asset/scan.html";

        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(url);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
