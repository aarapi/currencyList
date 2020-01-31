package com.example.fragments.anim;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ExpandCollapseAnimation extends Animation {
    private static final int DEFAULT_DURATION = 100;

    public enum Type {
        EXPAND, COLLAPSE
    }

    private View view;
    private int _viewHeight;
    private Type type;

    public ExpandCollapseAnimation(View p_view, Type p_type) {
        this(p_view, p_type, DEFAULT_DURATION);
    }

    public ExpandCollapseAnimation(View p_view, Type p_type, int p_duration) {
        view = p_view;
        type = p_type;
        if (type == Type.EXPAND) {
            view.getLayoutParams().height = LayoutParams.WRAP_CONTENT;
            int _desiredHeight = MeasureSpec.makeMeasureSpec(view.getHeight(),
                    MeasureSpec.UNSPECIFIED);
            view.measure(MeasureSpec.UNSPECIFIED, _desiredHeight);
            _viewHeight = view.getMeasuredHeight();
            view.getLayoutParams().height = 1;
            view.setVisibility(View.VISIBLE);
        } else
            _viewHeight = view.getMeasuredHeight();

        setDuration(p_duration);
        view.requestLayout();
    }

    @Override
    protected void applyTransformation(float p_interpolatedTime,
                                       Transformation p_transformation) {
        if (type == Type.EXPAND) {
            int _targetHeight = 1;
            if (p_interpolatedTime != 1)
                _targetHeight = (int) (_viewHeight * p_interpolatedTime);
            else
                _targetHeight = LayoutParams.WRAP_CONTENT;
            view.getLayoutParams().height = _targetHeight;
            view.requestLayout();
        } else {
            if (p_interpolatedTime == 1) {
                view.getLayoutParams().height = 0;
                view.setVisibility(View.INVISIBLE);
            } else
                view.getLayoutParams().height = _viewHeight
                        - (int) (_viewHeight * p_interpolatedTime);
            view.requestLayout();
        }
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
