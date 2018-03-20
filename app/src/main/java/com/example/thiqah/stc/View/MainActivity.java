package com.example.thiqah.stc.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.thiqah.stc.API.RemoteDataSource;
import com.example.thiqah.stc.Model.Currency;
import com.example.thiqah.stc.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private DocumentReference documentReference = FirebaseFirestore.getInstance().document("STC/sampleData");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        initializingData();

    }

    private void initializingData() {
        RemoteDataSource remoteDataSource = new RemoteDataSource();
        io.reactivex.Observable<Currency> dataClient = remoteDataSource.getDataClient();
        dataClient.subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<Currency>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Currency currency) {
                        documentReference.set(currency);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //here we can handle the error
                        Log.w("error",e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
