package com.example.hx_mvp_kotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.content.Context
import android.view.View
import com.example.hx_mvp_kotlin.data.AddFriendItem
import com.example.hx_mvp_kotlin.widght.AddFriendListItemView

/**
 * Create by 心跳 on 2019/8/9 10:29
 * Blog : https://mp.csdn.net/
 * escription :
 */
class AddFriendListAdapter(val context: Context?, val addFriendItems: MutableList<AddFriendItem>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val addFriendListItemView = p0.itemView as AddFriendListItemView
        addFriendListItemView.bindView(addFriendItems[p1])
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return AddFriendListItemViewHolder(AddFriendListItemView(context))
    }

    override fun getItemCount(): Int=addFriendItems.size

    class AddFriendListItemViewHolder(itemViwe : View) : RecyclerView.ViewHolder(itemViwe){

    }
}