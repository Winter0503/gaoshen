package com.example.hx_mvp_kotlin.presenter

import com.example.hx_mvp_kotlin.contract.LoginContract
import com.example.hx_mvp_kotlin.extentions.isValidPassWord
import com.example.hx_mvp_kotlin.extentions.isValidUserName
import com.hyphenate.chat.EMClient
import com.example.hx_mvp_kotlin.adapter.EMCallBackAdapter


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
        EMClient.getInstance().login(userName, password, object : EMCallBackAdapter() {
            override fun onSuccess() {
                super.onSuccess()
                EMClient.getInstance().groupManager().loadAllGroups()
                EMClient.getInstance().chatManager().loadAllConversations()

                uiThread { view.onLogedInSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                super.onError(p0, p1)
                uiThread { view.onLogedInFailed() }
            }
        })
    }

}