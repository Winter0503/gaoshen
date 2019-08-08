package com.example.hx_mvp_kotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.content.Context
import android.view.View
import com.example.hx_mvp_kotlin.data.ContactListItem
import com.example.hx_mvp_kotlin.ui.activity.ChatActivity
import com.example.hx_mvp_kotlin.widght.ContactListItemView
import org.jetbrains.anko.startActivity

/**
 * Create by 心跳 on 2019/8/8 14:27
 * Blog : https://mp.csdn.net/
 * escription :
 */
class ContactListAdapter(val context: Context, val contactListItems: MutableList<ContactListItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ContactListItemViewHolder(ContactListItemView(context))
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val contactListItemView = p0.itemView as ContactListItemView
        contactListItemView.bindView(contactListItems[p1])
        val userName = contactListItems[p1].userName
        contactListItemView.setOnClickListener{
            context!!.startActivity<ChatActivity>("username" to userName)
        }
    }

    override fun getItemCount(): Int =contactListItems.size

    class ContactListItemViewHolder(itemView :View?) : RecyclerView.ViewHolder(itemView!!){

    }
}