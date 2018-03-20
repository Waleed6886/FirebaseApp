package com.example.thiqah.stc.API;

import com.example.thiqah.stc.Model.Currency;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface DataClient {

    @GET("/latest")
    Observable<Currency> getCurrency();

}
