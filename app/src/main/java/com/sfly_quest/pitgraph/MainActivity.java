package com.sfly_quest.pitgraph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sfly_quest.pitgraph.model.PitPoint;

public class MainActivity extends AppCompatActivity {

    private PitView pitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pitView = (PitView)findViewById(R.id.thePitView);

        pitView.addPoint(new PitPoint(-300,150));
        pitView.addPoint(new PitPoint(-450,-150));
        pitView.addPoint(new PitPoint(0,0));
        pitView.addPoint(new PitPoint(300,150));
        pitView.addPoint(new PitPoint(450,-150));
    }

    public void addPointInCenter(View view) {
        pitView.addPoint(new PitPoint(0,0));
        pitView.invalidate();
    }
}
