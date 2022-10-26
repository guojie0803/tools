package com.guojie.tools.fanShapedView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import com.guojie.tools.R;

public class CircleRelativeLayout extends RelativeLayout {
    private int color;
    private int[] colors;
    private int alpha;
    private Paint mPaint,ringNormalPaint ;

    public CircleRelativeLayout(Context context) {
        this(context,null);
    }
    public CircleRelativeLayout(Context context, AttributeSet attrs) {
        super(context,attrs);
        init(context,attrs);
        setWillNotDraw(false);
    }
    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.CircleRelativeLayoutLayout);
        color = array.getColor(R.styleable.CircleRelativeLayoutLayout_background_color,0X0000000);
        alpha = array.getInteger(R.styleable.CircleRelativeLayoutLayout_background_alpha,100);
        setColors();

        array.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);//取消锯齿
        mPaint.setStyle(Paint.Style.FILL);//设置画圆弧的画笔的属性为描边(空心)，个人喜欢叫它描边，叫空心有点会引起歧义
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.CYAN);

        mPaint.setARGB(alpha,colors[0],colors[1],colors[2]);
        mPaint.setAntiAlias(true);


        ringNormalPaint = new Paint(mPaint);
        ringNormalPaint.setStyle(Paint.Style.STROKE);
        ringNormalPaint.setStrokeWidth(dip2px(40));
        ringNormalPaint.setColor(Color.RED);//圆环默认颜色为灰色


    }
    @Override
    protected void onDraw(Canvas canvas) { //构建圆形
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        Log.d("tag","width=="+width);
        Log.d("tag","height=="+height);
        float cirX = width / 2;
        float cirY = width / 2;
        float radius = width / 2;
//        canvas.drawCircle(cirX, cirY, radius, mPaint);
        RectF oval = new RectF(width/3,-height/3*2,width/3*5,height/3*2);
        RectF oval1 = new RectF(width/6,-height/6*5,width/6*11,height/6*5);
        canvas.drawArc(oval,90,90,true,mPaint);
        canvas.drawArc(oval1,90,90,false,ringNormalPaint);
        Log.d("tag","width=="+width);
        Log.d("tag","height=="+height);
        super.onDraw(canvas);
    }

    public void setColor(int color) { //设置背景色
        this.color = color;
        setColors();
        invalidate();
    }

    public void setAlhpa(int alhpa) { //设置透明度
        this.alpha = alhpa;
        invalidate();
    }


    public void setColors() {
        int red = (color & 0xff0000) >> 16;
        int green = (color & 0x00ff00) >> 8;
        int blue = (color & 0x0000ff);
        this.colors = new int[]{red,green,blue};
    }

    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }
}
