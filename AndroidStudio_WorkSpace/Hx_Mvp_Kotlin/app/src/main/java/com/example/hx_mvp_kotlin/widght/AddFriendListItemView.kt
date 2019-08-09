package com.example.hx_mvp_kotlin.widght

import android.util.AttributeSet
import android.widget.RelativeLayout
import android.content.Context
import android.view.View
import com.example.hx_mvp_kotlin.R
import com.example.hx_mvp_kotlin.data.AddFriendItem
import kotlinx.android.synthetic.main.view_add_friend_item.view.*

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
    }

}