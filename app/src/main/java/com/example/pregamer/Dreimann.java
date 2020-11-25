package com.example.pregamer;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.concurrent.atomic.AtomicInteger;

public class Dreimann extends AppCompatActivity {
    private boolean oneDice = true;
    private ImageView singleCubeImage;
    private ImageView leftCubeImage;
    private ImageView rightCubeImage;
    private TextView resultText;
    private SwitchMaterial diceSwitch;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dreimann);

        singleCubeImage = (ImageView) findViewById(R.id.singleCubeImage);
        leftCubeImage = (ImageView) findViewById(R.id.leftCubeImage);
        rightCubeImage = (ImageView) findViewById(R.id.rightCubeImage);

        resultText = findViewById(R.id.resultText);

        // random values at start
        initializeDice();

        // hide two dice at start
        leftCubeImage.setVisibility(View.INVISIBLE);
        rightCubeImage.setVisibility(View.INVISIBLE);

        // button to roll dice
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(v -> rollTheDice());

        // switch between one or two dice
        diceSwitch = (SwitchMaterial) findViewById(R.id.diceSwitch);
        diceSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                oneDice = false;
                singleCubeImage.setVisibility(View.INVISIBLE);
                leftCubeImage.setVisibility(View.VISIBLE);
                rightCubeImage.setVisibility(View.VISIBLE);
            } else {
                oneDice = true;
                singleCubeImage.setVisibility(View.VISIBLE);
                leftCubeImage.setVisibility(View.INVISIBLE);
                rightCubeImage.setVisibility(View.INVISIBLE);
            }
            resultText.setVisibility(View.INVISIBLE);
        });
    }

    private void initializeDice() {
        rollSingleDice(singleCubeImage);
        rollSingleDice(leftCubeImage);
        rollSingleDice(rightCubeImage);
    }

    private void rollTheDice() {
        button.setEnabled(false);
        diceSwitch.setEnabled(false);
        resultText.setVisibility(View.INVISIBLE);

        // result values of rolling the dice
        AtomicInteger resultSingle = new AtomicInteger();
        AtomicInteger resultLeft = new AtomicInteger();
        AtomicInteger resultRight = new AtomicInteger();

        Handler handler = new Handler();
        for(int i=0; i<10; i++){
            handler.postDelayed(() -> {
                if(oneDice) {
                    resultSingle.set(rollSingleDice(singleCubeImage));
                } else {
                    resultLeft.set(rollSingleDice(leftCubeImage));
                    resultRight.set(rollSingleDice(rightCubeImage));
                }
            }, 100*i);
        }

        handler.postDelayed(() -> {
            button.setEnabled(true);
            diceSwitch.setEnabled(true);

            // logic for result
            int sum = resultLeft.get() + resultRight.get();
            String resultString = "";

            if(oneDice){
                if(resultSingle.get()==3){
                    resultString = "Du bist jetzt der Dreimann!";
                } else {
                    resultString = "Nächster Spieler!";
                }
            } else {
                if(resultLeft.get()==3 || resultRight.get()==3){
                    resultString = "Ein Würfel zeigt 3: Dreimann trinkt!\n";
                }
                if(resultLeft.get()==resultRight.get()){
                    resultString = resultString + "Pasch: "+sum+" Schlücke verteilen!\n";
                }
                switch (sum) {
                    case 3: resultString = resultString + "Die Summe ist 3: Dreimann trinkt!"; break;
                    case 7: resultString = resultString + "Die Summe ist 7: Links trinkt!"; break;
                    case 8: resultString = resultString + "Die Summe ist 8: Rechts trinkt!"; break;
                    case 9: resultString = resultString + "Die Summe ist 9: Regel ausdenken!"; break;
                    case 10: resultString = resultString + "Die Summe ist 10: Stirn berühren!"; break;
                }
                if(resultString.equals("")){
                    resultString = "Nichts passiert!";
                }
            }
            resultText.setText(resultString);
            resultText.setVisibility(View.VISIBLE);
        }, 1000);
    }

    private int rollSingleDice(ImageView imageView) {
        int cubeNumber = (int) (Math.random()*6+1);

        int imageId = 0;

        switch (cubeNumber) {
            case 1: imageId = R.drawable.cube1; break;
            case 2: imageId = R.drawable.cube2; break;
            case 3: imageId = R.drawable.cube3; break;
            case 4: imageId = R.drawable.cube4; break;
            case 5: imageId = R.drawable.cube5; break;
            case 6: imageId = R.drawable.cube6; break;
        }
        imageView.setImageResource(imageId);

        double rotation = Math.random()*720 -360;
        imageView.setRotation((float) rotation);

        return cubeNumber;
    }
}