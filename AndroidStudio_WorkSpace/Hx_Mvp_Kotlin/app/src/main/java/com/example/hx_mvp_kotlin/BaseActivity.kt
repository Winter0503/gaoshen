package com.example.hx_mvp_kotlin

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Create by 心跳 on 2019/8/3 8:36
 * Blog : https://mp.csdn.net/
 * escription :
 */
abstract class BaseActivity : AppCompatActivity(){

    val progressDialog by lazy{
        ProgressDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        init()
    }

    abstract fun getLayoutResId() : Int

    open fun init(){
        //初始化一些公共的功能
    }

    fun showProgress(message : String){
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    fun dismissProgress(){
        progressDialog.dismiss()
    }
}