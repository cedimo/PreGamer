package com.example.pregamer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;

public class Start extends AppCompatActivity {
    private final LocalDate initialDate = Instant.ofEpochMilli(getMillis()).atZone(ZoneId.systemDefault()).toLocalDate();
    private int birthDay = initialDate.getDayOfMonth();
    private int birthMonth = initialDate.getMonthValue();
    private int birthYear = initialDate.getYear();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button playAsGuest = findViewById(R.id.guest_play);

        playAsGuest.setOnClickListener(v -> launchWarning());
    }

    @Override
    public void onBackPressed(){
        //make Back Button acting like Home Button
        moveTaskToBack(true);
    }


    private void launchWarning() {
        setContentView(R.layout.warning);

        Button acceptWarning = findViewById(R.id.accept);

        acceptWarning.setOnClickListener(v -> launchAgeInput());
    }


    private void launchAgeInput() {
        setContentView(R.layout.age);

        DatePicker dateOfBirth = findViewById(R.id.datePicker);
        dateOfBirth.setMaxDate(getMillis());
        dateOfBirth.setFirstDayOfWeek(Calendar.MONDAY);

        dateOfBirth.setOnDateChangedListener((view, year, monthOfYear, dayOfMonth) -> {
            birthDay = dayOfMonth;
            birthMonth = monthOfYear + 1;
            birthYear = year;
        });

        Button enterAge = findViewById(R.id.enterage);

        enterAge.setOnClickListener(v -> {
            LocalDate dayOfBirth = LocalDate.of(birthYear, birthMonth, birthDay);
            int age = Period.between(dayOfBirth, getDate()).getYears();

            if (age > 17) {
                launchOverage();
            } else {
                launchUnderage();
            }
        });
    }


    private void launchOverage() {
        setContentView(R.layout.overage);
        ConstraintLayout layout = findViewById(R.id.overage);
        layout.setOnClickListener(v -> launchMainActivity());
    }


    private void launchUnderage() {
        setContentView(R.layout.underage);
    }


    private void launchMainActivity() {
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(v -> startActivity(new Intent(Start.this, Busfahrer.class)));

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> startActivity(new Intent(Start.this, Dreimann_Regeln.class)));

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> startActivity(new Intent(Start.this, KingsCup.class)));
    }


    private long getMillis() {
        return System.currentTimeMillis() - 86400000;
    }


    private LocalDate getDate() {
        return LocalDate.now();
    }
}