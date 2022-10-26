package com.guojie.tools.audioView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.List;

/**
 * 声音波纹
 */
public class AudioView extends View {

    private final int lineSpace = dip2ps(5);//竖线之间的间隔宽度
    private final int lineWidth = dip2ps(1);//竖线的宽度
    private int lineColor = Color.GREEN;
    private int lineCount;
    private int heightSize;
    private float lineHeight=heightSize;

    private List<Integer> values;//存放数值

    Paint paintLine;

    public AudioView(Context context) {
        this(context,null);
    }

    public AudioView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AudioView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        heightSize = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        lineCount = getWidth()/(lineSpace+lineWidth);

        for(int i=0;i<lineCount;i++){
            int startX = (lineSpace >> 1) + i * (lineSpace + lineWidth);
            canvas.drawLine(startX,heightSize, startX, (float) (heightSize/2+heightSize/2*Math.random()),paintLine);
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

    public void change(){
        invalidate();
    }
}
