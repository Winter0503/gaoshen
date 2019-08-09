package com.example.hx_mvp_kotlin.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.example.hx_mvp_kotlin.R
import com.example.hx_mvp_kotlin.adapter.AddFriendListAdapter
import com.example.hx_mvp_kotlin.contract.AddFriendContract
import com.example.hx_mvp_kotlin.presenter.AddFriendPresenter
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.activity_chat.recyclerView
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 * Create by 心跳 on 2019/8/9 10:07
 * Blog : https://mp.csdn.net/
 * escription :
 */
class AddFriendActivity : BaseActivity(),AddFriendContract.View{
    override fun getLayoutResId(): Int = R.layout.activity_add_friend

    val presenter= AddFriendPresenter(this)
    override fun init() {
        super.init()
        headerTitle.text=getString(R.string.add_friend)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(context)
            adapter= AddFriendListAdapter(context,presenter.addFriendItems)
        }

        search.setOnClickListener { search() }
        userName.setOnEditorActionListener { textView, i, keyEvent ->
            search()
            true
        }
    }

    fun search(){
        hideSoftKeyboar()
        showProgress(getString(R.string.searching))
        val key=userName.text.trim().toString()
        presenter.search(key)
    }

    override fun onSearchSuccess() {
        dismissProgress()
        toast(R.string.search_success)
        recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onSearchFailed() {
        dismissProgress()
        toast(R.string.search_failed)
    }
}
