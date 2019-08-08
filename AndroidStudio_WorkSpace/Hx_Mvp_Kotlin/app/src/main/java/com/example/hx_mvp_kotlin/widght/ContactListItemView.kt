package com.example.hx_mvp_kotlin.widght

import android.util.AttributeSet
import android.widget.RelativeLayout
import android.content.Context
import android.view.View
import com.example.hx_mvp_kotlin.R

/**
 * Create by 心跳 on 2019/8/8 14:17
 * Blog : https://mp.csdn.net/
 * escription :
 */
class ContactListItemView(context: Context?,attrs: AttributeSet?=null) : RelativeLayout(context,attrs) {

    init {
        View.inflate(context, R.layout.view_contact_item,this)
    }
}