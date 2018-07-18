package com.coinbase.android.idology;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Pair;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.dialog.SpinnerDialogFragment;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
public class IdologyQuestionDialogFragment extends SpinnerDialogFragment<String> {
    public static String IDOLOGY_ANSWERS = "idology_answers";
    public static String IDOLOGY_TYPE = "idology_type";
    private List<String> mAnswerList = new ArrayList();
    @Inject
    IdologyAnswerSelectedConnector mIdologyAnswerSelectedConnector;
    private String mSelectedAnswer;
    private String mType;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ComponentProvider) getActivity().getApplicationContext()).applicationComponent().fragmentSubcomponent().inject(this);
    }

    public String[] getOptions() {
        return (String[]) this.mAnswerList.toArray(new String[this.mAnswerList.size()]);
    }

    public String getOptionDisplayText(String title) {
        return title;
    }

    public void onChosenValue(String chosenValue) {
        this.mSelectedAnswer = chosenValue;
        this.mIdologyAnswerSelectedConnector.get().onNext(new Pair(this.mType, this.mSelectedAnswer));
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.mAnswerList = getQuestionList();
        return super.onCreateDialog(savedInstanceState);
    }

    private List<String> getQuestionList() {
        Bundle args = getArguments();
        if (args != null) {
            this.mAnswerList = args.getStringArrayList(IDOLOGY_ANSWERS);
            this.mType = args.getString(IDOLOGY_TYPE);
        }
        return this.mAnswerList;
    }
}
