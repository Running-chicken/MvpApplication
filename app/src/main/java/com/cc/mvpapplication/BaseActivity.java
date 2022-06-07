package com.cc.mvpapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle3.LifecycleProvider;

public abstract class BaseActivity extends AppCompatActivity {

    protected LifecycleProvider<Lifecycle.Event> mLifecycleProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mLifecycleProvider = AndroidLifecycle.createLifecycleProvider(this);

    }
}
