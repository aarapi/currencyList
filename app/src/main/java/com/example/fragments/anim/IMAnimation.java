package com.example.fragments.anim;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Interpolator;

public class IMAnimation {

    public static void rotate3d(View p_view1, View p_view2) {
        rotate3d(p_view1, p_view2, null);
    }

    public static void rotate3d(View p_view1, View p_view2,
                                AnimationListener p_listener) {
        Rotate3dAnimation.applyRotation(p_view1, p_view2, 0, 90, p_listener);
    }

    public static FadeAnimation getFadeAnim(float p_fromAlpha, float p_toAlpha) {
        return new FadeAnimation(p_fromAlpha, p_toAlpha);
    }

    public static FadeAnimation getFadeOutAnim() {
        return getFadeAnim(1, 0);
    }

    public static FadeAnimation getFadeInAnim() {
        return getFadeAnim(0, 1);
    }

    public static void fade(View p_view, float p_fromAlpha, float p_toAlpha,
                            int p_duration, AnimationListener p_listener) {
        FadeAnimation anim = new FadeAnimation(p_fromAlpha, p_toAlpha,
                p_listener);
        anim.setDuration(p_duration);
        p_view.startAnimation(anim);
    }

    public static void fade(View p_view, float p_fromAlpha, float p_toAlpha,
                            AnimationListener p_listener) {
        p_view.startAnimation(new FadeAnimation(p_fromAlpha, p_toAlpha,
                p_listener));
    }

    public static void fade(View p_view, float p_fromAlpha, float p_toAlpha) {
        fade(p_view, p_fromAlpha, p_toAlpha, null);
    }

    public static void fadeIn(View p_view, AnimationListener p_listener) {
        fade(p_view, 0, 1, p_listener);
    }

    public static void fadeIn(View p_view, int p_duration,
                              AnimationListener p_listener) {
        fade(p_view, 0, 1, p_duration, p_listener);
    }

    public static void fadeOut(View p_view, AnimationListener p_listener) {
        fade(p_view, 1, 0, p_listener);
    }

    public static void fadeOut(View p_view, int p_duration,
                               AnimationListener p_listener) {
        fade(p_view, 1, 0, p_duration, p_listener);
    }

    public static void expand(View p_view, int p_duration) {
        p_view.startAnimation(new ExpandCollapseAnimation(p_view, ExpandCollapseAnimation.Type.EXPAND,
                p_duration));
    }

    public static void collapse(View p_view, int p_duration,
                                AnimationListener p_listener) {
        Animation anim = new ExpandCollapseAnimation(p_view, ExpandCollapseAnimation.Type.COLLAPSE,
                p_duration);
        anim.setAnimationListener(p_listener);
        p_view.startAnimation(anim);
    }

    public static void collapse(View p_view, int p_duration) {
        p_view.startAnimation(new ExpandCollapseAnimation(p_view,
                ExpandCollapseAnimation.Type.COLLAPSE, p_duration));
    }

    public static void expand(View p_view, AnimationListener p_listener) {
        Animation anim = new ExpandCollapseAnimation(p_view, ExpandCollapseAnimation.Type.EXPAND);
        anim.setAnimationListener(p_listener);
        p_view.startAnimation(anim);
    }

    public static void expand(View p_view, int p_duration,
                              AnimationListener p_listener) {
        Animation anim = new ExpandCollapseAnimation(p_view, ExpandCollapseAnimation.Type.EXPAND,
                p_duration);
        anim.setAnimationListener(p_listener);
        p_view.startAnimation(anim);
    }

    public static void collapse(View p_view, AnimationListener p_listener) {
        Animation anim = new ExpandCollapseAnimation(p_view, ExpandCollapseAnimation.Type.COLLAPSE);
        anim.setAnimationListener(p_listener);
        p_view.startAnimation(anim);
    }

    public static Animation resize(View p_view, int p_toW, int p_toH) {
        ResizeAnimation anim = new ResizeAnimation(p_view, p_toW, p_toH);
        p_view.startAnimation(anim);
        return anim;
    }

    public static Animation resizeW(View p_view, int p_toW) {
        ResizeAnimation anim = new ResizeAnimation(p_view, p_toW);
        p_view.startAnimation(anim);
        return anim;
    }

    public static Animation resizeH(View p_view, int p_toH) {
        ResizeAnimation anim = new ResizeAnimation(p_view, p_toH);
        p_view.startAnimation(anim);
        return anim;
    }

    public static void resizeH(View p_view, int p_toH, int p_duration,
                               Interpolator p_intp, AnimationListener p_listener) {
        ResizeAnimation anim = new ResizeAnimation(p_view, p_toH);
        anim.setDuration(p_duration);
        anim.setInterpolator(p_intp);
        anim.setAnimationListener(p_listener);
        p_view.startAnimation(anim);
    }
}