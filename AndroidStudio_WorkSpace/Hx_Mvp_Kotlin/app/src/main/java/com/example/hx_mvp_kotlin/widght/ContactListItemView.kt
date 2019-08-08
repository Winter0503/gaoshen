package com.example.hx_mvp_kotlin.widght

import android.util.AttributeSet
import android.widget.RelativeLayout
import android.content.Context
import android.view.View
import com.example.hx_mvp_kotlin.R
import com.example.hx_mvp_kotlin.data.ContactListItem
import kotlinx.android.synthetic.main.view_contact_item.view.*

/**
 * Create by 心跳 on 2019/8/8 14:17
 * Blog : https://mp.csdn.net/
 * escription :
 */
class ContactListItemView(context: Context?,attrs: AttributeSet?=null) : RelativeLayout(context,attrs) {

    fun bindView(contactListItem: ContactListItem) {
        if(contactListItem.showFirstLetter){
            firstLetter.visibility=View.VISIBLE
            firstLetter.text=contactListItem.firstLetter.toString()
        }else  firstLetter.visibility=View.GONE
        userName.text=contactListItem.userName
    }

    init {
        View.inflate(context, R.layout.view_contact_item,this)
    }

}