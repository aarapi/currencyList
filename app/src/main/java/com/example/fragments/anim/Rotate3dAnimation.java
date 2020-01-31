package com.example.fragments.anim;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;

public class Rotate3dAnimation extends Animation {
    private static final int DEFAULT_DURATION = 350;
    private static final int DEPTH_Z = 600;

    private final float _fromDegrees;
    private final float _toDegrees;
    private final float _centerX;
    private final float _centerY;
    private final float _depthZ;
    private final boolean _reverse;
    private Camera mCamera;
    private AnimationListener listener;

    public Rotate3dAnimation(float p_fromDegrees, float p_toDegrees,
                             float p_centerX, float p_centerY, float p_depthZ, boolean p_reverse) {
        _fromDegrees = p_fromDegrees;
        _toDegrees = p_toDegrees;
        _centerX = p_centerX;
        _centerY = p_centerY;
        _depthZ = p_depthZ;
        _reverse = p_reverse;
    }

    public void addAnimationListener(AnimationListener p_listener) {
        listener = p_listener;
    }

    @Override
    public void initialize(int width, int height, int parentWidth,
                           int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float degrees = _fromDegrees
                + ((_toDegrees - _fromDegrees) * interpolatedTime);

        final Matrix matrix = t.getMatrix();
        mCamera.save();
        if (_reverse) {
            mCamera.translate(0.0f, 0.0f, _depthZ * interpolatedTime);
        } else {
            mCamera.translate(0.0f, 0.0f, _depthZ * (1 - interpolatedTime));
        }
        mCamera.rotateY(-degrees);
        mCamera.getMatrix(matrix);
        mCamera.restore();

        matrix.preTranslate(-_centerX, -_centerY);
        matrix.postTranslate(_centerX, _centerY);
    }

    public static void applyRotation(final View p_view1, final View p_view2,
                                     float p_start, float p_end, final AnimationListener p_listener) {
        final View container = (View) p_view1.getParent();
        final float centerX = container.getWidth() / 2.0f;
        final float centerY = container.getHeight() / 2.0f;

        final Rotate3dAnimation rotation = new Rotate3dAnimation(p_start,
                p_end, centerX, centerY, DEPTH_Z, true);
        rotation.setDuration(DEFAULT_DURATION);
        rotation.setFillAfter(false);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                container.post(new Anim2(container, p_view1, p_view2,
                        p_listener));
            }
        });
        container.startAnimation(rotation);
    }

    static class Anim2 implements
            Runnable {
        private View container;
        private View view1;
        private View view2;
        private AnimationListener listener;

        public Anim2(View p_container, View p_view1, View p_view2,
                     AnimationListener p_listener) {
            container = p_container;
            view1 = p_view1;
            view2 = p_view2;
            listener = p_listener;
        }

        public void run() {
            final float centerX = container.getWidth() / 2.0f;
            final float centerY = container.getHeight() / 2.0f;

            view1.setVisibility(View.GONE);
            view2.setVisibility(View.VISIBLE);
            view2.requestFocus();

            Rotate3dAnimation anim2 = new Rotate3dAnimation(-90, 0, centerX,
                    centerY, DEPTH_Z, false);
            anim2.setDuration(DEFAULT_DURATION);
            anim2.setFillAfter(true);
            anim2.setInterpolator(new DecelerateInterpolator());
            anim2.setAnimationListener(listener);
            container.startAnimation(anim2);

        }
    }
}
