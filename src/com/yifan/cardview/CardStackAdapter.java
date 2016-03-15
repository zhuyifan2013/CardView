package com.yifan.cardview;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public abstract class CardStackAdapter implements View.OnTouchListener {

    public static final int INVALID_CARD_POSITION = -1;

    private View[] mCardViews;

    private int mSelectedCardPosition = INVALID_CARD_POSITION;
    private int mCardPaddingInternal = 0;
    private int mFullCardHeight;
    private int mParentPaddingTop = 0;

    private final int mScreenHeight;

    // Settings for the adapter from layout
    private float mCardGapBottom;
    private float mCardGap;

    private CardStackLayout mParent;

    public CardStackAdapter(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        mScreenHeight = dm.heightPixels;
        mCardViews = new View[getCount()];
    }

    public abstract View createView(int position, ViewGroup container);
    public abstract int getCount();

    void addView(final int position) {
        View root = createView(position, mParent);
        root.setOnTouchListener(this);
        //root.setTag(R.id.cardstack_internal_position_tag, position);
        root.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        mCardPaddingInternal = root.getPaddingTop();

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mFullCardHeight);
        root.setLayoutParams(lp);
//        if (mShowInitAnimation) {
//            root.setY(getCardFinalY(position));
//            setScreenTouchable(false);
//        } else {
//            root.setY(getCardOriginalY(position) - mParentPaddingTop);
//            setScreenTouchable(true);
//        }
        mCardViews[position] = root;
        mParent.addView(root);
    }

    public View getCardView(int position) {
        return mCardViews[position];
    }

    void setAdapterParams(CardStackLayout cardStackLayout) {
        mParent = cardStackLayout;
        mCardGapBottom = cardStackLayout.getCardGapBottom();
        mCardGap = cardStackLayout.getCardGap();
        mParentPaddingTop = cardStackLayout.getPaddingTop();
        mFullCardHeight = (int) (mScreenHeight - getCount() * mCardGapBottom);
    }

    /**
     * Returns false if all the cards are in their initial position i.e. no card is selected
     *
     * Returns true if the {@link CardStackLayout} has a card selected and all other cards are
     * at the bottom of the screen.
     *
     * @return true if any card is selected, false otherwise
     */
    public boolean isCardSelected() {
        return mSelectedCardPosition != INVALID_CARD_POSITION;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
