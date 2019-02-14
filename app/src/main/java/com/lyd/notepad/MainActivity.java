package com.lyd.notepad;

import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        SQLDatabase.getInstance(this).getDateDao().getDateDao(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataEntity>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataEntity entity) {
                        Log.e("lyd"," entity1 "+(entity==null));
                        if(entity==null){
                            entity = create();
                            SQLDatabase.getInstance(MainActivity.this).getDateDao().insert(entity);
                        }

                        SQLDatabase.getInstance(MainActivity.this).getDateDao().getDateDao(1)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<DataEntity>() {

                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(DataEntity entity) {
                                        Log.e("lyd"," entity2 "+(entity==null));
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private DataEntity create(){
        DataEntity entity = new DataEntity();
        entity.setCreateTime(System.currentTimeMillis());
        entity.setSid(0);
        entity.setText("初始化数据");
        return entity;
    }
}
