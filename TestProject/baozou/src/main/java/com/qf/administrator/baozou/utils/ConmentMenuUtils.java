package com.qf.administrator.baozou.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

import com.qf.administrator.baozou.R;

/**
 * Created by Alden on 2017/4/24.
 */

public class ConmentMenuUtils {
    public static void conmentMenu(Activity activity, View view, int menuValue){
        PopupMenu popupMenu = new PopupMenu(activity, view);

        activity.getMenuInflater().inflate(R.menu.more, popupMenu.getMenu());

        popupMenu.show();

    }
}
