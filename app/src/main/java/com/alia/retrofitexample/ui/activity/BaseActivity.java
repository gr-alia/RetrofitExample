package com.alia.retrofitexample.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alia.retrofitexample.network.NetworkSubscriber;

import java.util.HashSet;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by arhis on 07.07.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayoutId();

    protected HashSet<Subscription> subscriptions = new HashSet<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    @Override
    protected void onDestroy() {
        for(Subscription subscription: subscriptions) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    protected <T> Subscription subscribe(Observable<T> observable, Action1<T> onSuccess) {
        NetworkSubscriber<T> subscriber = new NetworkSubscriber<T>(this) {
            @Override
            public void onNext(T t) {
                onSuccess.call(t);
            }
        };

        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        subscriptions.add(subscription);
        return subscriber;
    }
}
