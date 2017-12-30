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
    private List<PitPoint> mPoints ;

    // This is the model zero point in terms of the display
    private PitPoint  mZero = new PitPoint(0,0);


    public PointCollection(){
        mPoints = new ArrayList<PitPoint>() ;
    }

    public PointCollection(List<PitPoint> list){
        mPoints = list;
    }


    // The point added in values relative to zero
    public void addPoint(PitPoint p){
        mPoints.add(p.plus(mZero));
    }

    public void setZero(PitPoint zero){
        for(int i = 0; i< mPoints.size() ; ++i){
            PitPoint tmp = (mPoints.get(i).minus(mZero)).plus(zero);
            mPoints.get(i).x = tmp.x;
            mPoints.get(i).y = tmp.y;
            mPoints.get(i).setZero(zero);
        }
        mZero = zero;
    }
    public PitPoint getZero(){
        return mZero;
    }



    // This routine fetch the points in terms of the
    public List<PitPoint> getList(){
        Collections.sort(mPoints);
        return mPoints;
    }


    public PitPoint touched(Point touchPoint, double dist){

        for(PitPoint point : mPoints){
            if( Math.abs(touchPoint.x - point.x) < dist  &&    Math.abs(touchPoint.y - point.y) < dist )
                return point ;
        }
        return null ;
    }
}
