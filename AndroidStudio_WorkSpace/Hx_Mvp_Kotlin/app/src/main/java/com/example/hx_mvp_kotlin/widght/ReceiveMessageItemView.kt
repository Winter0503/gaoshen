package com.example.hx_mvp_kotlin.widght

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.hx_mvp_kotlin.R
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.view_receive_message_item.view.*
import java.util.*

/**
 * Create by 心跳 on 2019/8/9 21:12
 * Blog : https://mp.csdn.net/
 * escription :
 */
class ReceiveMessageItemView(context: Context?, attrs: AttributeSet?=null) : RelativeLayout(context, attrs) {
    fun bindView(emMessage: EMMessage, showTimestamp: Boolean) {
        updataTimestamp(emMessage,showTimestamp)
        updateMessage(emMessage)
    }

    init{
        View.inflate(context, R.layout.view_receive_message_item,this)
    }

    private fun updateMessage(emMessage: EMMessage) {
        if(emMessage.type==EMMessage.Type.TXT){
            receiveMessage.text=(emMessage.body as EMTextMessageBody).message
        }else{
            receiveMessage.text=context.getString(R.string.no_text_message)
        }
    }

    private fun updataTimestamp(emMessage: EMMessage, showTimestamp: Boolean) {
        if(showTimestamp){
            timestamp.visibility=View.VISIBLE
            timestamp.text=DateUtils.getTimestampString(Date(emMessage.msgTime))
        }else{
            timestamp.visibility=View.GONE
        }
    }
}