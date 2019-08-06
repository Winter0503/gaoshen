package com.example.hx_mvp_kotlin

import android.widget.Toast
import com.example.hx_mvp_kotlin.contract.LoginContract
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Create by 心跳 on 2019/8/3 10:42
 * Blog : https://mp.csdn.net/
 * escription :
 */
class LoginActivity:BaseActivity(),LoginContract.View{

    override fun getLayoutResId(): Int =R.layout.activity_login

    override fun onUserNameError() {
        userName.error=getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error=getString(R.string.password_error)
    }

    override fun onStartLogin() {
         //弹出一个进度条
        showProgress(getString(R.string.logging))
    }

    override fun onLogedInSuccess() {
        //隐藏进度条
        dismissProgress()

        //进入主界面
        startActivity<MainActivity>()

        //退出LoginActivity
        finish()
    }

    override fun onLogedInFailed() {
        //隐藏进度条
        dismissProgress()

        //提示用户
        toast(R.string.login_failed)
    }
}