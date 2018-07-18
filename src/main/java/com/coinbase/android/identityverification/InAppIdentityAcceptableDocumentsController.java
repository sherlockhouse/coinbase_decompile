package com.coinbase.android.identityverification;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerAcceptableDocumentsBinding;
import com.coinbase.android.ui.ActionBarController;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
public class InAppIdentityAcceptableDocumentsController extends ActionBarController implements IdentityAcceptableDocumentsScreen {
    AcceptableDocumentsAdapter mAdapter;
    ControllerAcceptableDocumentsBinding mBinding;
    @Inject
    InAppIdentityAcceptableDocumentsPresenter mPresenter;

    public InAppIdentityAcceptableDocumentsController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerAcceptableDocumentsBinding) DataBindingUtil.inflate(inflater, R.layout.controller_acceptable_documents, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        setForceDisplayHomeAsUp(true);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().addInAppAcceptableDocumentsControllerSubcomponent(new InAppIdentityAcceptableDocumentsPresenterModule(this, this)).inject(this);
        return this.mBinding.getRoot();
    }

    protected void onAttach(View view) {
        super.onAttach(view);
        this.mPresenter.onAttach();
    }

    protected void onDetach(View view) {
        super.onDetach(view);
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.verify_identity_title));
    }

    public void hideProgress() {
        this.mBinding.progress.setVisibility(8);
    }

    public void initializeDocsList(List<String> docsList, List<Integer> docsIcons) {
        this.mAdapter = new AcceptableDocumentsAdapter(getActivity(), docsList, docsIcons);
        this.mBinding.acceptableDocumentsContent.lvList.setAdapter(this.mAdapter);
        this.mBinding.acceptableDocumentsContent.lvList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                InAppIdentityAcceptableDocumentsController.this.mPresenter.onItemClick(position);
            }
        });
    }
}
