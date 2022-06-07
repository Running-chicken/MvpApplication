package com.cc.mvpapplication.view

import com.cc.mvpapplication.util.Utils

class ViewImpl:ViewInterface {
    override fun updateData(data:String) {
        Utils.print("更新数据$data")
    }
}