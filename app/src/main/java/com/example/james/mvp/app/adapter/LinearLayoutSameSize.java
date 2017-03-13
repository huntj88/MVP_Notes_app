package com.example.james.mvp.app.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by James on 3/13/2017.
 */

public class LinearLayoutSameSize extends LinearLayout {
    public LinearLayoutSameSize(Context context) {
        super(context);
    }

    public LinearLayoutSameSize(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearLayoutSameSize(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LinearLayoutSameSize(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Set a square layout.
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
