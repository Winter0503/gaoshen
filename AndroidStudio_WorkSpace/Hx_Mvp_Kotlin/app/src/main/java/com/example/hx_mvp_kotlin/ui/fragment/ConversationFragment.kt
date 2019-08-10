package com.example.hx_mvp_kotlin.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.example.hx_mvp_kotlin.R
import com.example.hx_mvp_kotlin.adapter.ConversitionListItemAdapter
import com.example.hx_mvp_kotlin.adapter.EMMessageListenerAdapter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.fragment_conversation.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Create by 心跳 on 2019/8/8 7:51
 * Blog : https://mp.csdn.net/
 * escription :
 */
class ConversationFragment: BaseFragment(){

    val conversations = mutableListOf<EMConversation>()

    override fun getlayoutResId(): Int = R.layout.fragment_conversation

    val messageListener=object : EMMessageListenerAdapter(){
        override fun onCmdMessageReceived(p0: MutableList<EMMessage>?) {
            loadConversations()
        }
    }

    override fun init() {
        super.init()
        headerTitle.text=getString(R.string.message)

        recyclerView.apply{
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter= ConversitionListItemAdapter(context,conversations)
        }
        
//        loadConversations()

        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }

    private fun loadConversations() {
        doAsync {
            conversations.clear()
            val allConversations = EMClient.getInstance().chatManager().allConversations
            conversations.addAll(allConversations.values)
            uiThread { recyclerView.adapter!!.notifyDataSetChanged() }
        }
    }

    override fun onResume() {
        super.onResume()
        loadConversations()
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
}