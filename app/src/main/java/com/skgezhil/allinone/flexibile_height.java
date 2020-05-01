package com.skgezhil.allinone;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

public class flexibile_height extends android.support.v7.widget.AppCompatImageView {

    public flexibile_height(@NonNull Context context) {
        super(context);
    }

    public flexibile_height(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public flexibile_height(@NonNull Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable d = getDrawable();
        if (d != null) {
            int w = MeasureSpec.getSize(widthMeasureSpec);
            int h = w * d.getIntrinsicHeight() / d.getIntrinsicWidth();
            setMeasuredDimension(w, h);
        }
        else super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
