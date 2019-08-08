package com.example.hx_mvp_kotlin.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.hx_mvp_kotlin.R
import com.example.hx_mvp_kotlin.adapter.ContactListAdapter
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*

/**
 * Create by 心跳 on 2019/8/8 7:51
 * Blog : https://mp.csdn.net/
 * escription :
 */
class ContactFragment: BaseFragment(){
    override fun getlayoutResId(): Int = R.layout.fragment_contacts

    override fun init() {
        super.init()
        headerTitle.text=getString(R.string.contact)
        add.visibility= View.VISIBLE

        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing=true
        }

        recyclerView.apply{
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(context)
            adapter= ContactListAdapter(context)
        }
    }

    


}