package com.guojie.tools.uipopup;

import android.app.Activity;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;


import com.guojie.tools.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UIPopupMenu {
    private Activity mContext;
    private PopupWindow mPopupWindow;
    private View content;
    private LinearLayout layoutMain;
    private ScrollView scrollViewMain;

    private List<MenuItemEntity> listMenu = new ArrayList<>();

    private View anchor;
    private int xOff;
    private int yOff;
    private boolean animationEnable = true;
//  private boolean animationEnable = false;
    private boolean clickDismissible = true;

    private static final int DEFAULT_ANIM_STYLE = R.style.PopupMenuStyle;
    private int animationStyle = DEFAULT_ANIM_STYLE;

    private float alpha = 0.5f;
    private OnMenuItemClickListener mOnMenuItemClickListener;

    private int mTopBackgroundResource = R.drawable.popup_middle;
    private int mMiddleBackgroundResource = R.drawable.popup_middle;
    private int mBottomBackgroundResource = R.drawable.popup_middle;

    public UIPopupMenu(Activity context) {
        this.mContext = context;
        init();
    }

    private void init() {
        content = LayoutInflater.from(mContext).inflate(R.layout.layout_popup_menu, null);
        scrollViewMain = (ScrollView) content.findViewById(R.id.sv_main);
        layoutMain = (LinearLayout) content.findViewById(R.id.lLayout_main);

        mPopupWindow = new PopupWindow(mContext);
        mPopupWindow.setContentView(content);
        mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
//        mPopupWindow.setAnimationStyle(animationStyle);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
//        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1f);
            }
        });
    }

    public PopupWindow getPopupWindow() {
        return mPopupWindow;
    }

    public UIPopupMenu setMargin(int left, int top, int right, int bottom) {
        ViewGroup.MarginLayoutParams lp = new LinearLayout.LayoutParams(dip2ps(120), LinearLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(left, top, right, bottom);
        scrollViewMain.setLayoutParams(lp);
        return this;
    }

    public UIPopupMenu setPadding(int left, int top, int right, int bottom) {
        content.setPadding(left, top, right, bottom);
        return this;
    }

    public UIPopupMenu setBackgroundResource(int resId) {
        scrollViewMain.setBackgroundResource(resId);
        return this;
    }

    public UIPopupMenu setBackgroundColor(int color) {
        scrollViewMain.setBackgroundColor(color);
        return this;
    }

    public UIPopupMenu setTopMenuBackgroundResource(int resId) {
        this.mTopBackgroundResource = resId;
        return this;
    }

    public UIPopupMenu setMiddleMenuBackgroundResource(int resId) {
        this.mMiddleBackgroundResource = resId;
        return this;
    }

    public UIPopupMenu setBottomMenuBackgroundResource(int resId) {
        this.mBottomBackgroundResource = resId;
        return this;
    }

    /**
     * ??????????????????
     *
     * @param item
     * @return
     */
    public UIPopupMenu setMenuItem(MenuItemEntity item) {
        listMenu.add(item);
        return this;
    }

    /**
     * ??????????????????
     *
     * @param list
     * @return
     */
    public UIPopupMenu setMenuItems(List<MenuItemEntity> list) {
        listMenu.addAll(list);
        return this;
    }

    public UIPopupMenu setAlpha(float alpha) {
        this.alpha = alpha;
        if (mPopupWindow.isShowing()) {
            setBackgroundAlpha(alpha);
        }
        return this;
    }

    /**
     * ??????????????????
     *
     * @param animationEnable
     * @return
     */
    public UIPopupMenu setAnimationEnable(boolean animationEnable) {
        this.animationEnable = animationEnable;
        if (animationEnable) {
            mPopupWindow.setAnimationStyle(animationStyle);
        } else {
            mPopupWindow.setAnimationStyle(0);
        }
        return this;
    }

    /**
     * ????????????
     *
     * @param style
     * @return
     */
    public UIPopupMenu setAnimationStyle(int style) {
        this.animationStyle = style;
        if (animationEnable) {
            mPopupWindow.setAnimationStyle(animationStyle);
        }
        return this;
    }

    /**
     * ??????????????????item popupMenu dismiss
     *
     * @param clickDismissible
     * @return
     */
    public UIPopupMenu setClickDismissible(boolean clickDismissible) {
        this.clickDismissible = clickDismissible;
        return this;
    }

    public UIPopupMenu setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        this.mOnMenuItemClickListener = listener;
        return this;
    }

    public UIPopupMenu showAsDropDown(View anchor) {
        showAsDropDown(anchor, 0, 0);
        return this;
    }

    public UIPopupMenu showAsDropDown(View anchor, int xOff, int yOff) {
        show();
        if (!mPopupWindow.isShowing()) {
            mPopupWindow.showAsDropDown(anchor, xOff, yOff);
            setBackgroundAlpha(alpha);
        }
        return this;
    }

    public UIPopupMenu show() {
        setItems();
        if (animationEnable) {
            mPopupWindow.setAnimationStyle(animationStyle <= 0 ? DEFAULT_ANIM_STYLE : animationStyle);
        }
        if (!mPopupWindow.isShowing() && anchor != null) {
            mPopupWindow.showAsDropDown(anchor, xOff, yOff);
            setBackgroundAlpha(alpha);
        }
        return this;
    }

    /**
     * ??????items
     */
    private void setItems() {
        if (listMenu == null || listMenu.size() <= 0) {
            return;
        }
        layoutMain.removeAllViews();
        // ??????????????????
        for (int i = 0; i <= listMenu.size() - 1; i++) {
            final MenuItemEntity entity = listMenu.get(i);
            final int position = i;
            View viewItem = LayoutInflater.from(mContext).inflate(R.layout.item_popup_menu, null);

            ImageView imageView = (ImageView) viewItem.findViewById(R.id.iv_icon);
            TextView textView = (TextView) viewItem.findViewById(R.id.tv_choose_text);
            textView.setText(entity.text);
//            textView.setTextColor(entity.textColor);
            textView.setTextSize(entity.textSizeUnit, entity.textSize);
            imageView.setImageResource(entity.icon < 0 ? 0 : entity.icon);

//            viewItem.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    if(event.getAction() == MotionEvent.ACTION_UP){
//                        textView.setTextColor(Color.BLACK);
//                    }
//                    if(event.getAction() == MotionEvent.ACTION_DOWN){
//                        textView.setTextColor(Color.WHITE);
//                    }
//                    return false;
//                }
//            });
            viewItem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){
                        textView.setTextColor(Color.BLACK);
                    }
                }
            });
            // ????????????
            if (listMenu.size() == 1) {
                viewItem.setBackgroundResource(mMiddleBackgroundResource);
            } else {
                if (i == 0) {
                    viewItem.setBackgroundResource(mMiddleBackgroundResource);
                } else if (i < listMenu.size() - 1) {
                    viewItem.setBackgroundResource(mMiddleBackgroundResource);
                } else {
                    viewItem.setBackgroundResource(mMiddleBackgroundResource);
                }
            }
            viewItem.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            // ????????????
            if (entity.clickable) {
                viewItem.setClickable(true);
                viewItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mPopupWindow != null && mPopupWindow.isShowing() && clickDismissible) {
                            mPopupWindow.dismiss();
                        }
                        if (mOnMenuItemClickListener != null) {
                            mOnMenuItemClickListener.onMenuItemClick(position);
                        }
                    }
                });
            } else {
                viewItem.setClickable(false);
            }
            layoutMain.addView(viewItem);
        }
    }

    private void setBackgroundAlpha(float alpha) {
        final WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        lp.alpha = alpha;
        mContext.getWindow().setAttributes(lp);
    }

    /**
     *
     */
    public void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    public interface OnMenuItemClickListener {
        void onMenuItemClick(int position);
    }

    private int dip2ps(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,mContext.getResources().
                getDisplayMetrics());
    }
}
