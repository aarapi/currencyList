package com.example.fragments.anim;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ResizeAnimation extends Animation {
    private int _duration = 1000;
    private int _toW;
    private int _toH;
    private int _initW;
    private int _initH;
    private View view;
    private boolean _resizeW = true;
    private boolean _resizeH = true;
    private LayoutParams params;

    public ResizeAnimation(int p_toW, View p_view) {
        this(p_view, p_toW, -1);
        _resizeH = false;
    }

    public ResizeAnimation(View p_view, int p_toH) {
        this(p_view, -1, p_toH);
        _resizeW = false;
    }

    public ResizeAnimation(View p_view, int p_toW, int p_toH) {
        view = p_view;
        _initW = view.getWidth();
        _initH = view.getHeight();
        if (p_toW == LayoutParams.WRAP_CONTENT) {
            view.getLayoutParams().width = LayoutParams.WRAP_CONTENT;
            int _desiredWidth = MeasureSpec.makeMeasureSpec(view.getWidth(),
                    MeasureSpec.UNSPECIFIED);
            view.measure(_desiredWidth, MeasureSpec.UNSPECIFIED);
            _toW = view.getMeasuredWidth();
            view.getLayoutParams().width = _initW;
        } else {
            _toW = p_toW;
        }
        if (p_toH == LayoutParams.WRAP_CONTENT) {
            view.getLayoutParams().height = LayoutParams.WRAP_CONTENT;
            int _desiredHeight = MeasureSpec.makeMeasureSpec(
                    MeasureSpec.UNSPECIFIED, view.getHeight());
            view.measure(MeasureSpec.UNSPECIFIED, _desiredHeight);
            _toH = view.getMeasuredHeight();
            view.getLayoutParams().height = _initH;
        } else {
            _toH = p_toH;
        }
        params = view.getLayoutParams();
        setDuration(_duration);
    }

    @Override
    protected void applyTransformation(float p_interpolatedTime,
                                       Transformation t) {
        if (p_interpolatedTime == 1) {
            if (_resizeW)
                params.width = _toW;
            if (_resizeH)
                params.height = _toH;
        } else if (p_interpolatedTime == 0) {
            if (_resizeW && view.getWidth() == 0)
                params.width = 1;
            if (_resizeH && view.getHeight() == 0)
                params.height = 1;
        } else {
            if (_resizeW) {
                int _newW = (int) (_toW * p_interpolatedTime + (_initW * (1 - p_interpolatedTime)));
                params.width = _newW;
            }
            if (_resizeH) {
                int _newH = (int) (_toH * p_interpolatedTime + (_initH * (1 - p_interpolatedTime)));
                params.height = _newH;
            }
        }
        view.requestLayout();
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}