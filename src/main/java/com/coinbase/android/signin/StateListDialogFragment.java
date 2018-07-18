package com.coinbase.android.signin;

import android.app.Dialog;
import android.os.Bundle;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.dialog.SpinnerDialogFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.io.IOUtils;

@ActivityScope
public class StateListDialogFragment extends SpinnerDialogFragment<HashMap<String, String>> {
    @Inject
    StateListSelectorConnector mConnector;
    protected List<HashMap<String, String>> states;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ComponentProvider) getActivity().getApplicationContext()).applicationComponent().fragmentSubcomponent().inject(this);
    }

    public String getOptionDisplayText(HashMap<String, String> option) {
        return (String) option.get("name");
    }

    public HashMap<String, String>[] getOptions() {
        return (HashMap[]) this.states.toArray(new HashMap[this.states.size()]);
    }

    public void onChosenValue(HashMap<String, String> chosenValue) {
        this.mConnector.get().onNext(chosenValue);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.states = getStates();
        return super.onCreateDialog(savedInstanceState);
    }

    private List<HashMap<String, String>> getStates() {
        try {
            return (List) new Gson().fromJson(IOUtils.toString(getResources().openRawResource(R.raw.states), "UTF-8"), new TypeToken<List<HashMap<String, String>>>() {
            }.getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
