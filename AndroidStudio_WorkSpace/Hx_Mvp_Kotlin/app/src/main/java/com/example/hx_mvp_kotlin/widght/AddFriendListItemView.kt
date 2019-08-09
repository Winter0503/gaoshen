package com.example.hx_mvp_kotlin.widght

import android.util.AttributeSet
import android.widget.RelativeLayout
import android.content.Context
import android.view.View
import com.example.hx_mvp_kotlin.R
import com.example.hx_mvp_kotlin.adapter.EMCallBackAdapter
import com.example.hx_mvp_kotlin.data.AddFriendItem
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.view_add_friend_item.view.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

/**
 * Create by 心跳 on 2019/8/9 10:23
 * Blog : https://mp.csdn.net/
 * escription :
 */
class AddFriendListItemView(context : Context?, attrs :AttributeSet?=null) :RelativeLayout(context,attrs){
    init {
        View.inflate(context, R.layout.view_add_friend_item,this)
    }

    fun bindView(addFriendItem: AddFriendItem) {
        if(addFriendItem.isAdded){
            add.isEnabled = false
            add.text=context.getString(R.string.already_added)
        }else{
            add.isEnabled = true
            add.text=context.getString(R.string.add)
        }
        userName.text=addFriendItem.userName
        timestamp.text=addFriendItem.timestamp


        add.setOnClickListener { addFriend(addFriendItem.userName) }
    }

    private fun addFriend(userName: String) {
        EMClient.getInstance().contactManager().aysncAddContact(userName,"交个朋友吧",object : EMCallBackAdapter(){
            override fun onSuccess() {
                super.onSuccess()
                context.runOnUiThread { toast(R.string.send_add_friend_success) }
            }

            override fun onError(p0: Int, p1: String?) {
                super.onError(p0, p1)
                context.runOnUiThread { toast(R.string.send_add_friend_failed) }
            }
        })

    }

}