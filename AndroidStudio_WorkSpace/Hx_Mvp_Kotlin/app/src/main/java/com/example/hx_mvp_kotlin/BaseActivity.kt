package com.example.hx_mvp_kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Create by 心跳 on 2019/8/3 8:36
 * Blog : https://mp.csdn.net/
 * escription :
 */
abstract class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
    }

    abstract fun getLayoutResId() : Int

    open fun init(){
        //初始化一些公共的功能
    }
}