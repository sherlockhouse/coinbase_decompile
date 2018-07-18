package com.coinbase.android.paymentmethods.linkedaccounts;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.widget.ImageView;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.R;
import com.coinbase.android.event.ClassConsumableEvent;
import com.coinbase.android.ui.BackNavigationConnector;
import com.coinbase.android.utils.PaymentMethodUtils;
import com.coinbase.v2.models.paymentMethods.Data;
import com.coinbase.v2.models.paymentMethods.Data.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

@ControllerScope
public class LinkedAccountsPickerPresenter {
    public static final String PAYMENT_METHOD_LIST = "PAYMENT_METHOD_LIST";
    public static final String SELECTED_PAYMENT_METHOD = "SELECTED_PAYMENT_METHOD";
    private final BackNavigationConnector mBackNavigationConnector;
    private final Context mContext;
    private final Gson mGson = new Gson();
    private final LinkedAccountConnector mLinkedAccountConnector;
    private final PaymentMethodUtils mPaymentMethodUtils;
    private List<Object> mPaymentMethodsAndHeader = new ArrayList();
    private final LinkedAccountsPickerScreen mScreen;
    private Data mSelectedPaymentMethod;

    enum PaymentGroup {
        FIAT(R.string.linked_account_fiat_header),
        BANK(R.string.linked_account_banks_header),
        CARD(R.string.linked_account_cards_header),
        OTHER(R.string.linked_account_other_header);
        
        private final int mHeaderResourceStr;

        private PaymentGroup(int resourceStr) {
            this.mHeaderResourceStr = resourceStr;
        }

        public int getHeaderResourceStr() {
            return this.mHeaderResourceStr;
        }

        public static PaymentGroup fromType(Type type) {
            switch (type) {
                case FIAT_ACCOUNT:
                    return FIAT;
                case ACH_BANK_ACCOUNT:
                case SEPA_BANK_ACCOUNT:
                case BANK_WIRE:
                case XFERS:
                    return BANK;
                case CREDIT_CARD:
                case DEBIT_CARD:
                case SECURE_3DS:
                case WORLDPAY_CARD:
                    return CARD;
                default:
                    return OTHER;
            }
        }
    }

    @Inject
    LinkedAccountsPickerPresenter(Application application, LinkedAccountsPickerScreen screen, PaymentMethodUtils paymentMethodUtils, LinkedAccountConnector linkedAccountConnector, BackNavigationConnector backNavigationConnector) {
        this.mContext = application;
        this.mScreen = screen;
        this.mPaymentMethodUtils = paymentMethodUtils;
        this.mLinkedAccountConnector = linkedAccountConnector;
        this.mBackNavigationConnector = backNavigationConnector;
    }

    void onCreateView(Bundle args) {
        if (args != null) {
            if (args.containsKey(SELECTED_PAYMENT_METHOD)) {
                this.mSelectedPaymentMethod = (Data) this.mGson.fromJson(args.getString(SELECTED_PAYMENT_METHOD), Data.class);
            }
            String paymentMethodString = args.getString(PAYMENT_METHOD_LIST);
            if (paymentMethodString != null) {
                List<Data> paymentMethodList = (List) this.mGson.fromJson(paymentMethodString, new TypeToken<ArrayList<Data>>() {
                }.getType());
                if (paymentMethodList != null) {
                    this.mPaymentMethodsAndHeader.clear();
                    List<Object> paymentMethodsAndHeader = insertHeadersIntoPaymentMethodsList(filterPaymentMethods(paymentMethodList));
                    if (paymentMethodsAndHeader != null) {
                        this.mPaymentMethodsAndHeader.addAll(paymentMethodsAndHeader);
                    }
                }
            }
        }
    }

    public List<Data> filterPaymentMethods(List<Data> paymentMethods) {
        List<Data> filteredPaymentMethods = new ArrayList();
        if (!paymentMethods.isEmpty()) {
            Collections.sort(paymentMethods, LinkedAccountsPickerPresenter$$Lambda$1.lambdaFactory$());
            filteredPaymentMethods.addAll(paymentMethods);
        }
        return filteredPaymentMethods;
    }

    static /* synthetic */ int lambda$filterPaymentMethods$0(Data lhs, Data rhs) {
        PaymentGroup lhsType = lhs.getLimits() == null ? null : PaymentGroup.fromType(lhs.getType());
        PaymentGroup rhsType = rhs.getLimits() == null ? null : PaymentGroup.fromType(rhs.getType());
        if (lhsType == null && rhsType == null) {
            return 0;
        }
        if (lhsType == null) {
            return -1;
        }
        if (rhsType == null) {
            return 1;
        }
        return lhsType.compareTo(rhsType);
    }

    public List<Object> insertHeadersIntoPaymentMethodsList(List<Data> paymentMethods) {
        if (paymentMethods == null || paymentMethods.isEmpty()) {
            return new LinkedList(paymentMethods);
        }
        List<Object> paymentMethodsWithHeaders = new LinkedList();
        PaymentGroup currentType = null;
        for (Data paymentMethod : paymentMethods) {
            PaymentGroup group = PaymentGroup.fromType(paymentMethod.getType());
            if (currentType != group) {
                paymentMethodsWithHeaders.add(this.mContext.getString(group.getHeaderResourceStr()));
                currentType = group;
            }
            paymentMethodsWithHeaders.add(paymentMethod);
        }
        return paymentMethodsWithHeaders;
    }

    List<Object> getPaymentMethodAndHeaderList() {
        return this.mPaymentMethodsAndHeader;
    }

    Data getSelectedPaymentMethod() {
        return this.mSelectedPaymentMethod;
    }

    String getFiatAccountCurrencySymbol(Data paymentMethod) {
        if (paymentMethod == null) {
            return null;
        }
        return this.mPaymentMethodUtils.getFiatAccountCurrencySymbol(paymentMethod);
    }

    String getFiatBalance(Data paymentMethod) {
        if (paymentMethod == null) {
            return null;
        }
        return this.mPaymentMethodUtils.getFiatBalanceFromAmount(paymentMethod);
    }

    Pair<String, String> getFormattedNameAndNumberDisplay(Data paymentMethod) {
        if (paymentMethod == null) {
            return null;
        }
        return this.mPaymentMethodUtils.getFormattedNameAndNumberDisplay(paymentMethod);
    }

    int getResourceForType(Type type) {
        return this.mPaymentMethodUtils.getResourceForType(type);
    }

    void onPaymentMethodClicked(Data paymentMethod) {
        this.mBackNavigationConnector.get().onNext(null);
        this.mLinkedAccountConnector.getPaymentMethodSelectedSubject().onNext(new Pair(new ClassConsumableEvent(), paymentMethod));
    }

    void setFiatImageBackground(ImageView imageView, Data paymentMethod) {
        this.mPaymentMethodUtils.setFiatImageBackground(this.mContext, imageView, paymentMethod);
    }
}
