package com.coinbase.android.signin;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Pair;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.dialog.SpinnerDialogFragment;
import com.coinbase.api.internal.models.idology.options.Data;
import com.coinbase.api.internal.models.idology.options.IdologyOptions;
import com.coinbase.api.internal.models.idology.options.IdologyOptions.OptionType;
import com.google.gson.Gson;
import java.util.List;
import javax.inject.Inject;

@ActivityScope
public class IdologyOptionDialogFragment extends SpinnerDialogFragment<Data> {
    public static String IDOLOGY_DATA = "IDOLOGY_DATA";
    public static String IDOLOGY_OPTION_TYPE = "IDOLOGY_OPTION_TYPE";
    @Inject
    IdologyOptionSelectedConnector mIdologyOptionSelectedConnector;
    private OptionType mOptionType;
    protected List<Data> options;
    private Data selectedOption;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ComponentProvider) getActivity().getApplicationContext()).applicationComponent().fragmentSubcomponent().inject(this);
    }

    public String getOptionDisplayText(Data option) {
        return option.getTitle();
    }

    public Data[] getOptions() {
        return (Data[]) this.options.toArray(new Data[this.options.size()]);
    }

    public void onChosenValue(Data chosenValue) {
        this.selectedOption = chosenValue;
        this.mIdologyOptionSelectedConnector.get().onNext(new Pair(this.selectedOption, this.mOptionType));
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.options = getIdologyOptions();
        return super.onCreateDialog(savedInstanceState);
    }

    private List<Data> getIdologyOptions() {
        Gson gson = new Gson();
        Bundle args = getArguments();
        String json = args.getString(IDOLOGY_DATA);
        this.mOptionType = (OptionType) args.getSerializable(IDOLOGY_OPTION_TYPE);
        this.options = ((IdologyOptions) gson.fromJson(json, IdologyOptions.class)).getData();
        return this.options;
    }
}
