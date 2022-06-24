package com.cc.mvpapplication

import android.app.usage.UsageEvents
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Parcel
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

        mBinding.tvAms.setOnClickListener {
            var intent = Intent();
            intent.component = ComponentName("com.cc.skillapp","com.cc.skillapp.service.ActivityManagerService");
            bindService(intent,conn, BIND_AUTO_CREATE)
        }

        testRxjavaLifecycle()

    }

    var conn = object:ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            Utils.print("CONN")
            var data = Parcel.obtain()
            var reply = Parcel.obtain()
            var result = 0
            data.writeInterfaceToken("cc.skill.IActivityManager")
            intent = Intent()
            intent.setAction("test.demo.action.life.a")
            intent.writeToParcel(data,0)
            p1?.transact(6,data,reply,0)
            result  = reply.readInt()
            Utils.print("输出：$result")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            TODO("Not yet implemented")
        }

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