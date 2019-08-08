package com.example.hx_mvp_kotlin.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.hx_mvp_kotlin.R
import com.example.hx_mvp_kotlin.adapter.ContactListAdapter
import com.example.hx_mvp_kotlin.contract.ContactContract
import com.example.hx_mvp_kotlin.presenter.ContactPresenter
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 * Create by 心跳 on 2019/8/8 7:51
 * Blog : https://mp.csdn.net/
 * escription :
 */
class ContactFragment: BaseFragment(),ContactContract.view{
    override fun getlayoutResId(): Int = R.layout.fragment_contacts

    val presenter = ContactPresenter(this)
    override fun init() {
        super.init()
        headerTitle.text=getString(R.string.contact)
        add.visibility= View.VISIBLE

        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing=true
            setOnRefreshListener { presenter.loadContact() }
        }

        recyclerView.apply{
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(context)
            adapter= ContactListAdapter(context,presenter.contactListItems)
        }

        presenter.loadContact()
    }

    override fun onLoadContactsSuccess() {
        swipeRefreshLayout.isRefreshing=false
        recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onLoadContactsFiled() {
        swipeRefreshLayout.isRefreshing=false
        context!!.toast(R.string.load_contacts_failed)
    }




}
