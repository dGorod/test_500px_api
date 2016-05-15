package com.dgorod.example.util;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by Dmytro Gorodnytskyi on 13-Dec-15.
 */
public class DimensionUtil {

    public static int pxToDp(float value) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                Resources.getSystem().getDisplayMetrics()));
    }
}
