package com.example.pregamer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.time.LocalDate;
import java.time.Period;

public class Start extends AppCompatActivity {
    private int bday = 0;
    private int bmonth = 0;
    private int byear = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button playAsGuest = (Button) findViewById(R.id.guest_play);

        playAsGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.warning);

                Button acceptwarning = (Button) findViewById(R.id.accept);

                acceptwarning.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.age);

                        DatePicker dateOfBirth = (DatePicker) findViewById(R.id.datePicker);
                        dateOfBirth.setMaxDate(getMillis());

                        dateOfBirth.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                            @Override
                            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                bday = dayOfMonth;
                                bmonth = monthOfYear;
                                byear = year;
                            }
                        });

                        Button enterAge = (Button) findViewById(R.id.enterage);

                        enterAge.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LocalDate dayOfBirth = LocalDate.of(byear, bmonth+1, bday);
                                int age = Period.between(dayOfBirth, getDate()).getYears();

                                if(age>17){
                                    setContentView(R.layout.overage);
                                    ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.overage);
                                    layout.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            setContentView(R.layout.activity_main);

                                            Button button1 = (Button) findViewById(R.id.button1);
                                            button1.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    startActivity(new Intent(Start.this, Busfahrer.class));
                                                }
                                            });

                                            Button button2 = (Button) findViewById(R.id.button2);
                                            button2.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    startActivity(new Intent(Start.this, Dreimann_Regeln.class));
                                                }
                                            });

                                            Button button3 = (Button) findViewById(R.id.button3);
                                            button3.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    startActivity(new Intent(Start.this, KingsCup.class));
                                                }
                                            });
                                        }
                                    });
                                } else {
                                    setContentView(R.layout.underage);
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    private long getMillis() {
        return System.currentTimeMillis() - 86400000;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private LocalDate getDate() {
        return LocalDate.now();
    }
}