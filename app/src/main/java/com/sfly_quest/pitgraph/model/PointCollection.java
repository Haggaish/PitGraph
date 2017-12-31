package com.sfly_quest.pitgraph.model;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by haggai on 27/12/2017.
 */

public class PointCollection {


    // point are held in values relative to the display.
    private List<PitPoint> points;

    // This is the model zero point in terms of the display
    private PitPoint zero = new PitPoint(0,0);


    public PointCollection(){
        points = new ArrayList<PitPoint>() ;
    }

    public PointCollection(List<PitPoint> list){
        points = list;
    }


    // The point added in values relative to zero
    public void addPoint(PitPoint p){
        PitPoint revisedPoint = p.plus(zero);
        revisedPoint.setZero(zero);
        points.add(revisedPoint);
    }

    public void setZero(PitPoint zero){
        for(int i = 0; i< points.size() ; ++i){
            PitPoint tmp = (points.get(i).minus(this.zero)).plus(zero);
            points.get(i).x = tmp.x;
            points.get(i).y = tmp.y;
            points.get(i).setZero(zero);
        }
        this.zero = zero;
    }
    public PitPoint getZero(){
        return zero;
    }



    public static final boolean ABOVE = true;
    public static final boolean BELOW = false;
    
    public List<PitPoint> getList(){
        Collections.sort(points);
        if(points.size() < 2)
            return points;
        for(int i = 0; i< points.size() ; ++i){
            if(i == 0){
                points.get(i).setCapturePosition( points.get(i).y <   points.get(i+1).y ? ABOVE : BELOW );
            }
            else if(i == points.size()-1){
                points.get(i).setCapturePosition( points.get(i).y <   points.get(i-1).y ? ABOVE : BELOW );
            } else {
                points.get(i).setCapturePosition(
                        points.get(i).y <   points.get(i-1).y &&
                        points.get(i).y <   points.get(i+1).y ? ABOVE : BELOW );
            }
        }
        return points;
    }


    public PitPoint touched(Point touchPoint, double dist){

        for(PitPoint point : points){
            if( Math.abs(touchPoint.x - point.x) < dist  &&    Math.abs(touchPoint.y - point.y) < dist )
                return point ;
        }
        return null ;
    }
}
