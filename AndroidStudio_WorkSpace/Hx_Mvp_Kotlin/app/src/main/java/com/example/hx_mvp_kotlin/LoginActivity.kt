package com.example.hx_mvp_kotlin

import android.widget.Toast
import com.example.hx_mvp_kotlin.contract.LoginContract
import com.example.hx_mvp_kotlin.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Create by 心跳 on 2019/8/3 10:42
 * Blog : https://mp.csdn.net/
 * escription :
 */
class LoginActivity:BaseActivity(),LoginContract.View{

    val persenter =LoginPresenter(this)
    override fun init() {
        super.init()
        login.setOnClickListener { login() }
        password.setOnEditorActionListener{ p0,p1,p2->
            login()
            true
        }
    }

    fun login(){
        //隐藏软键盘
        hideSoftKeyboar()
        val name = userName.text.trim().toString()
        val pwd =password.text.trim().toString()
        persenter.login(name,pwd)
    }
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

        //提示用户
        toast(R.string.login_success)
    }

    override fun onLogedInFailed() {
        //隐藏进度条
        dismissProgress()

        //提示用户
        toast(R.string.login_failed)
    }
}