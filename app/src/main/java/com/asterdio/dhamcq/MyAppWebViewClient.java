package com.asterdio.dhamcq;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyAppWebViewClient extends WebViewClient
{
    public boolean shouldOverrideUriLoading(WebView paramWebView, String paramString)
    {
        if (Uri.parse(paramString).getHost().endsWith("dhamcq.com"))
            return false;
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
        paramWebView.getContext().startActivity(localIntent);
        return true;
    }
}