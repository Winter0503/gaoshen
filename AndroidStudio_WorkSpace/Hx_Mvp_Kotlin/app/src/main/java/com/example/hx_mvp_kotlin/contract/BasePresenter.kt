package com.example.hx_mvp_kotlin.contract

import android.os.Handler
import android.os.Looper

/**
 * Create by 心跳 on 2019/8/3 10:00
 * Blog : https://mp.csdn.net/
 * escription :
 */

interface BasePresenter{

    companion object{
        val handler by lazy {
            Handler(Looper.getMainLooper())
        }
    }
    fun uiThread(f:()->Unit){
        handler.post{ f()}
    }
}