package com.coinbase.android.deposits;

import com.coinbase.api.internal.models.sepaDepositInfo.Data;
import java.util.List;

public interface SepaDepositScreen {
    Boolean getFromDeposit();

    void setActivityTitle(String str);

    void showError(String str);

    void showSepaDepositInfo(List<Data> list);
}
