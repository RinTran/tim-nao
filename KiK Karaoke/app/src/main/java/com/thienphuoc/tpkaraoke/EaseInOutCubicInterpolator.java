package com.thienphuoc.tpkaraoke;

import android.animation.TimeInterpolator;

/**
 * Created by Thien Phuoc on 12/14/2015.
 */
public class EaseInOutCubicInterpolator implements TimeInterpolator {

    @Override
    public float getInterpolation(float input) {
        if ((input *= 2) < 1.0f) {
            return 0.5f * input * input * input;
        }
        input -= 2;
        return 0.5f * input * input * input + 1;
    }

}