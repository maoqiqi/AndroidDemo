package com.software.march.commonframe.rxjava2;

import android.os.Bundle;

import com.software.march.R;
import com.software.march.appcommonlibrary.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxJava2Activity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_java2;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

            }
        }).subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}