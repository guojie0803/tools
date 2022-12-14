package com.guojie.tools.loginView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ShapeView extends View {
    private Shape mCurrentShape= Shape.Circle;
    private Paint mPaint;
    private Path mPath;

    public ShapeView(Context context) {
        this(context,null);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mWidth=getMeasuredWidth();
        int mHeight=getMeasuredHeight();
        setMeasuredDimension(Math.min(mWidth,mHeight),Math.min(mWidth,mHeight));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        switch (mCurrentShape){
            case Circle:
                //画圆
                mPaint.setColor(Color.YELLOW);
                int center=getWidth()/2;
                canvas.drawCircle(center,center,center,mPaint);
                break;
            case Square:
                //画正方形
                mPaint.setColor(Color.BLUE);
                canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
                break;
            case Triangle:
                //画三角
                mPaint.setColor(Color.RED);
                if(mPath==null){
                    mPath=new Path();
                    mPath.moveTo(getWidth()/2,0);
                    mPath.lineTo(0,(float) (getWidth()/2*Math.sqrt(3)));
                    mPath.lineTo(getWidth(),(float) (getWidth()/2*Math.sqrt(3)));
                    mPath.close();
                }
                canvas.drawPath(mPath,mPaint);

                break;

        }
    }

    public enum Shape{
        Circle,Square,Triangle
    }

    Shape getCurrentShape(){
        return mCurrentShape;
    }

    public void exchange(){
        switch (mCurrentShape){
            case Circle:
                mCurrentShape= Shape.Square;
                break;
            case Square:
                mCurrentShape= Shape.Triangle;
                break;
            case Triangle:
                mCurrentShape= Shape.Circle;
                break;
        }
        invalidate();
    }
}
