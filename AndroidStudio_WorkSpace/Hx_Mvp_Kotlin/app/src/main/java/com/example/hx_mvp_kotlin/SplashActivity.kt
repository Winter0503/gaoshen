package com.example.hx_mvp_kotlin

import android.os.Handler
import com.example.hx_mvp_kotlin.contract.SplashContract
import com.example.hx_mvp_kotlin.presenter.SplashPresenter
import org.jetbrains.anko.startActivity

/**
 * Create by 心跳 on 2019/8/3 9:08
 * Blog : https://mp.csdn.net/
 * escription :
 */
class SplashActivity : BaseActivity(),SplashContract.View{

    val presenter = SplashPresenter(this)
    companion object{
        val DELAY=2000L
    }

    val handler by lazy{
        Handler()
    }

    override fun init() {
        super.init()
        presenter.checkLoginStatus()
    }

    override fun getLayoutResId(): Int =R.layout.activity_splash

    //延时2秒，跳转页面
    override fun onNotLoggedIn() {
        handler.postDelayed({
            startActivity<LoginActivity>()
            finish()
        }, DELAY);
    }

    override fun onLoggedIn() {
        startActivity<MainActivity>()
        finish()
    }


}