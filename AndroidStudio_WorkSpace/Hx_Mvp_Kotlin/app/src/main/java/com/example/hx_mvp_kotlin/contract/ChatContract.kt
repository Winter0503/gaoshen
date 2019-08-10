package com.example.hx_mvp_kotlin.contract

import com.hyphenate.chat.EMMessage

/**
 * Create by 心跳 on 2019/8/9 21:14
 * Blog : https://mp.csdn.net/
 * escription :
 */
interface ChatContract {

    interface Presenter :BasePresenter{
        fun sendMessage(contaxt: String,message:String)
        fun addMessage(username: String, p0: MutableList<EMMessage>?)
        fun loadMessage(username: String)
        abstract fun loadMoreMessages(username: String)
    }

    interface View{
        fun onStartSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
        fun onMessageLoaded()
        fun onMoreMessageLoaded(size: Int)
    }
}