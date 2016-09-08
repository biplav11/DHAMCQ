package com.asterdio.dhamcq;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import np.com.biplavsharma.dhamcq.R;

public class MainActivity extends ActionBarActivity {
    ConnectionDetector cd;
    Boolean isInternetPresent = Boolean.valueOf(false);
    private WebView mWebView;
    private Toolbar toolbar;

    public void onBackPressed() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
            return;
        }
        super.onBackPressed();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main);


        if (ConnectionDetector.isConnectingToInternet(this)) {
            this.mWebView = (WebView) findViewById(R.id.activity_main_webview);
            WebSettings localWebSettings = this.mWebView.getSettings();
            this.mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            localWebSettings.setJavaScriptEnabled(true);
            this.mWebView.getSettings().setGeolocationEnabled(true);
            this.mWebView.setWebChromeClient(new GeoWebChromeClient());
            this.mWebView.loadUrl("http://dhamcq.com/");
            getSupportActionBar().hide();
            this.mWebView.setWebViewClient(new MyAppWebViewClient() {
                public void onPageFinished(WebView paramWebView, String paramString) {
                    //hide loading image
                    findViewById(R.id.imageLoading1).setVisibility(View.GONE);
                    //show webview
                    findViewById(R.id.activity_main_webview).setVisibility(View.VISIBLE);
                }
            });
            return;
        } else {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
            localBuilder.setTitle("Network Error");
            localBuilder.setMessage("There seems to be problem with your internet connection.");
            localBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    System.exit(0);
                }
            });
            localBuilder.show();
        }
    }

    public class GeoWebChromeClient extends WebChromeClient {
        public GeoWebChromeClient() {
        }

        public void onGeolocationPermissionsShowPrompt(String paramString, GeolocationPermissions.Callback paramCallback) {
            paramCallback.invoke(paramString, true, false);
        }
    }

    private class MyAppWebViewClient extends WebViewClient {
        private MyAppWebViewClient() {
        }
    }
}
