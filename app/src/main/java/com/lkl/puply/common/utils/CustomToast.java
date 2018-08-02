package com.lkl.puply.common.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lkl.puply.R;


/**
 * 自定义时长Toast
 *
 * @author likailing
 * 优点：可以自定义时间，
 *      可以时时更新内容
 * 使用：CustomToast.show("点击",MainActivity.this);
 */
public class CustomToast {
    private static final String TAG = "CustomToast";
    public static final String FIXCATION_TAG = "1";
    public static final String CUSTOM_TAG = "2";
    public static final int LENGTH_ALWAYS = 0;
    public static final int LENGTH_SHORT = 2;
    public static final int LENGTH_LONG = 4;
    private static Context context;

    private Toast toast;
    private int mDuration = LENGTH_SHORT;
    private boolean isShow = false;
    private static CustomToast customToast;
    private int countShow,countHide;


    private CustomToast() {
        if (toast == null) {
            toast = new Toast(context);
        }
    }

    private Runnable hideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };


    /**
     * 自定义时长的toast
     *
     * @param text
     * @param duration 自定义时间
     */
    public static void show(String text, int duration,Context context) {
        CustomToast.context = context;
        if (!TextUtils.isEmpty(text)) {
            if (customToast != null) {
                customToast.hide();
                customToast = CustomToast.makeText(text, duration, CUSTOM_TAG);
                if (null != customToast) {
                    customToast.show();
                }
            } else {
                customToast = CustomToast.makeText(text, duration, CUSTOM_TAG);
                if (null != customToast) {
                    customToast.show();
                }
            }
        }
    }

    /**
     * 固定时长的toast调用
     *
     * @param text
     */
    public static void show(String text,Context context) {
        CustomToast.context = context;
        if (!TextUtils.isEmpty(text)) {
            if (customToast != null) {
                customToast.hide();
                customToast = CustomToast.makeText(text, 2000, FIXCATION_TAG);
            } else {
                customToast = CustomToast.makeText(text, 2000, FIXCATION_TAG);
            }
        }
    }

    /**
     * Show the view for the specified duration.
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)  //使用@TargetApi()， 使高版本API的代码在低版本SDK不报错
    private void show() {
        countShow ++;
        Log.d(TAG, "hide: countShow" + countShow);
        if (isShow) {
            return;
        }
        toast.show();
        isShow = true;
        //判断duration，如果大于#LENGTH_ALWAYS 则设置消失时间
        if (mDuration > LENGTH_ALWAYS) {
            MainLooper.getInstance().postDelayed(hideRunnable, mDuration);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void hide() {
        countHide ++;
        Log.d(TAG, "hide: countHide" + countHide);
        //customToast = null;
        if (!isShow) {
            return;
        }
        toast.cancel();
        isShow = false;
    }


    private static CustomToast makeText(String text, int duration, String tag) {

        CustomToast customToast = new CustomToast();
        customToast.toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        View view = LayoutInflater.from(context).inflate(R.layout.toast_layout_custom, null);
        TextView textView = (TextView) view.findViewById(R.id.content);

        textView.setText(text);
        customToast.toast.setView(view);
        //此TAG标记区别是否是自定义时长调用，解决某些手机调用不显示toast情况（调用反射拿不到方法，直接利用系统显示）
        if (TextUtils.equals(tag, FIXCATION_TAG)) {
            customToast.toast.show();
        } else if (TextUtils.equals(tag, CUSTOM_TAG)) {
            customToast.mDuration = duration;
        }
        return customToast;
    }


    private void setText(int resId) {
        setText(context.getText(resId));
    }

    private void setText(CharSequence s) {
        toast.setText(s);
    }


}
