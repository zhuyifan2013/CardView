package com.yifan.cardview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class MyCardStackAdapter extends CardStackAdapter {

    public MyCardStackAdapter(Context context) {
        super(context);
    }

    @Override
    public View createView(int position, ViewGroup container) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
