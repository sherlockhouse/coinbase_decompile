package com.coinbase;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public interface CallbackWithRetrofit<T> {
    void onFailure(Call<T> call, Throwable th);

    void onResponse(Call<T> call, Response<T> response, Retrofit retrofit);
}
