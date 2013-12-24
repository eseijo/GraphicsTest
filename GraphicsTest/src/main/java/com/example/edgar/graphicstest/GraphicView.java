package com.example.edgar.graphicstest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by edgar on 12/17/13.
 */
public class GraphicView extends View {
    public Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    boolean moving = false;
    float pX, pY, rad;



    public GraphicView(Context context) {
        super(context);
        pX = -1; pY = -1; rad=25;
    }
    public GraphicView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint); // Fill screen with color
        int height = canvas.getHeight();
        int width = canvas.getWidth();
        paint.setColor(Color.RED);
        canvas.drawLine(0, height/2, width, height/2, paint);
        canvas.drawLine(width/2, 0, width/2, height, paint);
        // Draw a cross
        paint.setColor(Color.BLUE);
        paint.setTextSize(20);
        canvas.drawText("Width x Height: " + width + "x" + height, 20, 40, paint); // Draw text
        paint.setColor(Color.GREEN);
        int minus = width>height ? height : width;
        canvas.drawCircle(3*width/4, 3*height/4, minus/4, paint);
        //Draw a circle
        paint.setColor(Color.BLACK);
        if(pX<0){
            pX=3*width/4;
            pY=height/4;
        }
        canvas.drawCircle(pX, pY, rad, paint);
    }


    @Override public boolean
    onTouchEvent(MotionEvent event) {
        float eventX=event.getX(); float eventY=event.getY();
        if (event.getAction()==
                MotionEvent.ACTION_DOWN) {
            moving = true;
                float dX=Math.abs(pX-eventX);
                float dY=Math.abs(pY-eventY);
                float dist=(float) Math.sqrt(
                        (dX*dX)+(dY*dY));
                if (dist < rad) moving = true;
                //if (drawpath) circlePath.moveTo(eventX, eventY);
            }
        else if (event.getAction()== MotionEvent.ACTION_MOVE) {
            if(moving){pX=eventX; pY=eventY;}
        }
        invalidate();
        return true;
    }

    /*public class MoveView extends View {
        private float x[ ]={100}, y[ ]={100};
        private int rad[ ]={50};
        private int tt;
        private float pX, pY;

        public MoveView(Context context) {
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            for (int i=0; i<x.length;i++)
                canvas.drawCircle(x[i],y[i],rad[i],paint);
        }

    @Override public boolean
    onTouchEvent(MotionEvent event) {
        pX=event.getX(); pY=event.getY();
        if (event.getAction()==
                MotionEvent.ACTION_DOWN) {
            tt = -1;
            for (int i=0; i<x.length; i++) {
                float dX=Math.abs(x[i]-pX);
                float dY=Math.abs(y[i]-pY);
                float dist=(float) Math.sqrt(
                        (dX*dX)+(dY*dY));
                if (dist < rad[i]) tt = i;
            }
        } else if (event.getAction()==
                MotionEvent.ACTION_MOVE) {
            if (tt!=-1) {x[tt]=pX; y[tt]=pY; }
        }
        invalidate();
        return true;
    }
    }*/
}