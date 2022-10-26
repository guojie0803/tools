package com.guojie.tools.loadingView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

/**
 * 加载动画
 */
public class LoadingView extends RelativeLayout {
    private CircleView mLeftView,mMiddleView,mRightView;
    private int mTranslationDistance=dip2px(30);
    private long mDuration= 250;

    private int leftColor,middleColor,rightColor;
    private AnimatorSet animatorSet;
    private boolean mIsStopAnimator=false;

    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.WHITE);

        mLeftView = getCircleView(context);
        mLeftView.exchange(Color.RED);
        mMiddleView = getCircleView(context);
        mMiddleView.exchange(Color.BLACK);
        mRightView = getCircleView(context);
        mRightView.exchange(Color.BLUE);
        addView(mLeftView);
        addView(mRightView);
        addView(mMiddleView);

        post(new Runnable() {
            @Override
            public void run() {
                expandAnimator();
            }
        });
    }

    private void expandAnimator() {
        if(mIsStopAnimator){
            return;
        }
        ObjectAnimator leftTranslationAnimator=ObjectAnimator.ofFloat(
                mLeftView,"translationX",0,-mTranslationDistance);

        ObjectAnimator rightTranslationAnimator=ObjectAnimator.ofFloat(
                mRightView,"translationX",0,mTranslationDistance);

        animatorSet=new AnimatorSet();

        animatorSet.setInterpolator(new DecelerateInterpolator());

        animatorSet.setDuration(mDuration);

        animatorSet.playTogether(leftTranslationAnimator,rightTranslationAnimator);

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                innerAnimator();
            }
        });

        animatorSet.start();
    }

    private void innerAnimator() {
        if(mIsStopAnimator){
            return;
        }
        ObjectAnimator leftTranslationAnimator=ObjectAnimator.ofFloat(
                mLeftView,"translationX",-mTranslationDistance,0);

        ObjectAnimator rightTranslationAnimator=ObjectAnimator.ofFloat(
                mRightView,"translationX",mTranslationDistance,0);

        animatorSet = new AnimatorSet();

        animatorSet.setInterpolator(new AccelerateInterpolator());

        animatorSet.setDuration(mDuration);

        animatorSet.playTogether(leftTranslationAnimator,rightTranslationAnimator);

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //左边颜色给中间，中间的给右边，右边的给左边
                leftColor=mLeftView.getColor();
                middleColor=mMiddleView.getColor();
                rightColor=mRightView.getColor();

                mMiddleView.exchange(leftColor);
                mRightView.exchange(middleColor);
                mLeftView.exchange(rightColor);

                expandAnimator();
            }
        });

        animatorSet.start();
    }

    private CircleView getCircleView(Context context) {
        CircleView circleView=new CircleView(context);
        LayoutParams params=new LayoutParams(dip2px(10),dip2px(10));
        params.addRule(CENTER_IN_PARENT);
        circleView.setLayoutParams(params);
        return circleView;
    }

    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,dip,getResources().getDisplayMetrics());
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(GONE);

        mRightView.clearAnimation();
        mLeftView.clearAnimation();
        mRightView.clearAnimation();
        animatorSet.cancel();

        ViewGroup parent= (ViewGroup) getParent();

        if(parent!=null){
            parent.removeView(this);
        }

        mIsStopAnimator=true;

    }
}
