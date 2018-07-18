package com.coinbase.android.deposits;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout.LayoutParams;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.CoinbaseActivityMystiqueSubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.FragmentSepaDepositWithdrawBinding;
import com.coinbase.android.databinding.ListItemSepaDepositInfoBinding;
import com.coinbase.android.utils.Utils;
import com.coinbase.api.internal.models.sepaDepositInfo.Data;
import java.util.List;
import javax.inject.Inject;

@ActivityScope
public class SepaDepositFragment extends Fragment implements SepaDepositScreen {
    private boolean isFromDeposit = false;
    FragmentSepaDepositWithdrawBinding mBinding;
    @Inject
    SepaDepositPresenter mPresenter;

    public class SepaDepositAdapter extends Adapter<ViewHolder> {
        private Context mContext;
        List<Data> mInfo;

        public class ViewHolderSepa extends ViewHolder {
            private final ListItemSepaDepositInfoBinding mBinding;
            Context mContext;

            public ViewHolderSepa(Context context, View view) {
                super(view);
                this.mContext = context;
                this.mBinding = (ListItemSepaDepositInfoBinding) DataBindingUtil.bind(view);
            }

            ListItemSepaDepositInfoBinding getBinding() {
                return this.mBinding;
            }
        }

        public SepaDepositAdapter(Context context, List<Data> info) {
            this.mContext = context;
            this.mInfo = info;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolderSepa(this.mContext, LayoutInflater.from(this.mContext).inflate(R.layout.list_item_sepa_deposit_info, parent, false));
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            ListItemSepaDepositInfoBinding binding = ((ViewHolderSepa) holder).getBinding();
            final Data sepaInfo = (Data) this.mInfo.get(position);
            LayoutParams lp = (LayoutParams) binding.tvSepaContent.getLayoutParams();
            if (sepaInfo.getTitle() != null) {
                binding.tvSepaTitle.setText(sepaInfo.getTitle());
                binding.tvSepaTitle.setVisibility(0);
                binding.ibtnCopy.setVisibility(0);
                lp.topMargin = 0;
            } else {
                binding.tvSepaTitle.setVisibility(8);
                binding.ibtnCopy.setVisibility(8);
                lp.topMargin = (int) SepaDepositFragment.this.getResources().getDimension(R.dimen.margin_normal);
            }
            binding.tvSepaContent.setText(sepaInfo.getValue());
            if (sepaInfo.getHighlight().booleanValue()) {
                binding.tvSepaContent.setTextColor(SepaDepositFragment.this.getResources().getColor(R.color.sepa_green));
                binding.tvSepaDetail.setVisibility(0);
                binding.tvSepaDetail.setText(sepaInfo.getDescription());
            } else {
                binding.tvSepaContent.setTextColor(SepaDepositFragment.this.getResources().getColor(R.color.extra_dark_grey_text2));
                binding.tvSepaDetail.setVisibility(8);
            }
            binding.ibtnCopy.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    SepaDepositFragment.this.mPresenter.onCopyButtonClicked(sepaInfo.getValue());
                }
            });
        }

        public int getItemCount() {
            return this.mInfo.size();
        }
    }

    public static SepaDepositFragment newInstance(boolean fromDeposit) {
        SepaDepositFragment f = new SepaDepositFragment();
        f.isFromDeposit = fromDeposit;
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentSepaDepositWithdrawBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_sepa_deposit_withdraw, container, false);
        ((CoinbaseActivityMystiqueSubcomponentProvider) getActivity()).coinbaseActivityMystiqueSubcomponent().sepaDepositFragmentSubcomponent(new SepaDepositPresenterModule(this)).inject(this);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mPresenter.onViewCreated();
    }

    public void showError(String error) {
        this.mBinding.progress.setVisibility(8);
        Utils.showMessage(getContext(), error, 1);
    }

    public void showSepaDepositInfo(List<Data> info) {
        this.mBinding.progress.setVisibility(8);
        this.mBinding.rvSepaDepositInfo.setAdapter(new SepaDepositAdapter(getContext(), info));
        this.mBinding.rvSepaDepositInfo.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
    }

    public Boolean getFromDeposit() {
        return Boolean.valueOf(this.isFromDeposit);
    }

    public void setActivityTitle(String title) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setTitle(title);
        }
    }
}
