package com.yifan.cardview;

import android.app.Activity;
import android.os.Bundle;

public class MyActivity extends Activity {

    private CardStackLayout mCardStackLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mCardStackLayout = (CardStackLayout) findViewById(R.id.cardStack);

        mCardStackLayout.setCardGap(180);
        mCardStackLayout.setCardGapBottom(15);

        mCardStackLayout.setAdapter(new MyCardStackAdapter(this));

    }
}
