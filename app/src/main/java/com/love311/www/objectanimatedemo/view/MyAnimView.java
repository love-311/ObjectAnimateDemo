package com.love311.www.objectanimatedemo.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.love311.www.objectanimatedemo.bean.Point;
import com.love311.www.objectanimatedemo.evaluator.ColorEvaluator;
import com.love311.www.objectanimatedemo.evaluator.PointEvaluator;

/**
 * Created by Administrator on 2016/9/20.
 */
public class MyAnimView extends View {

    public static final float RADIUS = 50f;
    private Point currentPoint;
    private Paint mPaint;
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }
    public MyAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (currentPoint ==null){
            currentPoint = new Point(RADIUS,RADIUS);
            drawCircle(canvas);
            startAnimation();
        }else {
            drawCircle(canvas);
        }
    }

    //画圆
    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x,y,RADIUS,mPaint);
    }

    //开始动画
    private void startAnimation() {
        Point startPoint = new Point(RADIUS,RADIUS);
        Point endPoint = new Point(getWidth()-RADIUS,getHeight()-RADIUS);
        Log.d("startPoint","startPoint="+(startPoint.getX()+","+startPoint.getY()));
        Log.d("endPoint","endPoint="+(endPoint.getX()+","+endPoint.getY()));
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(),startPoint,endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentPoint = (Point) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        ObjectAnimator anim2 = ObjectAnimator.ofObject(this,"color",new ColorEvaluator(),"#0000FF","#FF0000");
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim).with(anim2);
        animSet.setDuration(5000);
        animSet.start();
    }
}
