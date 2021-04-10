package com.example.calc_307_pie_rybakov;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {
    Settings context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeManager.setTheme(this);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;

        Button item = findViewById(R.id.theme1);
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemeManager.changeTo(context, ThemeManager.THEME_1);
            }
        };
        item.setOnClickListener(buttonClickListener);

        Button item2 = findViewById(R.id.theme2);
        View.OnClickListener buttonClickListener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemeManager.changeTo(context, ThemeManager.THEME_2);
            }
        };
        item2.setOnClickListener(buttonClickListener2);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}