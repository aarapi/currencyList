package com.example.fragments.anim;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class FadeAnimation extends Animation {
    public final int INTERPOLATOR_TYPE_ACCELERATE = 0;
    public final int INTERPOLATOR_TYPE_DECELERATE = 1;

    private final int DEFAULT_INTERPOLATOR = INTERPOLATOR_TYPE_ACCELERATE;
    private final int DEFAULT_DURATION = 500;

    private boolean _animFinished = false;
    private float _fromAlpha;
    private float _toAlpha;

    public FadeAnimation(float p_fromAlpha, float p_toAlpha) {
        this(p_fromAlpha, p_toAlpha, null);
    }

    public FadeAnimation(float p_fromAlpha, float p_toAlpha,
                         AnimationListener p_listener) {
        _fromAlpha = p_fromAlpha;
        _toAlpha = p_toAlpha;
        setDuration(DEFAULT_DURATION);
        setInterpolator(new AccelerateInterpolator());
        setFillAfter(true);
        setAnimationListener(p_listener);
    }

    @Override
    protected void applyTransformation(float p_intpTime, Transformation p_trans) {
        float _alpha = _toAlpha * p_intpTime + _fromAlpha * (1 - p_intpTime);
        p_trans.setAlpha(_alpha);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }

    @Override
    public boolean willChangeTransformationMatrix() {
        return true;
    }
}
