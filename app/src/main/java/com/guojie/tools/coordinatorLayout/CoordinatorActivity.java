package com.guojie.tools.coordinatorLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.AppBarLayout;
import com.guojie.tools.R;
import com.guojie.tools.StatusBarUtils;

public class CoordinatorActivity extends AppCompatActivity  implements AppBarLayout.OnOffsetChangedListener{

    private static final int PERCENTAGE_TO_SHOW_IMAGE = 20;
    private View mFab;
    private int mMaxScrollSize;
    private boolean mIsImageHidden;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);

        StatusBarUtils.setStatusTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        mFab = findViewById(R.id.flexible_example_fab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.flexible_example_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        AppBarLayout appbar = (AppBarLayout) findViewById(R.id.flexible_example_appbar);
        appbar.addOnOffsetChangedListener(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int currentScrollPercentage = (Math.abs(verticalOffset)) * 100
                / mMaxScrollSize;

        if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
            if (!mIsImageHidden) {
                mIsImageHidden = true;

                ViewCompat.animate(mFab).scaleY(0).scaleX(0).start();
            }
        }

        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
            if (mIsImageHidden) {
                mIsImageHidden = false;
                ViewCompat.animate(mFab).scaleY(1).scaleX(1).start();
            }
        }
    }
}