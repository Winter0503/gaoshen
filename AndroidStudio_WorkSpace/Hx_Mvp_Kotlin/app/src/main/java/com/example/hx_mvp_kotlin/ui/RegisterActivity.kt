package com.example.hx_mvp_kotlin.ui

import com.example.hx_mvp_kotlin.R
import com.example.hx_mvp_kotlin.contract.RegisterContract
import com.example.hx_mvp_kotlin.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 * Create by 心跳 on 2019/8/7 14:44
 * Blog : https://mp.csdn.net/
 * escription :
 */
class RegisterActivity : BaseActivity(),RegisterContract.View{

    override fun getLayoutResId(): Int = R.layout.activity_register

    val presenter = RegisterPresenter(this)

    override fun init() {
        super.init()

        register.setOnClickListener { register() }

        confirmPassword.setOnEditorActionListener{ p0,p1,p2->
            register()
            true
        }

    }

    fun register() {
        val name = userName.text.trim().toString()
        val pwd =password.text.trim().toString()
        val conpwd=confirmPassword.text.trim().toString()
        presenter.register(name,pwd,conpwd)
    }

    override fun onUserNameError() {
        userName.error=getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error=getString(R.string.password_error)
    }

    override fun onConfirmPasswordError() {
        confirmPassword.error=getString(R.string.confirm_password_error)
    }

    override fun onStartRegister() {
        showProgress(getString(R.string.registering))
    }

    override fun onRegisterSuccess() {
        toast(R.string.register_success)
        dismissProgress()
        finish()
    }

    override fun onRegisterFailed() {
        dismissProgress()
        toast(R.string.register_failed)
    }

    override fun onUserExist() {
        toast(R.string.user_already_exist)
    }


}