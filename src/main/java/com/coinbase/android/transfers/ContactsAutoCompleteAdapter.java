package com.coinbase.android.transfers;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.DropdownItemContactSuggestionBinding;
import com.coinbase.android.utils.RoundedTransformation;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.LoginManager;
import com.coinbase.v1.entity.Contact;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import javax.inject.Inject;

@ActivityScope
public class ContactsAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {
    @Inject
    LoginManager mLoginManager;
    int radius = 60;
    private ArrayList<String> resultList;

    public ContactsAutoCompleteAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        ((ComponentProvider) context.getApplicationContext()).applicationComponent().adapterSubcomponent().inject(this);
    }

    public int getCount() {
        return this.resultList.size();
    }

    public String getItem(int index) {
        return (String) this.resultList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        DropdownItemContactSuggestionBinding binding;
        if (convertView == null) {
            binding = (DropdownItemContactSuggestionBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dropdown_item_contact_suggestion, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (DropdownItemContactSuggestionBinding) convertView.getTag();
        }
        String email = getItem(position);
        Picasso.with(getContext()).load(Utils.getGravatarUrl(email)).transform(new RoundedTransformation(this.radius, 0)).into(binding.ivGravatar);
        binding.tvName.setText(email);
        return convertView;
    }

    public Filter getFilter() {
        return new Filter() {
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    ContactsAutoCompleteAdapter.this.resultList = ContactsAutoCompleteAdapter.this.fetchContacts(constraint.toString());
                    filterResults.values = ContactsAutoCompleteAdapter.this.resultList;
                    filterResults.count = ContactsAutoCompleteAdapter.this.resultList.size();
                }
                return filterResults;
            }

            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results == null || results.count <= 0) {
                    ContactsAutoCompleteAdapter.this.notifyDataSetInvalidated();
                } else {
                    ContactsAutoCompleteAdapter.this.notifyDataSetChanged();
                }
            }
        };
    }

    private ArrayList<String> fetchContacts(String filter) {
        ArrayList<String> result = new ArrayList();
        try {
            for (Contact contact : this.mLoginManager.getClient().getContacts(filter).getContacts()) {
                result.add(contact.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
