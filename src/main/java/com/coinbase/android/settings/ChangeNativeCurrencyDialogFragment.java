package com.coinbase.android.settings;

import android.app.Dialog;
import android.os.Bundle;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.dialog.SpinnerDialogFragment;
import com.coinbase.android.ui.CurrencySelectorConnector;
import java.util.List;
import javax.inject.Inject;
import org.joda.money.CurrencyUnit;

@ActivityScope
public class ChangeNativeCurrencyDialogFragment extends SpinnerDialogFragment<CurrencyUnit> {
    public static final String CURRENCIES = "ChooseCurrenciesDialogFragment_Currencies";
    protected List<CurrencyUnit> mCurrencies;
    @Inject
    CurrencySelectorConnector mCurrencySelectorConnector;
    private String selectedCurrency;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ComponentProvider) getActivity().getApplicationContext()).applicationComponent().fragmentSubcomponent().inject(this);
    }

    public String getOptionDisplayText(CurrencyUnit option) {
        return option.getCurrencyCode();
    }

    public CurrencyUnit[] getOptions() {
        return (CurrencyUnit[]) this.mCurrencies.toArray(new CurrencyUnit[this.mCurrencies.size()]);
    }

    public void onChosenValue(CurrencyUnit newValue) {
        this.selectedCurrency = newValue.getCurrencyCode();
        this.mCurrencySelectorConnector.getNative().onNext(this.selectedCurrency);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.mCurrencies = (List) getArguments().getSerializable(CURRENCIES);
        return super.onCreateDialog(savedInstanceState);
    }
}
