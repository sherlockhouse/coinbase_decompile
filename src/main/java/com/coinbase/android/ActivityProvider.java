package com.coinbase.android;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Window;

public interface ActivityProvider {
    AppCompatActivity getActivity();

    LayoutInflater getLayoutInflater();

    Window getWindow();
}
