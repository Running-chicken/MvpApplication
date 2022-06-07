package com.cc.mvpapplication

import android.app.usage.UsageEvents
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import com.cc.mvpapplication.databinding.ActivityMainBinding
import com.cc.mvpapplication.presenter.PresenterImpl
import com.cc.mvpapplication.presenter.WangDaMa
import com.cc.mvpapplication.util.Utils
import com.cc.mvpapplication.view.Me
import com.cc.mvpapplication.view.ViewInterface
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

/**
 * Activity实现View的方法
 * View要将自己传给Presenter
 */
class MainActivity : BaseActivity() , Me,ViewInterface {

    var wangDaMa:WangDaMa?=null

    var presenterImpl:PresenterImpl?=null

    lateinit var mBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)


        wangDaMa = WangDaMa(this)
        presenterImpl = PresenterImpl(this)

        findViewById<TextView>(R.id.tv_drink_cola).setOnClickListener {
            wangDaMa?.buyCola()
        }

        findViewById<TextView>(R.id.tv_drink_fen).setOnClickListener {
            wangDaMa?.buyFenDa()
        }

        mBinding.tvCcView.setOnClickListener {
            presenterImpl?.tellModelToCreate("cityCode")
        }


        testRxjavaLifecycle()

    }

    private fun testRxjavaLifecycle(){
        Observable.interval(0,1,TimeUnit.SECONDS)
            .doOnDispose(Action {
                Utils.print("this is dispose")
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(mLifecycleProvider.bindUntilEvent(Lifecycle.Event.ON_STOP))
            .subscribe {
                Utils.print("输出：$it")
            }


    }

    override fun drinkCola() {
        Utils.print("me: 这个可乐应该是真的")
    }

    override fun drinkFenDa() {
        Utils.print("me: 这个还可以应该是真的")
    }

    override fun updateData(data:String) {
        mBinding.tvCcView.text = data
    }
}