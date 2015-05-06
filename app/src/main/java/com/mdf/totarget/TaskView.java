package com.mdf.totarget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;


public class TaskView extends ViewGroup {
    private String mExampleString;
    private int mExampleColor = Color.RED;
    private float mExampleDimension = 0;
    private Drawable mExampleDrawable;

    private EditText text;
    private float mTextWidth;
    private float mTextHeight;

    public TaskView(Context context) {
        super(context);
        init(null, 0);
    }

    public TaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public TaskView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.TaskView, defStyle, 0);

        mExampleString = a.getString(R.styleable.TaskView_exampleString);
        mExampleColor = a.getColor(R.styleable.TaskView_exampleColor, mExampleColor);

        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(R.styleable.TaskView_exampleDimension,
                mExampleDimension);

        if (a.hasValue(R.styleable.TaskView_exampleDrawable)) {
            mExampleDrawable = a.getDrawable(R.styleable.TaskView_exampleDrawable);
            mExampleDrawable.setCallback(this);
        }

        LayoutInflater inflater = getLayoutInflater();
        inflater.inflate(R.layout.task_view, this, true);

        text = (EditText)findViewById(R.id.name);
        text.setText(mExampleString);

        a.recycle();

        invalidateTextPaintAndMeasurements();
    }

    private LayoutInflater getLayoutInflater() {
        if (getContext() instanceof Activity) {
            return ((Activity)getContext()).getLayoutInflater();
        }
        return LayoutInflater.from(getContext());
    }

    /// Update TextPaint and text measurements from attributes
    private void invalidateTextPaintAndMeasurements() {
        text.setTextSize(mExampleDimension);
        text.setTextColor(mExampleColor);
    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getExampleString() {
        return mExampleString;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setExampleString(String exampleString) {
        mExampleString = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getExampleColor() {
        return mExampleColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
    public void setExampleColor(int exampleColor) {
        mExampleColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.
     */
    public float getExampleDimension() {
        return mExampleDimension;
    }

    /**
     * Sets the view's example dimension attribute value. In the example view, this dimension
     * is the font size.
     *
     * @param exampleDimension The example dimension attribute value to use.
     */
    public void setExampleDimension(float exampleDimension) {
        mExampleDimension = exampleDimension;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable() {
        return mExampleDrawable;
    }

    /**
     * Sets the view's example drawable attribute value. In the example view, this drawable is
     * drawn above the text.
     *
     * @param exampleDrawable The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }
}
