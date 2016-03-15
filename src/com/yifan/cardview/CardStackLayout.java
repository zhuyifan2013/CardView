package com.yifan.cardview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class CardStackLayout extends LinearLayout {


    private float mCardGapBottom;
    private float mCardGap;
    private boolean mShowInitAnimation;

    //private OnCardSelected mOnCardSelectedListener = null;

    private CardStackAdapter mAdapter = null;

    public CardStackLayout(Context context) {
        super(context);
    }

    public CardStackLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardStackLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CardStackLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void handleArgs(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        final TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.CardStackLayout, defStyleAttr, defStyleRes);
        mCardGap = a.getDimension(R.styleable.CardStackLayout_card_gap, getResources().getDimension(R.dimen.card_gap));
        mCardGapBottom = a.getDimension(R.styleable.CardStackLayout_card_gap_bottom, getResources().getDimension(R.dimen.card_gap_bottom));
        a.recycle();
    }

    /**
     * @return adapter of type {@link CardStackAdapter} that is set for this view.
     */
    public CardStackAdapter getAdapter() {
        return mAdapter;
    }

    /**
     * Set the adapter for this {@link CardStackLayout}
     * @param adapter Should extend {@link CardStackAdapter}
     */
    public void setAdapter(CardStackAdapter adapter) {
        this.mAdapter = adapter;
        mAdapter.setAdapterParams(this);
        for (int i = 0; i < mAdapter.getCount(); i++) {
            mAdapter.addView(i);
        }
    }

    /**
     * @return the gap (in pixels) between two consecutive cards
     */
    public float getCardGap() {
        return mCardGap;
    }

    /**
     * Set the gap (in pixels) between two consecutive cards
     */
    public void setCardGap(float mCardGap) {
        this.mCardGap = mCardGap;
    }

    /**
     * @return gap between the two consecutive cards when collapsed to the bottom of the screen
     */
    public float getCardGapBottom() {
        return mCardGapBottom;
    }

    public void setCardGapBottom(float mCardGapBottom) {
        this.mCardGapBottom = mCardGapBottom;
    }

    /**
     * @return true if a card is selected, false otherwise
     */
    public boolean isCardSelected() {
        return mAdapter.isCardSelected();
    }

    /**
     * Intimates the implementing class about the selection of a card
     */
    public interface OnCardSelected {
        void onCardSelected(View v, int position);
    }

}
