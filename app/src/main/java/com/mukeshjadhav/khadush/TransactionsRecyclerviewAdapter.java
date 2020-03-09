package com.mukeshjadhav.khadush;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.util.List;

public class TransactionsRecyclerviewAdapter extends RecyclerView.Adapter<TransactionsRecyclerviewAdapter.ViewHolder> {
    private Typeface typefaceMedium, typefaceLight, typefaceRegular, typefaceThin;
    private List<Transaction> transactionList;
    private  TextView transactionTitleName, transactionValueName;
    private  TextView transactionTitleAmount, transactionValueAmount;
    private  TextView transactionValueDate;
    private Chip transactionValueType;
    private Context mcontext;

    public TransactionsRecyclerviewAdapter(Context mcontext, List<Transaction> transactions){
        this.transactionList = transactions;
        this.mcontext = mcontext;
        initTypeFaces();
    }

    private void initTypeFaces(){
        typefaceLight = ResourcesCompat.getFont(mcontext, R.font.montserrat_light);
        typefaceMedium = ResourcesCompat.getFont(mcontext, R.font.montserrat_medium);
        typefaceRegular = ResourcesCompat.getFont(mcontext, R.font.montserrat_regular);
        typefaceThin = ResourcesCompat.getFont(mcontext, R.font.montserrat_thin);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionTitleName = itemView.findViewById(R.id.rv_item__title_transaction_name);
            transactionValueName = itemView.findViewById(R.id.rv_item__value_transaction_name);

            transactionValueType = itemView.findViewById(R.id.rv_item_value_transactions_type);

            transactionTitleAmount = itemView.findViewById(R.id.rv_item__title_transaction_amount);
            transactionValueAmount = itemView.findViewById(R.id.rv_item__value_transaction_amount);

            transactionValueDate = itemView.findViewById(R.id.rv_item__value_transaction_date);
        }
    }

    @NonNull
    @Override
    public TransactionsRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout_single_item, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsRecyclerviewAdapter.ViewHolder holder, int position) {
        Transaction t = transactionList.get(position);
        transactionValueName.setText(t.getTransaction_name());
        transactionValueName.setTypeface(typefaceMedium);
        transactionTitleName.setTypeface(typefaceLight);

        transactionValueAmount.setText(t.getTransaction_amount());
        transactionValueAmount.setTypeface(typefaceMedium);
        transactionTitleAmount.setTypeface(typefaceLight);

        transactionValueType.setText(t.getTransaction_type());
        transactionValueType.setTypeface(typefaceMedium);

        transactionValueDate.setText(t.getTransaction_date());
        transactionValueDate.setTypeface(typefaceMedium);
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }
}
