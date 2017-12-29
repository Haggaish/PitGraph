package com.sfly_quest.pitgraph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sfly_quest.pitgraph.model.PitPoint;

public class MainActivity extends AppCompatActivity {

    private PitView mPitView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPitView = (PitView)findViewById(R.id.thePitView);

        mPitView.addPoint(new PitPoint(-300,150));
        mPitView.addPoint(new PitPoint(-450,-150));
        mPitView.addPoint(new PitPoint(0,0));
        mPitView.addPoint(new PitPoint(300,150));
        mPitView.addPoint(new PitPoint(450,-150));
    }

    public void addPointInCenter(View view) {
        mPitView.addPoint(new PitPoint(0,0));
        mPitView.invalidate();
    }
}
