package com.example.hx_mvp_kotlin.ui.fragment

import com.example.hx_mvp_kotlin.R
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*
import com.hyphenate.EMCallBack
import android.content.Context
import com.example.hx_mvp_kotlin.adapter.EMCallBackAdapter
import com.example.hx_mvp_kotlin.ui.activity.LoginActivity
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Create by 心跳 on 2019/8/8 7:51
 * Blog : https://mp.csdn.net/
 * escription :
 */
class DynamicFragment: BaseFragment(){

    override fun getlayoutResId(): Int = com.example.hx_mvp_kotlin.R.layout.fragment_dynamic

    override fun init() {
        super.init()
        headerTitle.text=getString(com.example.hx_mvp_kotlin.R.string.dynamic)

        val userName=String.format((getString(com.example.hx_mvp_kotlin.R.string.logout)), EMClient.getInstance().currentUser)
        logout.text=userName

        logout.setOnClickListener { logout() }
    }

    fun logout(){
        EMClient.getInstance().logout(true, object : EMCallBackAdapter() {

            override fun onSuccess() {
                context!!.runOnUiThread {
                    toast(R.string.logout_success)
                    context!!.startActivity<LoginActivity>()
                    activity!!.finish()
                }

            }

            override fun onError(p0: Int, p1: String?) {
                super.onError(p0, p1)
                context!!.runOnUiThread { toast(R.string.logout_failed)  }
            }
        })
     }
}
