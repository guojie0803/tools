package com.guojie.tools.loginView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.guojie.tools.R;

/**
 * 登录动画
 */
public class LoginView extends LinearLayout {

    private ShapeView shapeView;
    private View loadView;

    private int mTranslation=dip2ps(82);
    private int mDuration=350;

    private boolean mIsStopAnimator=false;


    public LoginView(Context context) {
        this(context,null);
    }

    public LoginView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoginView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initLayout();
    }

    private void initLayout() {
        inflate(getContext(), R.layout.ui_loading_view,this);

        shapeView=findViewById(R.id.shapeView);
        loadView=findViewById(R.id.loadingView);

        post(new Runnable() {
            @Override
            public void run() {
                startFallAnimator();
            }
        });


    }

    private void startFallAnimator() {
        if(mIsStopAnimator){
            return;
        }
        ObjectAnimator translationAnimator=ObjectAnimator.ofFloat(shapeView,
                "translationY",0,mTranslation);

        ObjectAnimator scaleAnimator=ObjectAnimator.ofFloat(loadView,
                "scaleX",1.0f,0.3f);

        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.playTogether(translationAnimator,scaleAnimator);

        animatorSet.setDuration(500);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startUpAnimator();
                shapeView.exchange();
            }
        });

        animatorSet.start();

    }

    private void startUpAnimator() {
        if(mIsStopAnimator){
            return;
        }
        ObjectAnimator translationAnimator=ObjectAnimator.ofFloat(shapeView,
                "translationY",mTranslation,0);

        ObjectAnimator scaleAnimator=ObjectAnimator.ofFloat(loadView,
                "scaleX",0.3f,1f);

        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.playTogether(translationAnimator,scaleAnimator);

        animatorSet.setDuration(mDuration);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startFallAnimator();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                startRotationAnimator();
            }

        });

        animatorSet.start();
    }

    private void startRotationAnimator() {
        ObjectAnimator translationAnimator=null;
        switch (shapeView.getCurrentShape()){
            case Circle:
                translationAnimator=ObjectAnimator.ofFloat(shapeView,
                        "rotation",0,180);
                break;
            case Square:
            case Triangle:
                translationAnimator=ObjectAnimator.ofFloat(shapeView,
                        "rotation",0,-120);
                break;
        }
        translationAnimator.setDuration(mDuration);
        translationAnimator.setInterpolator(new DecelerateInterpolator());
        translationAnimator.start();
    }

    private int dip2ps(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,getResources().
                getDisplayMetrics());
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(INVISIBLE);

        shapeView.clearAnimation();
        loadView.clearAnimation();

        ViewGroup parent= (ViewGroup) getParent();

        if(parent!=null){
            parent.removeView(this);
//            parent.removeAllViews();
        }

        mIsStopAnimator=true;

    }
}
