package com.example.hx_mvp_kotlin.presenter

import com.example.hx_mvp_kotlin.adapter.EMCallBackAdapter
import com.example.hx_mvp_kotlin.contract.ChatContract
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import org.jetbrains.anko.doAsync


/**
 * Create by 心跳 on 2019/8/9 21:22
 * Blog : https://mp.csdn.net/
 * escription :
 */
class ChatPresenter(val view:ChatContract.View) : ChatContract.Presenter{

    companion object{
        val PAGE_SIZE=10
    }

    val messages = mutableListOf<EMMessage>()

    override fun sendMessage(contaxt: String?, message: String?) {
        var message = EMMessage.createTxtSendMessage(message!!,contaxt!!)
        message!!.setMessageStatusCallback(object:EMCallBackAdapter(){
            override fun onError(p0: Int, p1: String?) {
               uiThread { view.onSendMessageFailed() }
            }

            override fun onSuccess() {
                uiThread { view.onSendMessageSuccess() }
            }
        })


        EMClient.getInstance().chatManager().sendMessage(message)

        messages.add(message)
        view.onStartSendMessage()
    }

    override fun addMessage(username: String, p0: MutableList<EMMessage>?) {
        //加入当前消息列表
        p0.let { messages.addAll(it!!) }
        //更新消息为已读状态
        //标记会话里面的消息全部为已读
        val conversation = EMClient.getInstance().chatManager().getConversation(username)
        conversation.markAllMessagesAsRead()
    }

    override fun loadMessage(username: String) {
        doAsync {
            val conversation =EMClient.getInstance().chatManager().getConversation(username)

            conversation.markAllMessagesAsRead()

            messages.addAll(conversation.allMessages)
            uiThread { view.onMessageLoaded() }
        }
    }

    override fun loadMoreMessages(username: String) {
        doAsync {
            val conversation = EMClient.getInstance().chatManager().getConversation(username)
            val startMsgId= messages[0].msgId
            val loadMoreMsgFromDB = conversation.loadMoreMsgFromDB(startMsgId, PAGE_SIZE)
            messages.addAll(0,loadMoreMsgFromDB)
            uiThread { view.onMoreMessageLoaded(loadMoreMsgFromDB.size) }
        }
    }
}
