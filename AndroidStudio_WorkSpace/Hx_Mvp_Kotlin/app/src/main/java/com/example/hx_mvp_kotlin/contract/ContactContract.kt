package com.example.hx_mvp_kotlin.contract

/**
 * Create by 心跳 on 2019/8/8 15:17
 * Blog : https://mp.csdn.net/
 * escription :
 */
interface ContactContract{

    interface presenter:BasePresenter{
        fun loadContact()
    }
    interface view{
        fun onLoadContactsSuccess()
        fun onLoadContactsFiled()
    }
}