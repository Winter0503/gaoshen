package com.example.hx_mvp_kotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.content.Context
import android.view.View
import com.example.hx_mvp_kotlin.widght.ContactListItemView

/**
 * Create by 心跳 on 2019/8/8 14:27
 * Blog : https://mp.csdn.net/
 * escription :
 */
class ContactListAdapter(val context : Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ContactListItemViewHolder(ContactListItemView(context))
    }

    override fun getItemCount(): Int =30

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {

    }

    class ContactListItemViewHolder(itemView :View?) : RecyclerView.ViewHolder(itemView!!){

    }
}