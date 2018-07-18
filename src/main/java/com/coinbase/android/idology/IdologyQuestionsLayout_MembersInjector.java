package com.coinbase.android.idology;

import android.support.v7.app.AppCompatActivity;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class IdologyQuestionsLayout_MembersInjector implements MembersInjector<IdologyQuestionsLayout> {
    private final Provider<AppCompatActivity> mAppCompatActivityProvider;
    private final Provider<IdologyQuestionsPresenter> mPresenterProvider;

    public IdologyQuestionsLayout_MembersInjector(Provider<IdologyQuestionsPresenter> mPresenterProvider, Provider<AppCompatActivity> mAppCompatActivityProvider) {
        this.mPresenterProvider = mPresenterProvider;
        this.mAppCompatActivityProvider = mAppCompatActivityProvider;
    }

    public static MembersInjector<IdologyQuestionsLayout> create(Provider<IdologyQuestionsPresenter> mPresenterProvider, Provider<AppCompatActivity> mAppCompatActivityProvider) {
        return new IdologyQuestionsLayout_MembersInjector(mPresenterProvider, mAppCompatActivityProvider);
    }

    public void injectMembers(IdologyQuestionsLayout instance) {
        injectMPresenter(instance, (IdologyQuestionsPresenter) this.mPresenterProvider.get());
        injectMAppCompatActivity(instance, (AppCompatActivity) this.mAppCompatActivityProvider.get());
    }

    public static void injectMPresenter(IdologyQuestionsLayout instance, IdologyQuestionsPresenter mPresenter) {
        instance.mPresenter = mPresenter;
    }

    public static void injectMAppCompatActivity(IdologyQuestionsLayout instance, AppCompatActivity mAppCompatActivity) {
        instance.mAppCompatActivity = mAppCompatActivity;
    }
}
