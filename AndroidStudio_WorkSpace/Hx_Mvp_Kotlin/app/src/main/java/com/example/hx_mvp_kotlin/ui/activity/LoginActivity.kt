package com.example.hx_mvp_kotlin.ui.activity

import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import com.example.hx_mvp_kotlin.R
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
class LoginActivity: BaseActivity(),LoginContract.View{

    val persenter =LoginPresenter(this)

    //设置布局
    override fun getLayoutResId(): Int = R.layout.activity_login

    //初始化
    override fun init() {
        super.init()

        //登录按钮点击事件
        login.setOnClickListener { login() }

        //密码输入框事件
        password.setOnEditorActionListener{ p0,p1,p2->
            login()
            true
        }

        newUser.setOnClickListener{ startActivity<RegisterActivity>() }

    }

    //登录
    fun login(){
        //隐藏软键盘
        hideSoftKeyboar()

        //检查是否有读写Sdcard 的权限
        if(checkPermission()){
            val name = userName.text.trim().toString()
            val pwd =password.text.trim().toString()
            persenter.login(name,pwd)
        }else  registePermission()

    }

    //申请权限
    private fun registePermission() {
        val premissions = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this,premissions,0)
    }

    //检查权限
    private fun checkPermission(): Boolean{
        val result = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result==PackageManager.PERMISSION_GRANTED
    }

    //用户名校验失败回调的方法
    override fun onUserNameError() {
        userName.error=getString(R.string.user_name_error)
    }

    //密码校验失败的方法
    override fun onPasswordError() {
        password.error=getString(R.string.password_error)
    }

    //正在登录接口回调的方法
    override fun onStartLogin() {
         //弹出一个进度条
        showProgress(getString(R.string.logging))
    }

    //登录成功接口回调的方法
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

    //登录失败接口回调的方法
    override fun onLogedInFailed() {
        //隐藏进度条
        dismissProgress()

        //提示用户
        toast(R.string.login_failed)
    }

    //用户选择是否开启权限的回调方法
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            //用户开启了需要的权限，开始登录验证
            login()
        }else toast(R.string.permission_denied)
    }
}