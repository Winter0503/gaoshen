package com.example.hx_mvp_kotlin.presenter

import com.example.hx_mvp_kotlin.contract.BasePresenter
import com.example.hx_mvp_kotlin.contract.LoginContract
import com.example.hx_mvp_kotlin.extentions.isValidPassWord
import com.example.hx_mvp_kotlin.extentions.isValidUserName

/**
 * Create by 心跳 on 2019/8/6 17:24
 * Blog : https://mp.csdn.net/
 * escription :
 */
class LoginPresenter(val view : LoginContract.View):LoginContract.Presenter{

    override fun login(userName: String, password: String) {
        if(userName.isValidUserName()){
            //用户名合法
            if(password.isValidPassWord()){
                //密码合法
                view.onStartLogin()
                logEaseMob(userName,password)
            }else view.onPasswordError()
        }else view.onUserNameError()
    }

    private fun logEaseMob(userName: String, password: String) {

    }

}