package com.example.hx_mvp_kotlin.widght

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.hx_mvp_kotlin.R
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.view_conversation_item.view.*
import java.util.*

/**
 * Create by 心跳 on 2019/8/10 10:34
 * Blog : https://mp.csdn.net/
 * escription :
 */
class ConversitionListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {
    fun binView(emConversation: EMConversation) {
        userName.text=emConversation.conversationId()
        if(emConversation.lastMessage.type==EMMessage.Type.TXT){
            val body =emConversation.lastMessage.body as EMTextMessageBody
            lastMessage.text=body.message
        }else lastMessage.text=context.getString(R.string.no_text_message)

        val timestampString = DateUtils.getTimestampString(Date(emConversation.lastMessage.msgTime))
        timestamp.text=timestampString

        if(emConversation.unreadMsgCount >0){
            unreadCount.visibility=View.VISIBLE
            unreadCount.text=emConversation.unreadMsgCount.toString()
        }else{
            unreadCount.visibility=View.GONE
        }
    }

    init {
        View.inflate(context, R.layout.view_conversation_item,this)
    }
}