package com.messanger.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by clicklabs on 11/24/17.
 * Class @{@link NetworkUtils} used to handle
 * all the network related tasks to handle
 * network functions.
 * <p>
 * Function list is as follow:
 * 1) isNetworkConnected(Context context);
 * 2)
 */

public final class NetworkUtils {
    private NetworkUtils() {
        // This utility class is not publicly instantiable
    }

    /**
     * Method used to get the status of the network state
     * of the device (Mobile Data  or Wifi Data)
     * is connected or not.
     *
     * @param context calling class context.
     * @return true if connected to the network else false.
     */
    public static boolean isNetworkConnected(final Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
