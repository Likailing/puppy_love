package com.example.typesprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CircleProgressBar extends View {
    public static final String TAG = "CircleProgressBar";
    //进度条背景色
    private int mCircleBgColor = Color.BLUE;
    //进度条文字展示颜色
    private int mTextColor = Color.GRAY;
    //进度条宽度
    private float mCircleWidth = 50;
    //进度条颜色
    private int mProgressColor = Color.GREEN;
    //进度条展示文字大小
    private float mTextSize = 50;

    private int progress;
    private int max = 100;

    private Paint paint = new Paint();

    public CircleProgressBar(Context context) {
        super(context);
        init(context,null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public void init(Context context,AttributeSet attrs){
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.CircleProgressBar);
        mCircleBgColor = array.getColor(R.styleable.CircleProgressBar_mCircleBgColor,Color.BLUE);
        mTextColor = array.getColor(R.styleable.CircleProgressBar_mTextColor,Color.GRAY);
        mProgressColor = array.getColor(R.styleable.CircleProgressBar_mProgressColor,Color.GREEN);
        mCircleWidth = array.getDimension(R.styleable.CircleProgressBar_mCircleWidth,50);
        mTextSize = array.getDimension(R.styleable.CircleProgressBar_mTextSize,50);

        array.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆背景
        paint.setColor(mCircleBgColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);//抗锯齿
        paint.setStrokeWidth(mCircleWidth);
        Log.d(TAG, "onDraw: getWidth() == " + getWidth());
        int center = getWidth() / 2;
        Log.d(TAG, "onDraw: center == " + center);
        int radius = (int) (center - mCircleWidth / 2);
        Log.d(TAG, "onDraw: radius == " + radius);
        //画圆（x坐标,y坐标,r半径,paint画笔）
        canvas.drawCircle(center,center,radius,paint);

        //写文字
        int percent = (int) (( progress / (float)max ) * 100);//进度具体数字
        Log.d(TAG, "onDraw: percent == " + percent);
        String percentStr = percent + "%";
        paint.setStrokeWidth(0);
        paint.setColor(mTextColor);
        paint.setTextSize(mTextSize);
        //得到对象的成员变量的值变为int类型。
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        if (progress != 0){
            Log.d(TAG, "onDraw: fm.bottom == " + fm.bottom);
            Log.d(TAG, "onDraw: fm.top == " + fm.top);
            //画进度数字文字（文本内容，x坐标，y坐标，画笔）
            canvas.drawText(percentStr,center - paint.measureText(percentStr) / 2,
                    center + (fm.bottom - fm.top) / 2 -fm.bottom,paint);
        }
        //画弧形
        RectF oval = new RectF(center - radius,center - radius,center + radius,center + radius);//确定外切矩形范围
        paint.setStrokeWidth(mCircleWidth);
        paint.setColor(mProgressColor);
        paint.setStrokeCap(Paint.Cap.ROUND);

        Log.d(TAG, "onDraw: sweepAngle == " + 360 * progress/max);
        //(矩形范围,圆弧开始的角度，圆弧结束的角度，是否显示以圆点为中心-false不含圆心,画笔)
        canvas.drawArc(oval,0,360 * progress/max,false,paint);

    }

    public void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress <= 100){
                    progress += 2;
                    setProgress(progress);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }
    //设置进度条
    public void setProgress(int progress){
        if (progress > max){
            progress = max;
        }
        if (progress <= max){
            this.progress = progress;
            postInvalidate();
        }
    }



}
