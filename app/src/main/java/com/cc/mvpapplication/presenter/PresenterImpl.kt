package com.cc.mvpapplication.presenter

import com.cc.mvpapplication.model.Modelmpl
import com.cc.mvpapplication.util.Utils
import com.cc.mvpapplication.view.ViewInterface

class PresenterImpl : PresenterInterface {

    var modelImpl:Modelmpl?=null
    var viewInterface:ViewInterface?=null

    constructor(viewInterface: ViewInterface){
        modelImpl = Modelmpl(this)
        this.viewInterface = viewInterface;
    }

    override fun tellModelToCreate(params:String) {
        Utils.print("我告诉model去造数据")
        modelImpl?.makeData(params)
    }

    override fun tellViewToSuccess(data:String) {
        Utils.print("获取到数据了,数据时$data")
        viewInterface?.updateData(data)
    }
}