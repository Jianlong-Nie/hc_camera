package com.reactlibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;

public class SurfaceView extends LinearLayout {
    private int mRatioWidth = 0;
    private int mRatioHeight = 0;
    private LayoutParams params;
    public AutoFitTextureView autoFitTextureView = null;

    @SuppressLint("ResourceType")
    public SurfaceView(Context context) {
        super(context);



        setOrientation(LinearLayout.VERTICAL);

        autoFitTextureView = new AutoFitTextureView(context);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        autoFitTextureView.setSelfSize(dm.widthPixels, dm.heightPixels - 260);
        addView(autoFitTextureView);

        setBackgroundColor(Color.BLUE);

//        ViewTreeObserver vto2 = getViewTreeObserver();
//        vto2.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            public boolean onPreDraw() {
//                int height = getMeasuredHeight();
//                int width = getMeasuredWidth();
//                Log.d("daf",  "" + height + width);
//                return true;
//            }
//        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        int count = getChildCount();
//        for(int i=0;i<count;i++){
//            View view = getChildAt(i);
//            view.measure(100,100);
//            view.layout(0,0,200,200);
//        }
    }

    public void setAspectRatio(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Size cannot be negative.");
        }
        mRatioWidth = width;
        mRatioHeight = height;
        autoFitTextureView.requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        System.out.println("onMeasure 我被调用了"+System.currentTimeMillis());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        System.out.println("onDraw 我被调用了" + System.currentTimeMillis());
    }
}
