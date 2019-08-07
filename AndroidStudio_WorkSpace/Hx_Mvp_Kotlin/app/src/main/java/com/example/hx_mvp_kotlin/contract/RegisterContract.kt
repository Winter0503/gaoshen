package com.example.hx_mvp_kotlin.contract

/**
 * Create by 心跳 on 2019/8/7 15:07
 * Blog : https://mp.csdn.net/
 * escription :
 */
interface RegisterContract {

    interface Presenter :BasePresenter{
        fun register(userName: String,password: String , confirmpassword: String)
    }

    interface View {
        fun onUserNameError()
        fun onPasswordError()
        fun onConfirmPasswordError()
        fun onStartRegister()
        fun onRegisterSuccess()
        fun onRegisterFailed()
        fun onUserExist()
    }
}
