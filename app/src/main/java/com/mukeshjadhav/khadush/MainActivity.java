package com.mukeshjadhav.khadush;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Typeface typefaceMedium, typefaceLight, typefaceRegular, typefaceThin;
    private TextView tvValueTotal, tvValueDebits, tvValueCredits, tvLabelTotal, tvLabelDebits, tvLabelCredits, tvLabelRecentTransactions, tvAppNameAppbar;
    private RecyclerView rvTransactions;
    private List<Transaction> transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTypeFaces();
        initComponents();
        //setActionBar();
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
        rvTransactions = (RecyclerView) findViewById(R.id.rv_ma_transactions);
        tvLabelRecentTransactions = (TextView) findViewById(R.id.tv_title_recent_transactions);
        tvAppNameAppbar = (TextView) findViewById(R.id.tv_app_name_appbar);

        transactions = new ArrayList<>();
        transactions.add(new Transaction("Paytm-248635", "Rs.945", "paytm", "25th Feb 09:45 AM"));
        transactions.add(new Transaction("ABC shop", "Rs.20", "GooglePe", "7th Mar 08:00 PM"));
        transactions.add(new Transaction("Ola-money 2211", "Rs.89", "Ola Money", "9th Mar 09:45 PM"));
        transactions.add(new Transaction("PhonePe", "Rs.34", "PhonePe", "20th Aug 12:19 PM"));
        transactions.add(new Transaction("Paytm-248635", "Rs.945", "paytm", "25th Feb 09:45 AM"));
        transactions.add(new Transaction("ABC shop", "Rs.20", "GooglePe", "7th Mar 08:00 PM"));
        transactions.add(new Transaction("Ola-money 2211", "Rs.89", "Ola Money", "9th Mar 09:45 PM"));
        transactions.add(new Transaction("PhonePe", "Rs.34", "PhonePe", "20th Aug 12:19 PM"));
        transactions.add(new Transaction("Paytm-248635", "Rs.945", "paytm", "25th Feb 09:45 AM"));
        transactions.add(new Transaction("ABC shop", "Rs.20", "GooglePe", "7th Mar 08:00 PM"));
        transactions.add(new Transaction("Ola-money 2211", "Rs.89", "Ola Money", "9th Mar 09:45 PM"));
        transactions.add(new Transaction("PhonePe", "Rs.34", "PhonePe", "20th Aug 12:19 PM"));
        transactions.add(new Transaction("Paytm-248635", "Rs.945", "paytm", "25th Feb 09:45 AM"));
        transactions.add(new Transaction("ABC shop", "Rs.20", "GooglePe", "7th Mar 08:00 PM"));
        transactions.add(new Transaction("Ola-money 2211", "Rs.89", "Ola Money", "9th Mar 09:45 PM"));
        transactions.add(new Transaction("PhonePe", "Rs.34", "PhonePe", "20th Aug 12:19 PM"));

        TransactionsRecyclerviewAdapter adapter = new TransactionsRecyclerviewAdapter(this, transactions);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        rvTransactions.setLayoutManager(manager);
        rvTransactions.setAdapter(adapter);
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
        tvLabelRecentTransactions.setTypeface(typefaceLight);
        tvAppNameAppbar.setTypeface(typefaceRegular);
    }
}
