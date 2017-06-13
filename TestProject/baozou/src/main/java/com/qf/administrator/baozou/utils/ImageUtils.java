package com.qf.administrator.baozou.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

/**
 * Created by Alden on 2017/4/12.
 */

public class ImageUtils {
    public static Bitmap drawableToBitmap(Drawable drawable) // drawable 转换成 bitmap
    {
        int width = drawable.getIntrinsicWidth();   // 取 drawable 的长宽
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888:Bitmap.Config.RGB_565;         // 取 drawable 的颜色格式
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);     // 建立对应 bitmap
        Canvas canvas = new Canvas(bitmap);         // 建立对应 bitmap 的画布
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);      // 把 drawable 内容画到画布中
        return bitmap;
    }
    public static Drawable zoomDrawable(Drawable drawable, int w)
    {
        int width = drawable.getIntrinsicWidth();
        int height= drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable); // drawable 转换成 bitmap
        Matrix matrix = new Matrix();   // 创建操作图片用的 Matrix 对象
        float scaleWidth = ((float)w / width);   // 计算缩放比例
       // float scaleHeight = ((float)h / height);
        matrix.postScale(scaleWidth, scaleWidth);         // 设置缩放比例
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true);       // 建立新的 bitmap ，其内容是对原 bitmap 的缩放后的图
        return new BitmapDrawable(newbmp);       // 把 bitmap 转换成 drawable 并返回
    }

    public static Bitmap cropImage(Activity activity, Bitmap bitmap){
        //得到图片的分辨率，获取宽度
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int mScreenWidth = dm.widthPixels;// 获取屏幕分辨率宽度
        int mScreenHeight = dm.heightPixels;


        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        //需要判断屏幕宽度和图片宽度的大小，来决定是放大还是缩小，如果是放大，应该还需要加上图片本身宽度，即：（倍数+1）为缩放倍数float num = ((float)bitmapWidth/mScreenWidth)+1.0f;
        //得到图片宽度比
        float num = mScreenWidth / (float)bitmapWidth;

        Matrix matrix = new Matrix();
        matrix.postScale(num, num);
// 产生缩放后的Bitmap对象
        Bitmap resizeBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth,
                bitmapHeight, matrix, true);
        //Log.e("width",mScreenWidth+"start="+bitmapWidth+"==end"+resizeBitmap.getWidth());

        return resizeBitmap;
    }


}
