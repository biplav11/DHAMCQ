package com.asterdio.dhamcq;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

public class ConnectionDetector
{
    private Context _context;

    public ConnectionDetector(Context paramContext)
    {
        this._context = paramContext;
    }

    public boolean isConnectingToInternet()
    {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)this._context.getSystemService("connectivity");
        if (localConnectivityManager != null)
        {
            NetworkInfo[] arrayOfNetworkInfo = localConnectivityManager.getAllNetworkInfo();
            if (arrayOfNetworkInfo != null)
                for (int i = 0; i < arrayOfNetworkInfo.length; i++)
                    if (arrayOfNetworkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
        }
        return false;
    }
}