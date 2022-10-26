package com.guojie.tools.audioView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * spo2声音图标
 */

public class SoundSpo2View extends View {

    private Paint paintLine;
    private final int lineSpace = dip2ps(5);//竖线之间的间隔宽度
    private final int lineWidth = dip2ps(3);//竖线的宽度
    private int lineColor = Color.parseColor("#DC7785");
    private int soundSize = 10;
    private int width,height;


    public SoundSpo2View(Context context) {
        this(context,null);
    }

    public SoundSpo2View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SoundSpo2View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paintLine = new Paint();
        paintLine.setStrokeWidth(lineWidth);
        paintLine.setAntiAlias(true);
        paintLine.setColor(lineColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i=0;i< soundSize ;i++){
            int startY = (lineSpace >> 1) + i * (lineSpace + lineWidth);
            canvas.drawLine((width >> 1) -10,startY, (width >> 1) +10, startY,paintLine);
        }
    }

    private int dip2ps(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,getResources().
                getDisplayMetrics());
    }

    private int ps2dip(int ps) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,ps,getResources().
                getDisplayMetrics());
    }

    public void change(int heightSize){
        this.soundSize = heightSize;
        invalidate();
    }
}
