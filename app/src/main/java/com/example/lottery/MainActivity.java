package com.example.lottery;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ImageView table;
    private TextView text;
    private Button no;
    private Button yes;
    float i;
    private float startDegree = 0;
    private float endDegree = 360;
    private int flag = 0;
    private Button fate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        table = findViewById(R.id.table);
        text = findViewById(R.id.text);
        yes = findViewById(R.id.YES);
        no = findViewById(R.id.NO);
        fate = findViewById(R.id.fate);
    }

    public void click(View view) {
        i = (float) Math.random();
        addClick();
        if (flag == 0) { // fate
            endDegree = (float) (360 * (i + 3) + i * 70);
            Log.i(TAG, "FATE " + "i is " + i + " end is :" + endDegree);
        } else if (flag == 1) {// yes
            endDegree = (float) (360 * (2) + (i * 80));
            Log.i(TAG, "YES " + "i is " + i + " end is :" + endDegree);
        } else if (flag == 2) {// no
            endDegree = (float) (360 * 3 + ((Math.random() + 10) * 18));
            Log.i(TAG, "NO " + "i is " + i + " end is :" + endDegree);
        }
        if ((endDegree) % 90 < 1) {//去除刚好在分界线上的情况
            endDegree -= 20;
        }
        Log.i(TAG, "click: " + endDegree);
        startAnim();
        setText();
    }

    private void addClick() {
        fate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 2;
            }
        });
    }

    private void setText() {
        float v = endDegree % 360;
        if (v > 0 && v < 90) {
            text.setText("恭喜中奖");
        } else if (v > 90 && v < 180) {
            text.setText("下次努力");
        } else if (v > 180 && v < 270) {
            text.setText("下次努力");
        } else if (v > 270 && v < 360) {
            text.setText("下次努力");
        }
        Log.i(TAG, " text is:" + text.getText());
    }

    private void startAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(startDegree, endDegree);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                table.setRotation(animatedValue);
            }
        });
        animator.setDuration(2000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }
}
