package com.sfly_quest.pitgraph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.sfly_quest.pitgraph.model.PitPoint;
import com.sfly_quest.pitgraph.model.PointCollection;

import java.util.List;

/**
 * Created by haggai on 27/12/2017.
 */

public class PitView extends View {



    private PointCollection pointCollection = new PointCollection();
    private float touchRange = 50.0f;
    private float circleRadius = 20.0f;


    private Paint pointPaint = new Paint();
    private Paint linePaint = new Paint();
    private Paint axcesPaint = new Paint();
    private Paint capturePaint = new Paint();


    public PitView(Context context) {
        super(context);
        init(context);
    }

    public PitView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void addPoint(PitPoint pitPoint){
        pointCollection.addPoint(pitPoint);
    }
    private void init(Context context){
        pointPaint.setColor(  0xffff0000);
        pointPaint.setStrokeWidth(3);
        pointPaint.setStyle(Paint.Style.STROKE);

        linePaint.setColor(0xFF573D71);
        linePaint.setStrokeWidth(8);
        linePaint.setStyle(Paint.Style.STROKE);

        axcesPaint.setColor(0xFF42C9B0);
        axcesPaint.setStrokeWidth(3);
        axcesPaint.setStyle(Paint.Style.STROKE);

        capturePaint.setColor(0xFF2780DD);
        capturePaint.setStrokeWidth(1);
        capturePaint.setStyle(Paint.Style.STROKE);
        capturePaint.setTextSize(40);


        pointCollection = new PointCollection();

        return;
    }


    private PitPoint touchedPoint = null ;
    private PitPoint offset = null;
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        int x = (int)e.getX();
        int y = (int)e.getY();
        PitPoint eventPoint = new PitPoint(x,y);

        switch (e.getAction()) {

            case MotionEvent.ACTION_DOWN: {
                if ((touchedPoint = pointCollection.touched(eventPoint, touchRange)) != null) {
                    offset = eventPoint.minus(touchedPoint);
                    return true;
                }
                return false;
            }
            case MotionEvent.ACTION_MOVE: {
                if (touchedPoint == null || offset == null)
                    return false;

                synchronized (this) {
                    touchedPoint.x = x - offset.x;
                    touchedPoint.y = y - offset.y;
                }
                invalidate();
                return true;
            }

            case MotionEvent.ACTION_UP: {
                if (touchedPoint != null || offset != null) {

                    synchronized (this) {
                        touchedPoint.x = x - offset.x;
                        touchedPoint.y = y - offset.y;
                    }

                }
                touchedPoint = offset = null ;
                invalidate();
                return true;
            }
            default:
                return true ;
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        pointCollection.setZero(new PitPoint(width/2, height/2));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawAxces(canvas);

        List<PitPoint> list = pointCollection.getList();
        for(PitPoint pitPoint : list){
            canvas.drawCircle(pitPoint.x, pitPoint.y, circleRadius, pointPaint);
            PitPoint val = pitPoint.getNumericValue();

            if(pitPoint.getCapturePosition() == PointCollection.BELOW){
                canvas.drawText(String.format("%d,%d",val.x,val.y), pitPoint.x-3*circleRadius, pitPoint.y+3*circleRadius, capturePaint);
            } else {
                canvas.drawText(String.format("%d,%d",val.x,val.y), pitPoint.x-3*circleRadius, pitPoint.y-3*circleRadius, capturePaint);
            }
        }
        canvas.drawPath( calculatePath(), linePaint);

    }

    private void drawAxces(Canvas canvas) {
        canvas.drawLine(pointCollection.getZero().x, 0,
                pointCollection.getZero().x, pointCollection.getZero().y * 2,
                axcesPaint);
        canvas.drawLine(0, pointCollection.getZero().y,
                pointCollection.getZero().x * 2, pointCollection.getZero().y ,
                axcesPaint);
    }

    private Path calculatePath(){
        List<PitPoint> list = pointCollection.getList();
        Path path = new Path();
        path.reset();

        PitPoint pp = list.get(0) ;
        path.moveTo(pp.x, pp.y);
        for(int i=1 ; i< list.size() ; ++i){
            pp = list.get(i) ;
            path.lineTo(pp.x, pp.y);
        }
        return path;
    }

}
