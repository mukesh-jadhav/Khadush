package com.mukeshjadhav.khadush;

public class Transaction {
    private String transaction_amount;
    private String transaction_id = "";
    private String transaction_type = "abc";
    private String transaction_bearer;
    private String transaction_date;

    public Transaction(String transaction_id, String transaction_bearer, String transaction_amount, String transaction_date, String transaction_type){
        this.transaction_amount = transaction_amount;
        this.transaction_bearer = transaction_bearer;
        this.transaction_date = transaction_date;
        this.transaction_id = transaction_id;
        this.transaction_type = transaction_type;
    }

    public String getTransaction_amount() {
        return this.transaction_amount;
    }

    public void setTransaction_amount(String transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTransaction_id() {
        return this.transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTransaction_type() {
        return this.transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getTransaction_bearer() {
        return this.transaction_bearer;
    }

    public void setTransaction_bearer(String transaction_bearer) {
        this.transaction_bearer = transaction_bearer;
    }

    public String getTransaction_date() {
        return this.transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }
}
