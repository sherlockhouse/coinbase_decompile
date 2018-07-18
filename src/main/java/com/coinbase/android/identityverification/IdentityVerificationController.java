package com.coinbase.android.identityverification;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.MainActivitySubcomponentProvider;
import com.coinbase.android.R;
import com.coinbase.android.databinding.ControllerIdentityVerificationBinding;
import com.coinbase.android.databinding.ListItemJumioProfileBinding;
import com.coinbase.android.task.GetUserTask.AdminFlags;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.BundleWrapper;
import com.coinbase.android.utils.Utils;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.models.jumio.Data;
import com.coinbase.api.internal.models.jumio.JumioProfiles;
import com.coinbase.api.internal.models.jumio.JumioProfiles.Status;
import com.coinbase.api.internal.models.jumio.JumioProfiles.Type;
import com.squareup.picasso.Picasso;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

@ActivityScope
public class IdentityVerificationController extends ActionBarController {
    private static final int INTENT_VERIFY = 10001;
    JumioProfileAdapter mAdapter;
    ControllerIdentityVerificationBinding mBinding;
    @Inject
    LoginManager mLoginManager;
    @MainScheduler
    @Inject
    Scheduler mMainScheduler;
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    private class JumioProfileAdapter extends ArrayAdapter<Data> {
        JumioProfileAdapter(Context context) {
            super(context, 0);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ListItemJumioProfileBinding binding;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_jumio_profile, parent, false);
                binding = (ListItemJumioProfileBinding) DataBindingUtil.bind(convertView);
                convertView.setTag(binding);
            } else {
                binding = (ListItemJumioProfileBinding) convertView.getTag();
            }
            Data profile = (Data) getItem(position);
            if (profile != null) {
                String statusString;
                Status status = Status.INCOMPLETE;
                if (profile.getStatus() != null) {
                    status = profile.getStatus();
                }
                switch (status) {
                    case FAILED:
                        statusString = IdentityVerificationController.this.getApplicationContext().getString(R.string.jumio_status_failed);
                        break;
                    case COMPLETED:
                        statusString = IdentityVerificationController.this.getApplicationContext().getString(R.string.jumio_status_completed);
                        break;
                    case INCOMPLETE:
                    case PENDING:
                        statusString = IdentityVerificationController.this.getApplicationContext().getString(R.string.jumio_status_new);
                        break;
                    default:
                        statusString = "";
                        break;
                }
                binding.tvJumioProfileStatus.setText(statusString);
                Type profileType = profile.getType();
                if (profileType != null) {
                    binding.tvJumioProfileName.setText(JumioProfiles.getDisplayName(getContext(), profileType));
                }
                Picasso.with(getContext()).load(profile.getCountry().getImage()).into(binding.ivJumioFlag);
            }
            return convertView;
        }
    }

    public IdentityVerificationController(Bundle args) {
        super(args);
    }

    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        this.mBinding = (ControllerIdentityVerificationBinding) DataBindingUtil.inflate(inflater, R.layout.controller_identity_verification, container, false);
        onAttachToolbar(this.mBinding.cvCoinbaseToolbar);
        setForceDisplayHomeAsUp(true);
        ((MainActivitySubcomponentProvider) getActivity()).mainActivitySubcomponent().inject(this);
        return this.mBinding.getRoot();
    }

    protected void onAttach(View view) {
        super.onAttach(view);
        this.mAdapter = new JumioProfileAdapter(getActivity());
        this.mBinding.identityVerificationContent.lvList.setAdapter(this.mAdapter);
    }

    protected void onShow() {
        super.onShow();
        getJumioProfiles();
    }

    protected void onHide() {
        super.onHide();
        this.mSubscription.clear();
    }

    public void onShowOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.menu_jumio, menu);
    }

    public boolean onShownOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_jumio_add:
                onAddVerificationMenuClicked();
                return true;
            default:
                return super.onShownOptionsItemSelected(item);
        }
    }

    private void onAddVerificationMenuClicked() {
        pushModalController(new InAppIdentityAcceptableDocumentsController(appendModalArgs(new BundleWrapper().passAlongBoolean(getArgs(), new Bundle(), AdminFlags.REQUIRE_JUMIO_FACE_MATCH.toString()))));
        MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_JUMIO_START_VERIFICATION, new String[0]);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10001 && resultCode == -1) {
            getActivity().setResult(-1);
            getActivity().finish();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected SpannableStringBuilder getTitle() {
        return new SpannableStringBuilder(getApplicationContext().getString(R.string.identity_verifications));
    }

    private void getJumioProfiles() {
        this.mSubscription.add(this.mLoginManager.getClient().getJumioProfilesRx().observeOn(this.mMainScheduler).subscribe(IdentityVerificationController$$Lambda$1.lambdaFactory$(this), IdentityVerificationController$$Lambda$2.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$getJumioProfiles$0(IdentityVerificationController this_, Pair pair) {
        if (this_.getActivity() != null && this_.isAttached()) {
            Response<JumioProfiles> response = pair.first;
            Retrofit retrofit = pair.second;
            this_.mBinding.identityVerificationContent.progress.setVisibility(8);
            if (response.isSuccessful()) {
                this_.mBinding.identityVerificationContent.progress.setVisibility(8);
                this_.mBinding.identityVerificationContent.lvList.setVisibility(0);
                this_.mAdapter.clear();
                this_.mAdapter.addAll(this_.filterJumioProfiles(((JumioProfiles) response.body()).getData()));
                this_.mAdapter.notifyDataSetChanged();
                if (this_.mAdapter.getCount() == 0) {
                    this_.mBinding.identityVerificationContent.tvEmpty.setVisibility(0);
                    return;
                } else {
                    this_.mBinding.identityVerificationContent.tvEmpty.setVisibility(8);
                    return;
                }
            }
            Utils.showMessage(this_.getActivity(), Utils.getErrorMessage(response, retrofit), 1);
        }
    }

    static /* synthetic */ void lambda$getJumioProfiles$1(IdentityVerificationController this_, Throwable t) {
        if (this_.getActivity() != null && this_.isAttached()) {
            this_.mBinding.identityVerificationContent.progress.setVisibility(8);
            Utils.showMessage(this_.getActivity(), t, 1);
        }
    }

    private List<Data> filterJumioProfiles(List<Data> jumioProfiles) {
        LinkedList<Data> filteredJumioProfiles = new LinkedList();
        for (Data profile : jumioProfiles) {
            if (profile.getType() != Type.UNKNOWN) {
                filteredJumioProfiles.add(profile);
            }
        }
        return filteredJumioProfiles;
    }
}
