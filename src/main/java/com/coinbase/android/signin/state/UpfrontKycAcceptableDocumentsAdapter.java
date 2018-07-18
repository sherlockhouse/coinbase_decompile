package com.coinbase.android.signin.state;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.coinbase.android.R;
import com.coinbase.android.databinding.UpfrontKycAcceptableDocItemBinding;
import java.util.ArrayList;
import java.util.List;

public class UpfrontKycAcceptableDocumentsAdapter extends BaseAdapter {
    private Context context;
    private List<ListItem> items;

    private class ListItem {
        int icon;
        String title;

        ListItem() {
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIcon() {
            return this.icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }
    }

    public UpfrontKycAcceptableDocumentsAdapter(Context context, List<String> titles, List<Integer> icons) {
        this.items = new ArrayList(titles.size());
        for (int i = 0; i < titles.size(); i++) {
            ListItem item = new ListItem();
            item.title = (String) titles.get(i);
            item.icon = ((Integer) icons.get(i)).intValue();
            this.items.add(item);
        }
        this.context = context;
    }

    public int getCount() {
        return this.items.size();
    }

    public Object getItem(int position) {
        return this.items.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        UpfrontKycAcceptableDocItemBinding binding;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.upfront_kyc_acceptable_doc_item, parent, false);
            binding = (UpfrontKycAcceptableDocItemBinding) DataBindingUtil.bind(convertView);
            convertView.setTag(binding);
        } else {
            binding = (UpfrontKycAcceptableDocItemBinding) convertView.getTag();
        }
        binding.tvText.setText(((ListItem) this.items.get(position)).getTitle());
        binding.ivIcon.setImageResource(((ListItem) this.items.get(position)).getIcon());
        return convertView;
    }
}
