package com.example.hx_mvp_kotlin.presenter

import com.example.hx_mvp_kotlin.contract.SplashContract

/**
 * Create by 心跳 on 2019/8/3 19:58
 * Blog : https://mp.csdn.net/
 * escription :
 */
class SplashPresenter(val view: SplashContract.View) : SplashContract.Presenter{
    override fun checkLoginStatus() {
        if(isLoggedIn()) view.onLoggedIn() else view.onNotLoggedIn()
    }

    private fun isLoggedIn(): Boolean =false

}