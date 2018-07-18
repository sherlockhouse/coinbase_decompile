package com.coinbase.android.settings.idology;

import android.os.Bundle;
import android.text.TextUtils;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.internal.CoinbaseAdapterFactory;
import com.coinbase.api.internal.models.idology.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.inject.Inject;

@ControllerScope
public class IdologyArgsBuilder {
    public static final String BUILDING_IDOLOGY_DATA = "building_idology_data";
    public static final String IDOLOGY_DATA = "idology_data";
    private final ActionBarController mController;

    @Inject
    public IdologyArgsBuilder(ActionBarController controller) {
        this.mController = controller;
    }

    public Bundle getIdologyArgs(Data idology) {
        Bundle args = new Bundle();
        if (this.mController == null) {
            return args;
        }
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create();
        if (idology != null) {
            args.putString(IDOLOGY_DATA, gson.toJson((Object) idology));
        }
        return this.mController.appendModalArgs(args);
    }

    public Data getBuildingIdologyData(Data buildingIdology) {
        if (this.mController == null) {
            return null;
        }
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create();
        String previousIdologyStr = this.mController.getArgs().getString(BUILDING_IDOLOGY_DATA);
        if (!TextUtils.isEmpty(previousIdologyStr)) {
            buildingIdology = update((Data) gson.fromJson(previousIdologyStr, Data.class), buildingIdology);
        }
        return buildingIdology;
    }

    public Bundle getBuildingIdologyArgs(Data buildingIdology) {
        Bundle args = new Bundle();
        if (this.mController == null) {
            return args;
        }
        Object buildingIdology2 = getBuildingIdologyData(buildingIdology);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(CoinbaseAdapterFactory.create()).create();
        if (buildingIdology2 != null) {
            args.putString(BUILDING_IDOLOGY_DATA, gson.toJson(buildingIdology2));
        }
        return this.mController.appendModalArgs(args);
    }

    public Data update(Data previousData, Data newData) {
        return Data.builder().setId(newData.getId() != null ? newData.getId() : previousData.getId()).setCreatedAt(newData.getCreatedAt() != null ? newData.getCreatedAt() : previousData.getCreatedAt()).setUpdatedAt(newData.getUpdatedAt() != null ? newData.getUpdatedAt() : previousData.getUpdatedAt()).setStatus(newData.getStatus() != null ? newData.getStatus() : previousData.getStatus()).setMethod(newData.getMethod() != null ? newData.getMethod() : previousData.getMethod()).setInformationalOnly(newData.getInformationalOnly()).setFirstName(newData.getFirstName() != null ? newData.getFirstName() : previousData.getFirstName()).setLastName(newData.getLastName() != null ? newData.getLastName() : previousData.getLastName()).setAddress(newData.getAddress() != null ? newData.getAddress() : previousData.getAddress()).setDateOfBirth(newData.getDateOfBirth() != null ? newData.getDateOfBirth() : previousData.getDateOfBirth()).setMobilePhoneNumber(newData.getMobilePhoneNumber() != null ? newData.getMobilePhoneNumber() : previousData.getMobilePhoneNumber()).setUsesCoinbaseFor(newData.getUsesCoinbaseFor() != null ? newData.getUsesCoinbaseFor() : previousData.getUsesCoinbaseFor()).setPrimarySourceOfFunds(newData.getPrimarySourceOfFunds() != null ? newData.getPrimarySourceOfFunds() : previousData.getPrimarySourceOfFunds()).setCurrentJob(newData.getCurrentJob() != null ? newData.getCurrentJob() : previousData.getCurrentJob()).setFormerJob(newData.getFormerJob() != null ? newData.getFormerJob() : previousData.getFormerJob()).setSsnLast4(newData.getSsnLast4() != null ? newData.getSsnLast4() : previousData.getSsnLast4()).setQuestions(newData.getQuestions() != null ? newData.getQuestions() : previousData.getQuestions()).build();
    }
}
