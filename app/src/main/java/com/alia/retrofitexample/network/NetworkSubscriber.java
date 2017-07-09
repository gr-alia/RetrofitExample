package com.alia.retrofitexample.network;

import android.app.Activity;
import android.app.AlertDialog;

import rx.Subscriber;

/**
 * Created by arhis on 07.07.2017.
 */

public abstract class NetworkSubscriber<T> extends Subscriber<T> {

    protected Activity activity;

    public NetworkSubscriber(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        handleError(e);
    }

    private void handleError(Throwable e) {
        if (!activity.isFinishing()) {
            new AlertDialog.Builder(activity)
                    .setTitle("Error")
                    .setMessage(e.getMessage())
                    .show();
        }
    }
}
