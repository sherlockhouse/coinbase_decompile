package com.coinbase.android.task;

import android.content.Context;
import android.util.Pair;
import com.coinbase.android.ActivityScope;
import com.coinbase.android.BackgroundScheduler;
import com.coinbase.android.ComponentProvider;
import com.coinbase.android.accounts.AccountUpdatedConnector;
import com.coinbase.android.db.AccountORM;
import com.coinbase.android.db.DatabaseManager;
import com.coinbase.android.ui.MainScheduler;
import com.coinbase.android.utils.AccountUtils;
import com.coinbase.android.utils.MoneyFormatterUtil;
import com.coinbase.api.LoginManager;
import com.coinbase.v2.models.account.Account;
import com.coinbase.v2.models.account.Data;
import com.coinbase.v2.models.price.Price;
import java.util.HashMap;
import javax.inject.Inject;
import org.joda.money.CurrencyUnit;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;

@ActivityScope
public class FetchAccountTask {
    @Inject
    AccountUpdatedConnector mAccountUpdatedConnector;
    @Inject
    @BackgroundScheduler
    Scheduler mBackgroundScheduler;
    @Inject
    DatabaseManager mDbManager;
    @Inject
    LoginManager mLoginManager;
    @MainScheduler
    @Inject
    Scheduler mMainScheduler;
    @Inject
    MoneyFormatterUtil mMoneyFormatterUtil;

    public FetchAccountTask(Context context) {
        ((ComponentProvider) context.getApplicationContext()).applicationComponent().tasksSubcomponent().inject(this);
    }

    public void execute(Data account) {
        if (account != null) {
            switch (account.getType()) {
                case FIAT:
                    fetchAccount(account);
                    return;
                default:
                    fetchCryptoAccount(account);
                    return;
            }
        }
    }

    private void fetchAccount(Data mSelectedAccount) {
        this.mLoginManager.getClient().getAccountRx(mSelectedAccount.getId()).first().map(FetchAccountTask$$Lambda$1.lambdaFactory$(this)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainScheduler).subscribe(FetchAccountTask$$Lambda$2.lambdaFactory$(this), FetchAccountTask$$Lambda$3.lambdaFactory$(this));
    }

    static /* synthetic */ Pair lambda$fetchAccount$0(FetchAccountTask this_, Pair pair) {
        Response<Account> response = pair.first;
        if (response.isSuccessful()) {
            this_.updateAccountORM(((Account) response.body()).getData());
        }
        return pair;
    }

    static /* synthetic */ void lambda$fetchAccount$1(FetchAccountTask this_, Pair pair) {
        Response<Account> response = pair.first;
        if (response.isSuccessful()) {
            this_.mAccountUpdatedConnector.get().onNext(((Account) response.body()).getData());
        } else {
            this_.mAccountUpdatedConnector.get().onNext(null);
        }
    }

    private void fetchCryptoAccount(Data mSelectedAccount) {
        CurrencyUnit currencyUnit = this.mLoginManager.getCurrencyUnit();
        if (currencyUnit != null) {
            Observable.combineLatest(this.mLoginManager.getClient().getAccountRx(mSelectedAccount.getId()), this.mLoginManager.getClient().getSpotPriceRx(mSelectedAccount.getCurrency().getCode(), currencyUnit.getCurrencyCode(), new HashMap()), FetchAccountTask$$Lambda$4.lambdaFactory$()).map(FetchAccountTask$$Lambda$5.lambdaFactory$(this)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainScheduler).subscribe(FetchAccountTask$$Lambda$6.lambdaFactory$(this), FetchAccountTask$$Lambda$7.lambdaFactory$(this));
        }
    }

    static /* synthetic */ Data lambda$fetchCryptoAccount$4(FetchAccountTask this_, Pair response) {
        Response<Account> accountResponse = response.first.first;
        if (!accountResponse.isSuccessful()) {
            return null;
        }
        Data accountData = ((Account) accountResponse.body()).getData();
        Response<Price> spotPriceResponse = response.second.first;
        if (spotPriceResponse.isSuccessful()) {
            AccountUtils.setAccountNativeBalanceMoney(accountData, ((Price) spotPriceResponse.body()).getData(), this_.mMoneyFormatterUtil);
            this_.updateAccountORM(accountData);
            return accountData;
        }
        this_.updateAccountORM(accountData);
        return accountData;
    }

    private void updateAccountORM(Data account) {
        try {
            AccountORM.update(this.mDbManager.openDatabase(), account);
        } finally {
            this.mDbManager.closeDatabase();
        }
    }
}
