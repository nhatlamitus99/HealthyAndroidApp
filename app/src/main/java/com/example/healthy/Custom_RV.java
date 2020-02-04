package com.example.healthy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.AnimRes;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

class AnimatedRecyclerView extends RecyclerView  {

    private int orientation = RecyclerView.HORIZONTAL;
    private boolean reverse = false;
    private int animationDuration = 600;
    private int layoutManagerType = LayoutManagerType.LINEAR;
    private int columns = 1;
    @AnimRes
    private int animation = R.anim.custom_layout_anim;
    private LayoutAnimationController animationController;

    public AnimatedRecyclerView(Context context, int orientation, boolean reverse,
                                int animationDuration, int layoutManagerType, int columns,
                                int animation, LayoutAnimationController animationController) {
        super(context);
        this.orientation = orientation;
        this.reverse = reverse;
        this.animationDuration = animationDuration;
        this.layoutManagerType = layoutManagerType;
        this.columns = columns;
        this.animation = animation;
        this.animationController = animationController;
        init(context, null);
    }

    public AnimatedRecyclerView(Context context) {
        super(context);
        init(context, null);
    }

    public AnimatedRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AnimatedRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @SuppressLint("Recycle")
    private void init(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AnimatedRecyclerView, 0, 0);

        orientation = typedArray.getInt(R.styleable.AnimatedRecyclerView_layoutManagerOrientation, orientation);
        reverse = typedArray.getBoolean(R.styleable.AnimatedRecyclerView_layoutManagerReverse, reverse);
        animationDuration = typedArray.getInt(R.styleable.AnimatedRecyclerView_animationDuration, animationDuration);
        layoutManagerType = typedArray.getInt(R.styleable.AnimatedRecyclerView_layoutManagerType, layoutManagerType);
        columns = typedArray.getInt(R.styleable.AnimatedRecyclerView_gridLayoutManagerColumns, columns);
        animation = typedArray.getResourceId(R.styleable.AnimatedRecyclerView_layoutAnimation, -1);

        if (animationController == null)
            animationController = animation != -1
                    ? AnimationUtils.loadLayoutAnimation(getContext(), animation)
                    : AnimationUtils.loadLayoutAnimation(getContext(), R.anim.custom_layout_anim);

        animationController.getAnimation().setDuration(animationDuration);
        setLayoutAnimation(animationController);

        if (layoutManagerType == LayoutManagerType.LINEAR)
            setLayoutManager(new LinearLayoutManager(context, orientation, reverse));
        else if (layoutManagerType == LayoutManagerType.GRID)
            setLayoutManager(new GridLayoutManager(context, columns, orientation, reverse));
    }

    public void notifyDataSetChanged() {
        getAdapter().notifyDataSetChanged();
        scheduleLayoutAnimation();
    }

    public @interface LayoutManagerType {
        int LINEAR = 0;
        int GRID = 1;
    }
}