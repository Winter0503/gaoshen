package com.example.hx_mvp_kotlin.widght

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.hx_mvp_kotlin.R
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.view_add_friend_item.view.timestamp
import kotlinx.android.synthetic.main.view_send_message_item.view.*
import java.util.*

/**
 * Create by 心跳 on 2019/8/9 21:01
 * Blog : https://mp.csdn.net/
 * escription :
 */
class SendMessageItemView(context: Context?, attrs: AttributeSet?=null) : RelativeLayout(context, attrs) {
    fun bindView(emMessage: EMMessage, showTimestamp: Boolean) {
        updataTimestamp(emMessage,showTimestamp)
        updateMessage(emMessage)
        updateProgress(emMessage)
    }

    private fun updateProgress(emMessage: EMMessage) {
        emMessage.status().let {
            when(it){
                EMMessage.Status.INPROGRESS ->{
                    sendMessageProgress.visibility=View.VISIBLE
                    sendMessageProgress.setImageResource(R.drawable.send_message_progress)
                    val animationDrawable = sendMessageProgress.drawable as AnimationDrawable
                    animationDrawable.start()
                }
                EMMessage.Status.SUCCESS -> sendMessageProgress.visibility=View.GONE
                EMMessage.Status.FAIL -> {
                    sendMessageProgress.visibility=View.VISIBLE
                    sendMessageProgress.setImageResource(R.mipmap.msg_error)
                }
            }
        }
    }

    private fun updateMessage(emMessage: EMMessage) {
        if(emMessage.type==EMMessage.Type.TXT){
            sendMessage.text=(emMessage.body as EMTextMessageBody).message
        }else{
            sendMessage.text=context.getString(R.string.no_text_message)
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

    init {
        View.inflate(context,R.layout.view_send_message_item,this)
    }
}