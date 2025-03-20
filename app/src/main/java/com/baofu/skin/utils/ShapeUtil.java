package com.baofu.skin.utils;

import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;

import com.baofu.skin.BaseApplication;

public class ShapeUtil {
    /**
     * 创建一个Shape - GradientDrawable
     *
     * @param _strokeWidth - DP 沿边线厚度；无需传入0
     * @param _roundRadius - DP 圆角半径；无需传入0
     * @param _shape       - shape绘制类型(rectangle、oval等)；无需传入0，将采用默认的GradientDrawable.RECTANGLE
     * @param strokeColor - 沿边线颜色；无需传入null/""
     * @param fillColor   - 内部填充颜色
     * @return
     */
    public static GradientDrawable createShape(int _strokeWidth,
                                               int _roundRadius, int _shape,
                                               int strokeColor, int fillColor) {
        int strokeWidth = dp2px(_strokeWidth);
        int roundRadius = dp2px(_roundRadius);


        GradientDrawable gd = new GradientDrawable();



        if (0 == _shape) {
            gd.setShape(GradientDrawable.RECTANGLE);
        } else {
            gd.setShape(_shape);
        }
        if (roundRadius>0) {
            gd.setCornerRadius(roundRadius);
        }
        if (strokeWidth>0 && 0 != strokeColor) {
            gd.setStroke(strokeWidth, strokeColor);
        }
        if (0 != fillColor) {
            gd.setColor(fillColor);
        }
        return gd;
    }
    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                BaseApplication.getInstance().getResources().getDisplayMetrics());
    }
}
