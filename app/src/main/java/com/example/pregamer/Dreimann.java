package com.example.pregamer;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Dreimann extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dreimann);

        initializeDice();

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(v -> rollTheDice());
    }

    private void initializeDice() {
        ImageView leftCubeImage = (ImageView) findViewById(R.id.leftCubeImage);
        ImageView rightCubeImage = (ImageView) findViewById(R.id.rightCubeImage);

        rollSingleDice(leftCubeImage);
        rollSingleDice(rightCubeImage);
    }

    private boolean rollTheDice() {
        Button button = (Button) findViewById(R.id.button);
        button.setEnabled(false);

        ImageView leftCubeImage = (ImageView) findViewById(R.id.leftCubeImage);
        ImageView rightCubeImage = (ImageView) findViewById(R.id.rightCubeImage);

        Handler handler = new Handler();
        for(int i=0; i<10; i++){
            handler.postDelayed(() -> {
                rollSingleDice(leftCubeImage);
                rollSingleDice(rightCubeImage);
            }, 100*i);
        }

        handler.postDelayed(()->button.setEnabled(true), 1000);
        return true;
    }

    private void rollSingleDice(ImageView imageView) {
        int cubeNumber = (int) (Math.random()*6+1);

        switch (cubeNumber) {
            case 1: cubeNumber = R.drawable.cube1; break;
            case 2: cubeNumber = R.drawable.cube2; break;
            case 3: cubeNumber = R.drawable.cube3; break;
            case 4: cubeNumber = R.drawable.cube4; break;
            case 5: cubeNumber = R.drawable.cube5; break;
            case 6: cubeNumber = R.drawable.cube6; break;
        }
        imageView.setImageResource(cubeNumber);

        double rotation = Math.random()*720 -360;
        imageView.setRotation((float) rotation);
    }
}