package com.example.hx_mvp_kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.hx_mvp_kotlin.widght.ReceiveMessageItemView
import com.example.hx_mvp_kotlin.widght.SendMessageItemView
import com.hyphenate.chat.EMMessage
import com.hyphenate.util.DateUtils

/**
 * Create by 心跳 on 2019/8/9 21:41
 * Blog : https://mp.csdn.net/
 * escription :
 */
class MessageListAdapter(val context: Context,val messages :List<EMMessage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        val ITEM_TYPE_SEND_MESSAGE=0
        val ITEM_TYPE_RECEIVE_MESSAGE=1
    }

    override fun getItemViewType(position: Int): Int {
        if(messages[position].direct()==EMMessage.Direct.SEND){
            return ITEM_TYPE_SEND_MESSAGE
        }else{
            return ITEM_TYPE_RECEIVE_MESSAGE
        }
    }
    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val showTimestamp = isShowTimestamp(p1)
        if(getItemViewType(p1)== ITEM_TYPE_SEND_MESSAGE){
            val sendMessageItemView = p0.itemView as SendMessageItemView
            sendMessageItemView.bindView(messages[p1],showTimestamp)
        }else{
            val receiveMessageItemView = p0.itemView as ReceiveMessageItemView
            receiveMessageItemView.bindView(messages[p1],showTimestamp)
        }
    }

    private fun isShowTimestamp(position :Int): Boolean {
        var showTimestamp=true
        if(position>0){
            showTimestamp = !DateUtils.isCloseEnough(messages[position].msgTime,messages[position-1].msgTime)
        }
        return showTimestamp
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        if(p1== ITEM_TYPE_SEND_MESSAGE){
            return  SendMessageViewHolder(SendMessageItemView(context))
        }else return ReceiveMessageViewHolder(ReceiveMessageItemView(context))
    }

    override fun getItemCount(): Int =messages.size

    class SendMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
    class ReceiveMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

}