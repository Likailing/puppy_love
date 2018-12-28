package com.example.roundandcircleimage.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.example.roundandcircleimage.R;


/**
 * 圆角图片1：BitmapShader实现圆角图片
 * 主要用到3个类：BitmapShader画圆角，Paint画笔，RectF矩形
 * */
public class RoundImageView1 extends ImageView {

    private final int DEFAULT_RADIUS=10;
    //BitmapShader（图像渲染）是Shader（着色器，给图像着色）的子类
    private BitmapShader mBitmapShader;
    private Paint mBitmapPaint = new Paint();
    private RectF mRoundRect=new RectF();//矩形
    private int x_radius;
    private int y_radius;

    public RoundImageView1(Context context) {
        super(context);
        initObjectAttribute();
    }

    public RoundImageView1(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundImageView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initObjectAttribute();
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView, defStyleAttr, 0);
        //基本属性
        x_radius=a.getDimensionPixelSize(R.styleable.RoundImageView_x_radius, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,DEFAULT_RADIUS, getResources().getDisplayMetrics()));
        y_radius=a.getDimensionPixelSize(R.styleable.RoundImageView_y_radius, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,DEFAULT_RADIUS, getResources().getDisplayMetrics()));
        a.recycle();
    }

    private void initObjectAttribute() {
        mBitmapPaint.setAntiAlias(true);//抗锯齿
        //此处放开，图片就是剧中显示（不论布局中scaleType是什么属性）
//        if(getScaleType() != ScaleType.CENTER_CROP) {
//            setScaleType(ScaleType.CENTER_CROP);
//        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mRoundRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        createBitmapShader();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        createBitmapShader();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        createBitmapShader();
    }

    private void createBitmapShader(){
        mBitmapShader = null;
        Drawable mDrawable = getDrawable();
        if (null == mDrawable) {
            return;
        }
        if(mDrawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) mDrawable;
            Bitmap bitmap = bd.getBitmap();
            //参数1：bitmap; 参数2，3：TileMode 取值有3种（CLAMP-拉伸，REPEAT-重复，MIRROR-镜像）
            mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        } else {//if Drawable instanceof NinePathDrawable ，the code below is bad , because a view reference two bitmap ( one in NinePath , other is here)
            int w = mDrawable.getIntrinsicWidth();
            int h = mDrawable.getIntrinsicHeight();

            //ARGB指的是一种 色彩模式，里面A代表Alpha，R表示red，G表示green，B表示blue. ARGB_8888就是由4个8位组成即32位 ,代表32位ARGB位图
            Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            mDrawable.setBounds(0, 0, w, h);
            mDrawable.draw(canvas);
            mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable mDrawable = getDrawable();
        if (null == mDrawable || null == mBitmapShader) {
            return;
        }
        Matrix mDrawMatrix= getImageMatrix();
        if(null == mDrawMatrix) {
            //shader的变化矩阵，主要用于放大缩小
            mDrawMatrix =new Matrix();
        }
        mBitmapShader.setLocalMatrix(mDrawMatrix);
        mBitmapPaint.setShader(mBitmapShader);//BitmapShader使用方法
        //绘制圆角矩形
        //参数1：RecF对象； 参数2,3：X,Y方向上的圆角半径； 参数4：画笔
        canvas.drawRoundRect(mRoundRect, x_radius, y_radius,mBitmapPaint);
    }
}
