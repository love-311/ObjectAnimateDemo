package com.love311.www.objectanimatedemo.evaluator;

import android.animation.TypeEvaluator;

import com.love311.www.objectanimatedemo.bean.Point;

/**
 * Created by Administrator on 2016/9/20.
 */
public class PointEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float v, Object o, Object t1) {
        Point startPoint = (Point) o;
        Point endPoint = (Point) t1;
        float x = startPoint.getX() + v*(endPoint.getX()-startPoint.getX());
        float y = startPoint.getY() + v*(endPoint.getY()-startPoint.getY());
        Point point = new Point(x,y);
        return point;
    }
}
