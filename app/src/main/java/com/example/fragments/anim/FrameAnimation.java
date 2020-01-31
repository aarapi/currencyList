package com.example.fragments.anim;

import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class FrameAnimation {
    private List<BitmapDrawable> bmps;
    private View view;
    private boolean _stopped = false;
    private int _counter = 0;
    private int _duration = 200;

    public FrameAnimation(View p_view) {
        view = p_view;
        bmps = new ArrayList<BitmapDrawable>();
    }

    public void addFrame(BitmapDrawable p_bmp) {
        bmps.add(p_bmp);
    }

    public void setDuration(int p_val) {
        _duration = p_val;
    }

    public void start() {
        _stopped = false;
        nextFrameHandler.sendEmptyMessageDelayed(0, _duration);
    }

    private Handler nextFrameHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (_stopped)
                return;
            view.setBackgroundDrawable(bmps.get(_counter));
            _counter++;
            if (_counter == bmps.size())
                _counter = 0;
            if (!_stopped)
                nextFrameHandler.sendEmptyMessageDelayed(0, _duration);
        }

        ;
    };

    public void stop() {
        _stopped = true;
        if (nextFrameHandler.hasMessages(0))
            nextFrameHandler.removeMessages(0);
    }
}