package com.example.hx_mvp_kotlin.contract

/**
 * Create by 心跳 on 2019/8/9 10:37
 * Blog : https://mp.csdn.net/
 * escription :
 */
interface AddFriendContract {

    interface Presenter : BasePresenter{
        fun search(key: String)
    }

    interface View{
        fun onSearchSuccess()
        fun onSearchFailed()
    }
}