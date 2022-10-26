package com.guojie.tools.tabmenu;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.guojie.tools.R;

import java.util.ArrayList;
import java.util.List;

public class TabMenu extends LinearLayout {

    int mItemCount; //子布局个数
    int mTextSize;
    int mIconSize;
    int mTextColor;
    int mTextSelectColor;
    int mItemBg;
    int mSelectedItem; //选中中item
    Context mContext;
    private List<ImageView> mImageViews;
    private List<TextView> mTextViews;

    private List<View> mViews;
    private int[] mIcons = new int[10];
    private int[] mSelectIcons = new int[10];
    private String[] mTexts = new String[10];
    private ViewPager mViewPager;
    private TabViewPagerListener mTabViewPagerListener;

    public TabMenu(Context context){
        this(context,null);
        Log.i("lzq","TabMenu1");
    }

    public TabMenu(Context context , AttributeSet attributeSet){
        this(context,attributeSet,0);
        Log.i("lzq","TabMenu2");
    }

    public TabMenu(Context context , AttributeSet attributeSet , int defStyle){
        super(context,attributeSet,defStyle);
        Log.i("lzq","TabMenu");
        this.setOrientation(LinearLayout.HORIZONTAL);

        mContext = context;
        mImageViews = new ArrayList<>();
        mTextViews = new ArrayList<>();
        mViews = new ArrayList<>();
        //获取自定义的属性
        TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.TabMenu,defStyle,0);
        mItemCount = a.getInt(R.styleable.TabMenu_item_count,4);
        mTextSize = a.getDimensionPixelSize(R.styleable.TabMenu_android_textSize,0);
        mIconSize = (int)a.getDimension(R.styleable.TabMenu_icon_size, TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30,
                        getResources().getDisplayMetrics()));
        mTextColor = a.getColor(R.styleable.TabMenu_text_color,0x777777);
        mTextSelectColor = a.getColor(R.styleable.TabMenu_text_select_color,0x222222);
        mItemBg = a.getResourceId(R.styleable.TabMenu_item_bg,0);
        mSelectedItem = a.getInt(R.styleable.TabMenu_selected,0);
        a.recycle();
        initItemView();
        mTabViewPagerListener = new TabViewPagerListener();
    }

    public void initItemView(){
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        //循环添加item
        for(int i = 0; i < mItemCount ; i++){
            //通过LayoutInflater加载item布局
            View view = mInflater.inflate(R.layout.tab_menu_item,this,false);
            view.setClickable(true);
            final ImageView imageView = (ImageView)view.findViewById(R.id.tab_item_image);
            final TextView textView = (TextView)view.findViewById(R.id.tab_item_text);
            textView.setTextSize(mTextSize);
            textView.setTextColor(mTextColor);
            //item通过LayoutParams向父布局传递自己的意愿，设置布局属性
            LayoutParams param = new LayoutParams(
                    0, LayoutParams.MATCH_PARENT);
            //param.gravity = Gravity.CENTER_VERTICAL;
            param.weight = 1;
            view.setLayoutParams(param);
            addView(view,i);//通过add，添加item
            if(i==0){
                view.setBackgroundColor(Color.BLUE);
            }
            mImageViews.add(imageView);
            mTextViews.add(textView);
            mViews.add(view);
            final int j = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    setSelectedItem(j);
                    setImageBg();
                    setTextColor();
                    setViewBackground();
                    mViewPager.setCurrentItem(j);

                    Log.d("tag","i="+j);
//                    if(mOnChooseTabListener!=null){
//                        mOnChooseTabListener.chooseTab(j);
//                    }
                }
            });
        }
    }

    private void setViewBackground() {

        for (int i = 0; i < mItemCount; i++) {
            if (i > mIcons.length - 1) {
                break;
            }
            if(mSelectedItem == i){
               mViews.get(i).setBackgroundColor(Color.BLUE);
            }else {
                mViews.get(i).setBackgroundColor(Color.WHITE);
            }
        }
    }


    /*
     *设置选中的item
     */
    public void setSelectedItem(int selected){
        mSelectedItem = selected;
    }

    /*
     *对外获取图标资源，设置图标背景
     */
    public void setImageBg(int[] icons ,int[] selectIcons){
        if(icons != null && selectIcons != null) {
            for (int i = 0; i < mItemCount; i++) {
                if (i > mIcons.length - 1) {
                    break;
                }
                mIcons[i] = icons[i];
                mSelectIcons[i] = selectIcons[i];
                if(mSelectedItem == i){
                    mImageViews.get(i).setBackgroundResource(mSelectIcons[i]);
                }else {
                    mImageViews.get(i).setBackgroundResource(mIcons[i]);
                }
            }
        }
    }

    /*
     *设置图标背景
     */
    public void setImageBg(){
        for (int i = 0; i < mItemCount; i++) {
            if (i > mIcons.length - 1) {
                break;
            }
            if(mSelectedItem == i){
                mImageViews.get(i).setBackgroundResource(mSelectIcons[i]);
            }else {
                mImageViews.get(i).setBackgroundResource(mIcons[i]);
            }
        }
    }

    public void setTexts(String[] texts){
        if(texts != null){
            for(int i = 0 ; i <mItemCount;i++){
                if (i > mTexts.length - 1) {
                    break;
                }
                mTexts[i] = texts[i];
                mTextViews.get(i).setText(mTexts[i]);
            }
        }
    }

    public void setTextColor(int color , int selectColor){
        mTextColor = color;
        mTextSelectColor = selectColor;
    }

    public void setTextColor(){
        for(int i = 0 ; i <mItemCount;i++){
            if (i > mTexts.length - 1) {
                break;
            }
            if(mSelectedItem == i){
                mTextViews.get(i).setTextColor(mTextSelectColor);
            }else{
                mTextViews.get(i).setTextColor(mTextColor);
            }

        }
    }

    /*
     *得到viewPager，并设置viewPager的滑动监听
     */
    public void setViewPager( ViewPager viewPager){
        mViewPager = viewPager;
        mViewPager.setOnPageChangeListener(mTabViewPagerListener);
    }


    /*
     *OnPageChangeListener
     */
    private class TabViewPagerListener implements ViewPager.OnPageChangeListener{

        public TabViewPagerListener(){

        }
        @Override
        public void onPageScrollStateChanged(int i) {

        }

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            setSelectedItem(i);
            setImageBg();
            setTextColor();
            setViewBackground();
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }




//    private OnChooseTabListener mOnChooseTabListener;
//    public  interface OnChooseTabListener{
//        void chooseTab(int i);
//    }
//    public void setOnChooseTabListener(OnChooseTabListener onChooseTabListener){
//        this.mOnChooseTabListener = onChooseTabListener;
//    }
}
