package com.cc.mvpapplication.model

import android.util.Log
import com.cc.mvpapplication.presenter.MiddlePeople

/**
 * Model构造方法中要有Presenter
 */
class HanHan : ToolPeople {

    var middlePeople: MiddlePeople? = null;

    //和中间人取得联系 才能通知客户
    constructor(middlePeople: MiddlePeople){
        this.middlePeople = middlePeople
    }


    //生产可乐
    override fun produceCola() {
        Log.i("mvpDemo","hanhan:i produceCola")
        //通知客户
        middlePeople?.buySucceed()
    }

    override fun produceFenDa() {
        Log.i("mvpDemo","hanhan:i also produce FenDa")
        middlePeople?.buySucceed()
    }


}