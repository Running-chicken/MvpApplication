package com.cc.mvpapplication.presenter

import com.cc.mvpapplication.model.HanHan
import com.cc.mvpapplication.model.ToolPeople
import com.cc.mvpapplication.util.Utils
import com.cc.mvpapplication.view.Me

/**
 *  Presenter构造方法中要有View,并将自己传给Model
 */
class WangDaMa : MiddlePeople {

    var me: Me? = null
    var toolPeople: ToolPeople

    constructor(me:Me){
        this.me = me
        toolPeople = HanHan(this)
    }

    override fun buyCola() {
        Utils.print("dama: 我就是买可乐")
        toolPeople.produceCola()
    }

    override fun buyFenDa() {
        Utils.print("dama: 我也可买芬达")
        toolPeople.produceFenDa()
    }


    override fun buySucceed() {
        Utils.print("dama: 买到东西了")
        me?.drinkCola()
    }
}