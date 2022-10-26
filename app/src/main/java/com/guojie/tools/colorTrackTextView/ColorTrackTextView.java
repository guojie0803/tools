package com.guojie.tools.colorTrackTextView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.guojie.tools.R;

//渐变色 字体
public class ColorTrackTextView extends androidx.appcompat.widget.AppCompatTextView {
    private int originalColor = Color.BLACK;
    private int changeColor = Color.RED;

    Paint mOriginPaint, mChangePaint;

    private float mCurrentProcess = 0.0f;


    private Direction mDirection= Direction.LEFT_TO_RIGHT;


    public enum Direction {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT
    }

    public ColorTrackTextView(@NonNull Context context) {
        this(context, null);
    }

    public ColorTrackTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);
        originalColor = a.getColor(R.styleable.ColorTrackTextView_originalColor, originalColor);
        changeColor = a.getColor(R.styleable.ColorTrackTextView_changeColor, changeColor);
        a.recycle();

        mOriginPaint = getPaintByColor(originalColor);
        mChangePaint = getPaintByColor(changeColor);
    }

    private Paint getPaintByColor(int Color) {
        Paint paint = new Paint();
        paint.setTextSize(getTextSize());
        paint.setColor(Color);
        paint.setAntiAlias(true);
        paint.setDither(true);
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int middle = (int) (mCurrentProcess * getWidth());

        if (mDirection == Direction.LEFT_TO_RIGHT) {
            drawText(canvas, mChangePaint, 0, middle);
            drawText(canvas, mOriginPaint, middle, getWidth());
        }else {
            drawText(canvas, mChangePaint, getWidth()-middle, getWidth());
            drawText(canvas, mOriginPaint, 0, getWidth()-middle);
        }
    }


    private void drawText(Canvas canvas, Paint paint, int start, int end) {
        canvas.save();
        Rect rect = new Rect(start, 0, end, getHeight());
        canvas.clipRect(rect);
        String text = getText().toString();
        Rect textBounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), textBounds);
        int x = getWidth() / 2 - textBounds.width() / 2;
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(text, x, baseLine, paint);
        canvas.restore();
    }

    public void setDirection(Direction direction){
        this.mDirection=direction;
    }

    public void setCurrentProcess(float currentProcess){
        this.mCurrentProcess=currentProcess;
        invalidate();
    }

    public void setChangeColor(int changeColor) {
        this.mChangePaint.setColor(changeColor);
    }

    public void setOriginalColor(int originalColor) {
        this.mOriginPaint.setColor(originalColor);
    }
}
