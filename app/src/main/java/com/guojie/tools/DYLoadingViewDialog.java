package com.guojie.tools;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DYLoadingViewDialog extends Dialog {
    DYLoadingView dyLoadingView;
    public DYLoadingViewDialog(@NonNull Context context) {
        this(context,0);
    }

    public DYLoadingViewDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.MyDialog);
    }

    protected DYLoadingViewDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dyloading_dialog);

        setCanceledOnTouchOutside(false);
        dyLoadingView = findViewById(R.id.dyLoadingView);
        dyLoadingView.start();
    }

    @Override
    public void dismiss() {
        dyLoadingView.stop();
        super.dismiss();
    }
}
