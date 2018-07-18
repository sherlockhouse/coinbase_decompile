package com.coinbase.android.paymentmethods;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.coinbase.api.internal.models.achSetupSession.mfa.Data;
import java.util.List;

public class SendOptionsAdapter extends ArrayAdapter<Data> {
    public SendOptionsAdapter(Context context, int resource, List<Data> options) {
        super(context, resource, options);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        Data option = (Data) getItem(position);
        ((TextView) v).setText(option.getMask() + " (" + option.getType() + ")");
        return v;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = super.getDropDownView(position, convertView, parent);
        Data option = (Data) getItem(position);
        ((TextView) v).setText(option.getMask() + " (" + option.getType() + ")");
        return v;
    }
}
