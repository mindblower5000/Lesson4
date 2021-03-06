package com.example.rumpilstilstkin.lesson4.presenters.home;


import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.rumpilstilstkin.lesson4.data.models.RepsModel;
import com.example.rumpilstilstkin.lesson4.data.rest.NetApiClient;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;


@InjectViewState
public class RepsPresenter  extends MvpPresenter<RepsView> implements Subscriber<List<RepsModel>> {

    @Override
    public void attachView(RepsView view) {
        super.attachView(view);
        getViewState().startLoad();
//        loadData();
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(List<RepsModel> data) {
        Log.d("Dto", "size = " + data.size());
    }

    @Override
    public void onComplete() {
        getViewState().finishLoad();
    }

    @Override
    public void onError(Throwable t) {
        getViewState().showError(t);
        getViewState().finishLoad();
    }

    private void loadData(){
        getViewState().startLoad();

        NetApiClient.getInstance().getReps().subscribe(this);
    }
}
