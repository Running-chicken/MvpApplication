package com.cc.mvpapplication.model

import android.os.SystemClock
import com.cc.mvpapplication.presenter.PresenterInterface
import com.cc.mvpapplication.util.Utils

class Modelmpl : ModelInterface {

    var presenterInterface:PresenterInterface?=null

    constructor(presenterInterface: PresenterInterface){
        this.presenterInterface = presenterInterface
    }

    override fun makeData(params:String) {
        Utils.print("参数是：$params")
        SystemClock.sleep(3000);
        Utils.print("建好了")
        presenterInterface?.tellViewToSuccess("300")
    }
}