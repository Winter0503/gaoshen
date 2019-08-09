package com.example.hx_mvp_kotlin.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.hx_mvp_kotlin.R
import com.example.hx_mvp_kotlin.adapter.ContactListAdapter
import com.example.hx_mvp_kotlin.adapter.EMContactListenerAdapter
import com.example.hx_mvp_kotlin.contract.ContactContract
import com.example.hx_mvp_kotlin.presenter.ContactPresenter
import com.example.hx_mvp_kotlin.ui.activity.AddFriendActivity
import com.example.hx_mvp_kotlin.widght.SlideBar
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Create by 心跳 on 2019/8/8 7:51
 * Blog : https://mp.csdn.net/
 * escription :
 */
class ContactFragment: BaseFragment(),ContactContract.view{

    override fun getlayoutResId(): Int = R.layout.fragment_contacts

    val presenter = ContactPresenter(this)
    val contactListener =object :EMContactListenerAdapter() {
        override fun onFriendRequestDeclined(p0: String?) {
            presenter.loadContact()
        }
        override fun onContactDeleted(p0: String?) {
            presenter.loadContact()
        }
        override fun onContactAdded(p0: String?) {
            presenter.loadContact()
        }

        override fun onContactInvited(p0: String?, p1: String?) {
            presenter.loadContact()
        }

        override fun onFriendRequestAccepted(p0: String?) {
            presenter.loadContact()
        }
    }

    override fun init() {
        super.init()
        initHeader()
        initswipeRefreshLayout()
        initRecyclerView()
        EMClient.getInstance().contactManager().setContactListener(contactListener)
        initSlideBar()
        presenter.loadContact()
    }

    private fun initSlideBar() {
        slideBar.onSectionChange = object : SlideBar.OnSectionChangeListener{
            override fun onSectionChange(firstLetter: String) {
                section.visibility=View.VISIBLE
                section.text=firstLetter
                recyclerView.smoothScrollToPosition(getPosition(firstLetter))
            }

            override fun onSlideFinish() {
                section.visibility=View.GONE
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView.apply{
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(context)
            adapter= ContactListAdapter(context,presenter.contactListItems)
        }
    }

    private fun initswipeRefreshLayout() {
        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing=true
            setOnRefreshListener { presenter.loadContact() }
        }
    }

    private fun initHeader() {
        headerTitle.text=getString(R.string.contact)
        add.visibility= View.VISIBLE
        add.setOnClickListener{context!!.startActivity<AddFriendActivity>() }
    }

    private fun getPosition(firstLetter: String): Int=
        presenter.contactListItems.binarySearch {
            contactListItem -> contactListItem.firstLetter.minus(firstLetter[0])
        }


    override fun onLoadContactsSuccess() {
        swipeRefreshLayout.isRefreshing=false
        recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onLoadContactsFiled() {
        swipeRefreshLayout.isRefreshing=false
        context!!.toast(R.string.load_contacts_failed)
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().contactManager().removeContactListener(contactListener)
    }
}
