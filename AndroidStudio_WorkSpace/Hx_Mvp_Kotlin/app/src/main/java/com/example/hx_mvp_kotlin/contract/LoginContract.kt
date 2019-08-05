package com.example.hx_mvp_kotlin.contract

/**
 * Create by 心跳 on 2019/8/5 9:43
 * Blog : https://mp.csdn.net/
 * escription :
 */
interface LoginContract{

    interface Presenter: BasePresenter{
        fun login(userName : String , password : String)
    }

    interface View{
        fun onUserNameError()
        fun onPasswordError()
        fun onStartLogin()
        fun onLogedInSuccess()
        fun onLogedInFailed()
    }

}
