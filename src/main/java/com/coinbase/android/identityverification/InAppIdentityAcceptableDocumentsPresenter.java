package com.coinbase.android.identityverification;

import android.app.Application;
import android.content.Context;
import com.coinbase.CallbackWithRetrofit;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.ui.SnackBarWrapper;
import com.coinbase.android.utils.analytics.MixpanelTracking;
import com.coinbase.api.LoginManager;
import com.coinbase.api.internal.ModelGsonAdapterFactory;
import com.coinbase.api.internal.models.jumio.JumioProfiles;
import com.coinbase.api.internal.models.jumio.JumioProfiles.Type;
import com.coinbase.api.internal.models.jumio.supportedDocuments.Data;
import com.coinbase.api.internal.models.jumio.supportedDocuments.SupportedDocument;
import com.coinbase.api.internal.models.jumio.supportedDocuments.SupportedIdType;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@ControllerScope
public class InAppIdentityAcceptableDocumentsPresenter {
    private final BackNavigationConnector mBackNavigationConnector;
    private final Context mContext;
    private List<Integer> mDocIcons;
    private List<String> mDocList;
    private final IdentityVerificationBitmapConnector mIdentityVerificationBitmapConnector;
    private Data mJumioDoc;
    private final LoginManager mLoginManager;
    private final InAppIdentityAcceptableDocumentsRouter mRouter;
    private final IdentityAcceptableDocumentsScreen mScreen;
    private final SnackBarWrapper mSnackBarWrapper;

    @Inject
    public InAppIdentityAcceptableDocumentsPresenter(IdentityAcceptableDocumentsScreen screen, LoginManager loginManager, Application app, SnackBarWrapper snackBarWrapper, BackNavigationConnector backNavigationConnector, IdentityVerificationBitmapConnector bitmapConnector, InAppIdentityAcceptableDocumentsRouter router) {
        this.mScreen = screen;
        this.mLoginManager = loginManager;
        this.mContext = app;
        this.mSnackBarWrapper = snackBarWrapper;
        this.mBackNavigationConnector = backNavigationConnector;
        this.mIdentityVerificationBitmapConnector = bitmapConnector;
        this.mRouter = router;
    }

    public void onAttach() {
        getJumioSupportedDocument();
    }

    public void onItemClick(int position) {
        this.mIdentityVerificationBitmapConnector.get().onNext(null);
        this.mRouter.routeToNext(new GsonBuilder().registerTypeAdapterFactory(ModelGsonAdapterFactory.create()).create().toJson(this.mJumioDoc), position);
        SupportedIdType idType = (SupportedIdType) this.mJumioDoc.getSupportedIdTypes().get(position);
        if (idType != null && idType.getType() != null) {
            MixpanelTracking.getInstance().trackEvent(MixpanelTracking.EVENT_JUMIO_DOC_TYPE_SELECT, "id_type", idType.getType().toString());
        }
    }

    private void getJumioSupportedDocument() {
        this.mLoginManager.getClient().getJumioSupportedDocument(this.mLoginManager.getActiveUserCountryCode(), new CallbackWithRetrofit<SupportedDocument>() {
            public void onResponse(Call<SupportedDocument> call, Response<SupportedDocument> response, Retrofit retrofit) {
                if (InAppIdentityAcceptableDocumentsPresenter.this.mScreen.isShown()) {
                    InAppIdentityAcceptableDocumentsPresenter.this.mScreen.hideProgress();
                    if (response.isSuccessful()) {
                        InAppIdentityAcceptableDocumentsPresenter.this.mJumioDoc = ((SupportedDocument) response.body()).getData();
                        InAppIdentityAcceptableDocumentsPresenter.this.setUpDocumentViews();
                        return;
                    }
                    InAppIdentityAcceptableDocumentsPresenter.this.mSnackBarWrapper.showError(response);
                    InAppIdentityAcceptableDocumentsPresenter.this.handleJumioError();
                }
            }

            public void onFailure(Call<SupportedDocument> call, Throwable t) {
                if (InAppIdentityAcceptableDocumentsPresenter.this.mScreen.isShown()) {
                    InAppIdentityAcceptableDocumentsPresenter.this.mSnackBarWrapper.showGenericErrorTryAgain();
                    InAppIdentityAcceptableDocumentsPresenter.this.mScreen.hideProgress();
                    InAppIdentityAcceptableDocumentsPresenter.this.handleJumioError();
                }
            }
        });
    }

    private void handleJumioError() {
        if (this.mScreen.isShown()) {
            this.mBackNavigationConnector.get().onNext(null);
        }
    }

    private void setUpDocumentViews() {
        List<SupportedIdType> docIds = this.mJumioDoc.getSupportedIdTypes();
        this.mDocList = new ArrayList();
        this.mDocIcons = new ArrayList();
        for (SupportedIdType id : docIds) {
            this.mDocList.add(JumioProfiles.getDisplayName(this.mContext, id.getType()));
            if (id.getType() == Type.PASSPORT) {
                this.mDocIcons.add(Integer.valueOf(R.drawable.ic_docveri_passport));
            } else {
                this.mDocIcons.add(Integer.valueOf(R.drawable.ic_docveri_license));
            }
        }
        this.mScreen.initializeDocsList(this.mDocList, this.mDocIcons);
    }
}
