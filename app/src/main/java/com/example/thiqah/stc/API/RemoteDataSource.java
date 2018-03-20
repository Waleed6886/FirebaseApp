package com.example.thiqah.stc.API;


import com.example.thiqah.stc.Model.Currency;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {

    //this is to print the api response as logs
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor);
    //calling the api
    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://api.fixer.io/")
            .client(okBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    Retrofit retrofit = builder.build();

    DataClient dataClient = retrofit.create(DataClient.class);

    io.reactivex.Observable<Currency> currencyObservable = dataClient.getCurrency();

    public Observable<Currency> getDataClient() {
         return currencyObservable;

    }
}
