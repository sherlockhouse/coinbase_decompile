package com.coinbase.android.paymentmethods;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.coinbase.v2.models.account.Data;
import java.util.List;

public class AccountsAdapter extends ArrayAdapter<Data> {
    public AccountsAdapter(Context context, int resource, List<Data> accounts) {
        super(context, resource, accounts);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        ((TextView) v).setText(((Data) getItem(position)).getName());
        return v;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = super.getDropDownView(position, convertView, parent);
        ((TextView) v).setText(((Data) getItem(position)).getName());
        return v;
    }
}
