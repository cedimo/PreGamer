package com.example.pregamer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Dreimann extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dreimann);

        rollTheDice();

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollTheDice();
            }
        });
    }

    private void rollTheDice() {
        ImageView leftCubeImage = (ImageView) findViewById(R.id.leftCubeImage);
        ImageView rightCubeImage = (ImageView) findViewById(R.id.rightCubeImage);

        rollTheDiceView(leftCubeImage);
        rollTheDiceView(rightCubeImage);
    }

    private void rollTheDiceView(ImageView imageView) {
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