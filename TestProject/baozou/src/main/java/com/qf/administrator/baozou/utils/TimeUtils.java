package com.qf.administrator.baozou.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alden on 2017/4/13.
 */

public class TimeUtils {
    /**
     * 返回yyyy-MM-dd格式的时间
     * @param time
     * @return
     */
    public static String formatTimeStamp(long time){
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }
    /**
     * 返回yyyy-MM-dd HH:mm:ss格式的时间
     * @param time
     * @return
     */
    public static String formatCommentTimeStamp(long time){
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
    /**
     * 计算时间戳距离当前时间的时间(小时为单位，超过24小时显示日期)
     * @return
     */
    public static String intervalTime(long time){

        long currtime = System.currentTimeMillis()/1000;
        int interHours = (int)(currtime-time)/3600;
        Log.e("time","cu"+currtime+"=new="+time+"=="+(currtime-time));
        if(interHours==0){
            return "刚刚";
        }
        else if(interHours<24){
            return interHours+"小时前";
        }else{
            SimpleDateFormat df3 = new SimpleDateFormat("MM月dd日");
            return df3.format(time);
        }
    }
}
