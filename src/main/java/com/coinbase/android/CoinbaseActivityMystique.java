package com.coinbase.android;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import com.coinbase.android.databinding.ActivityModalMystiqueBinding;
import com.coinbase.android.ui.ActionBarProvider;
import com.coinbase.android.ui.BaseViewModule;
import com.coinbase.android.ui.BaseViewProvider;

public class CoinbaseActivityMystique extends CoinbaseActivity implements CoinbaseActivityMystiqueSubcomponentProvider, ActionBarProvider, BaseViewProvider {
    protected ActivityModalMystiqueBinding mBinding;
    private CoinbaseActivityMystiqueSubcomponent mSubcomponent;

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        this.mBinding = (ActivityModalMystiqueBinding) DataBindingUtil.setContentView(this, R.layout.activity_modal_mystique);
        this.mSubcomponent = ((ComponentProvider) getApplicationContext()).applicationComponent().coinbaseActivityMystiqueSubcomponent(new BaseActivityModule(this), new BaseViewModule(this), new ActionBarModule(this), new NonBottomNavActivityModule());
        initToolbar(this.mBinding.cvCoinbaseToolbar);
    }

    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        this.mBinding = (ActivityModalMystiqueBinding) DataBindingUtil.setContentView(this, R.layout.activity_modal_mystique);
        initToolbar(this.mBinding.cvCoinbaseToolbar);
    }

    public View getBaseView() {
        return this.mBinding.flContainer;
    }

    public RelativeLayout getBlockingOverlayView() {
        return this.mBinding.flOverlay;
    }

    public AppCompatActivity getActivity() {
        return this;
    }

    public Toolbar getToolbar() {
        return this.mBinding.cvCoinbaseToolbar;
    }

    public CoinbaseActivityMystiqueSubcomponent coinbaseActivityMystiqueSubcomponent() {
        return this.mSubcomponent;
    }
}
