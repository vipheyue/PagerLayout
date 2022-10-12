package com.inerdstack.lockscreen;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class MainActivity extends AppCompatActivity {

    private View mRecyclerView;
    private PullLayout myLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (View) findViewById(R.id.frv_face_view);
        myLinearLayout = (PullLayout) findViewById(R.id.lock_view);

        myLinearLayout.setLayout(this, R.layout.slide_layout);


        final float[] downY = {0};
        final int startDownPositionY=180;
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downY[0] = event.getY();
                        if (downY[0]<startDownPositionY){
                            return true;
                        }else {
                            return false;
                        }
                    case MotionEvent.ACTION_UP:

                        float curY = event.getY();
                        float delta = curY - downY[0];

                        if (downY[0]<startDownPositionY && delta>300) {
                            myLinearLayout.show();
                        }else{
                            myLinearLayout.hide();
                        }
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        myLinearLayout.getmGestureDetector().onTouchEvent(event);
                        return false;
                }
                return false;
            }
        });


        myLinearLayout.hide();


    }
}
