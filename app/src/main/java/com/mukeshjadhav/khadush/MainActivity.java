package com.mukeshjadhav.khadush;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Typeface typefaceMedium, typefaceLight, typefaceRegular, typefaceThin;
    private TextView tvValueTotal, tvValueDebits, tvValueCredits, tvLabelTotal, tvLabelDebits, tvLabelCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTypeFaces();
        initComponents();
        setActionBar();
        setTypeFaces();
    }

    private void initTypeFaces(){
        typefaceLight = ResourcesCompat.getFont(this, R.font.montserrat_light);
        typefaceMedium = ResourcesCompat.getFont(this, R.font.montserrat_medium);
        typefaceRegular = ResourcesCompat.getFont(this, R.font.montserrat_regular);
        typefaceThin = ResourcesCompat.getFont(this, R.font.montserrat_thin);
    }

    private void initComponents(){
        tvValueTotal = (TextView) findViewById(R.id.tv_value_ma_total);
        tvValueDebits = (TextView) findViewById(R.id.tv_value_ma_debits);
        tvValueCredits = (TextView) findViewById(R.id.tv_value_ma_credits);
        tvLabelTotal = (TextView) findViewById(R.id.tv_label_ma_total);
        tvLabelDebits = (TextView) findViewById(R.id.tv_label_ma_debits);
        tvLabelCredits = (TextView) findViewById(R.id.tv_label_ma_credits);
    }

    private void setActionBar(){
        androidx.appcompat.app.ActionBar ab = getSupportActionBar();

        TextView tv = new TextView(getApplicationContext());

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        tv.setLayoutParams(lp);
        tv.setText(ab.getTitle());
        tv.setTextSize(18f);
        tv.setTextColor(Color.WHITE);
        tv.setTypeface(typefaceRegular);
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM, androidx.appcompat.app.ActionBar.DISPLAY_HOME_AS_UP);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setElevation(2);
        ab.setCustomView(tv);
    }

    private void setTypeFaces(){
        tvValueTotal.setTypeface(typefaceRegular);
        tvValueDebits.setTypeface(typefaceRegular);
        tvValueCredits.setTypeface(typefaceRegular);
        tvLabelTotal.setTypeface(typefaceMedium);
        tvLabelDebits.setTypeface(typefaceMedium);
        tvLabelCredits.setTypeface(typefaceMedium);
    }
}
