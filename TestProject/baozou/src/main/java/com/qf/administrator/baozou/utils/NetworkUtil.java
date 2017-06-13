package com.qf.administrator.baozou.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by L_Alden on 2016/11/14.
 * 判断当前网络是否可用
 */
public class NetworkUtil {
    public static boolean isConnectWork(Context context) {
        //获取网络管理器
        ConnectivityManager manager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        //通过管理器对象获取网络信息对象
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //通过网络信息对象判断是否网络可用，getTypeName()可以获取网络类型名
        if (networkInfo != null && networkInfo.isConnectedOrConnecting() &&
                networkInfo.isAvailable()) {
            return true;//当前网络可用
        } else {
            return true;//当前网络不可用
        }
    }
}
