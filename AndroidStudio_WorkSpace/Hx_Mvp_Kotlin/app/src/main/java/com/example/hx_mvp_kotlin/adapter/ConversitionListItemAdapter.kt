package com.example.hx_mvp_kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.hx_mvp_kotlin.ui.activity.ChatActivity
import com.example.hx_mvp_kotlin.widght.ConversitionListItemView
import com.hyphenate.chat.EMConversation
import org.jetbrains.anko.startActivity

/**
 * Create by 心跳 on 2019/8/10 10:38
 * Blog : https://mp.csdn.net/
 * escription :
 */
class ConversitionListItemAdapter(val context: Context, val conversations: MutableList<EMConversation>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val conversitionListItemView = p0.itemView as ConversitionListItemView
        conversitionListItemView.binView(conversations[p1])

        conversitionListItemView.setOnClickListener{
            context.startActivity<ChatActivity>(
                "username" to conversations[p1].conversationId()
            )
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ConversationlistItemViewHolder(ConversitionListItemView(context))
    }

    override fun getItemCount(): Int =conversations.size
    class ConversationlistItemViewHolder(val itemView : View): RecyclerView.ViewHolder(itemView) {

    }

}