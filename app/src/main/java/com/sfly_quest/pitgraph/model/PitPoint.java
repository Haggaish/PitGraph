package com.sfly_quest.pitgraph.model;

import android.graphics.Point;
import android.support.annotation.NonNull;

/**
 * Created by haggai on 27/12/2017.
 */

public class PitPoint extends Point implements Comparable<PitPoint>{

    private PitPoint zero = null;

    public PitPoint(){
        super(0,0);
    }

    public PitPoint(Point ref){
        super(ref);
    }

    public PitPoint(int x, int y){
        super(x,y);
    }

    public void setZero(PitPoint zero) {
        this.zero = zero;
    }

    public PitPoint getNumericValue(){
        PitPoint point = this.minus(zero);
        point.y *= -1;
        return point;
    }


    public PitPoint plus(PitPoint p){
        return new PitPoint(x+p.x, y+p.y);
    }


    public PitPoint minus(PitPoint p){
        return new PitPoint(x-p.x, y-p.y);
    }

    @Override
    public int compareTo(@NonNull PitPoint p) {
        return p.x  - x;
    }



}
