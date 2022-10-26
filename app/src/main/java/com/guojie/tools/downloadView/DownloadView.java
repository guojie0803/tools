package com.guojie.tools.downloadView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 更新版本下载新版本百分比图
 */
public class DownloadView extends View {

    Paint ViewPaint, backgroundPaint, textPaint, okPaint;
    private int textPaintSize;
    private float mPercent = 50f;
    private Path path;
    private String text;

    public DownloadView(Context context) {
        this(context, null);
    }

    public DownloadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DownloadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        backgroundPaint = new Paint();
        //画笔颜色
        backgroundPaint.setColor(Color.GRAY);
        //设置抗锯齿
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setDither(true);

        ViewPaint = new Paint();
        ViewPaint.setColor(Color.BLUE);
        //设置抗锯齿
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setDither(true);


        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        //设置抗锯齿
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setDither(true);
        textPaintSize = sp2px(30);

        okPaint = new Paint();
        okPaint.setStrokeWidth(10);
        okPaint.setStyle(Paint.Style.STROKE);
        okPaint.setAntiAlias(true);
        backgroundPaint.setDither(true);
        okPaint.setColor(Color.GREEN);
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            //如果宽高模式都为AT_MOST，设置默认值为200，否则wrap_content无法正常显示
            setMeasuredDimension(widthSize-20, heightSize/4);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //同上
            setMeasuredDimension(widthSize, heightSize/6);
        } else if (heightMode == MeasureSpec.AT_MOST) {
//            同上
            setMeasuredDimension(widthSize, heightSize/6);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        @SuppressLint("DrawAllocation")
        //画矩形背景
        Rect rect = new Rect(0, 0, getWidth(), getHeight());
        canvas.drawRect(rect, backgroundPaint);


        @SuppressLint("DrawAllocation")
        Rect rectView = new Rect(0, 0, (int) (getWidth() * mPercent / 100), getHeight());
        canvas.drawRect(rectView, ViewPaint);

        if (mPercent == 100f) {
            float[] floats = {getWidth() / 2 - 30, getHeight() / 2, getWidth() / 2, getHeight() / 2 + 30,
                    getWidth() / 2, getHeight() / 2 + 30, getWidth() / 2 + 50, getHeight() / 2 - 60};
            canvas.drawLines(floats, okPaint);
            return;
        }
//        if ((int) mPercent % 10 == 0) {
//            text = (int) mPercent + "%";
//        }
        //画文字
        //字体属性
        text = (int) mPercent + "%";
        textPaint.setTextSize(textPaintSize);
        int textWidth = (int) textPaint.measureText(text);
        int x = getWidth() / 2 - textWidth / 2;

        Paint.FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(text, x, baseLine, textPaint);
    }

    public void setPercent(float percent) {
        this.mPercent = percent;
        invalidate();
    }
}
