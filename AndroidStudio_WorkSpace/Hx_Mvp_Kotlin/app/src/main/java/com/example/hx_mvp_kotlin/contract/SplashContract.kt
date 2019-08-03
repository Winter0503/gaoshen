package com.example.hx_mvp_kotlin.contract

/**
 * Create by 心跳 on 2019/8/3 10:02
 * Blog : https://mp.csdn.net/
 * escription :
 */
interface  SplashContract{

    interface Presenter :BasePresenter{

    }

    interface View{
        fun onNotLoggedIn() //没有登录的UI处理
        fun onLoggedIn() //已经登录的UI处理
    }
}