package com.mukeshjadhav.khadush;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Typeface typefaceMedium, typefaceLight, typefaceRegular, typefaceThin;
    private TextView tvValueTotal, tvValueDebits, tvValueCredits, tvLabelTotal, tvLabelDebits,
            tvLabelCredits, tvLabelRecentTransactions, tvAppNameAppbar, tvLabeltransactionFilters;

    private RecyclerView rvTransactions;
    private List<Transaction> transactions;
    private Chip chipBearer, chipDate, chipType, chipReset;
    private CompoundButton.OnCheckedChangeListener filterChipListener;
    private DBManager dbManager;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTypeFaces();
        initComponents();
        setTypeFaces();
        dumpMessages();
        resetTransactionsRecycler();
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
        tvLabeltransactionFilters = (TextView) findViewById(R.id.tv_title_transaction_filters);

        chipBearer = (Chip) findViewById(R.id.chip_transaction_filters_chip_bearer);
        chipDate = (Chip) findViewById(R.id.chip_transaction_filters_chip_date);
        chipType = (Chip) findViewById(R.id.chip_transaction_filters_chip_type);
        chipReset = (Chip) findViewById(R.id.chip_transaction_filters_chip_reset);

        filterChipListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (buttonView.getId()){
                    case R.id.chip_transaction_filters_chip_bearer:
                        break;

                    case R.id.chip_transaction_filters_chip_date:
                        break;

                    case R.id.chip_transaction_filters_chip_type:
                        break;

                    case R.id.chip_transaction_filters_chip_reset:
                        break;
                }
            }
        };

        dbManager = new DBManager(this);
        dbManager.open();
        dbManager.deleteTable();
        dbManager = new DBManager(this);
        dbManager.open();

        chipBearer.setOnCheckedChangeListener(filterChipListener);
        chipDate.setOnCheckedChangeListener(filterChipListener);
        chipType.setOnCheckedChangeListener(filterChipListener);
        chipReset.setOnCheckedChangeListener(filterChipListener);

        transactions = new ArrayList<>();
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
        tvLabeltransactionFilters.setTypeface(typefaceLight);
    }

    private void dumpMessages(){
        Uri uriSms = Uri.parse("content://sms/inbox");

        Cursor msgCursor = getContentResolver().query(uriSms, null, null, null,null);

        while (msgCursor.moveToNext()) {
            String msgBody = msgCursor.getString(3);
            Transaction t = checkForPaytmTransaction(msgBody);

            dbManager.insert(t.getTransaction_id(), t.getTransaction_bearer(), t.getTransaction_date(), t.getTransaction_type(), t.getTransaction_amount());
        }
        msgCursor.close();
    }

    private Transaction checkForPaytmTransaction(String msgBody){
        return new Transaction("2521991", "paytm","200","25-12-1991","paytm");
    }

    private void resetTransactionsRecycler(){
        cursor = dbManager.fetch();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Transaction t = new Transaction(cursor.getString(cursor.getColumnIndex("transaction_id")),
                            cursor.getString(cursor.getColumnIndex("transaction_bearer")),
                            cursor.getString(cursor.getColumnIndex("transaction_amount")),
                            cursor.getString(cursor.getColumnIndex("transaction_date")),
                            cursor.getString(cursor.getColumnIndex("transaction_type")));

                    transactions.add(t);
                } while (cursor.moveToNext());
            }
        }

        TransactionsRecyclerviewAdapter adapter = new TransactionsRecyclerviewAdapter(this, transactions);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        rvTransactions.setLayoutManager(manager);
        rvTransactions.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add_new_transaction_type:

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
