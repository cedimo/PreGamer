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

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int left_cube, right_cube;

                left_cube = (int) (Math.random()*6+1);
                right_cube = (int) (Math.random()*6+1);

                ImageView leftCubeImage = (ImageView) findViewById(R.id.leftCubeImage);
                ImageView rightCubeImage = (ImageView) findViewById(R.id.rightCubeImage);
                leftCubeImage.setImageResource(R.drawable.cube1);

                switch (left_cube) {
                    case 1: leftCubeImage.setImageResource(R.drawable.cube1); break;
                    case 2: leftCubeImage.setImageResource(R.drawable.cube2); break;
                    case 3: leftCubeImage.setImageResource(R.drawable.cube3); break;
                    case 4: leftCubeImage.setImageResource(R.drawable.cube4); break;
                    case 5: leftCubeImage.setImageResource(R.drawable.cube5); break;
                    case 6: leftCubeImage.setImageResource(R.drawable.cube6); break;
                }
                double leftRotation = Math.random()*720 -360;
                leftCubeImage.setRotation((float) leftRotation);

                switch (right_cube) {
                    case 1: rightCubeImage.setImageResource(R.drawable.cube1); break;
                    case 2: rightCubeImage.setImageResource(R.drawable.cube2); break;
                    case 3: rightCubeImage.setImageResource(R.drawable.cube3); break;
                    case 4: rightCubeImage.setImageResource(R.drawable.cube4); break;
                    case 5: rightCubeImage.setImageResource(R.drawable.cube5); break;
                    case 6: rightCubeImage.setImageResource(R.drawable.cube6); break;
                }
                double rightRotation = Math.random()*720 -360;
                rightCubeImage.setRotation((float) rightRotation);
            }
        });
    }
}