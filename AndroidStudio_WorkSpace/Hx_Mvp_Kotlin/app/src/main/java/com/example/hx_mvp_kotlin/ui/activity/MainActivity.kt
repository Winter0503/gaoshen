package com.example.hx_mvp_kotlin.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.hx_mvp_kotlin.R
import com.example.hx_mvp_kotlin.adapter.EMMessageListenerAdapter
import com.example.hx_mvp_kotlin.factory.FragmentFactory
import com.hyphenate.EMConnectionListener
import com.hyphenate.EMError
import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() {

    val messageListener=object : EMMessageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            runOnUiThread {
                val tab = bottomBar.getTabWithId(R.id.tab_conversation)
                tab.setBadgeCount(EMClient.getInstance().chatManager().unreadMessageCount)
            }
        }
    }

    override fun getLayoutResId(): Int =R.layout.activity_main

    override fun init() {
        super.init()
        bottomBar.setOnTabSelectListener{ tabId ->
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(R.id.fragment_frame,FragmentFactory.instance.getFragment(tabId)!!)
            beginTransaction.commit()
        }
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
        EMClient.getInstance().addConnectionListener(object: EMConnectionListener{
            override fun onConnected() {

            }

            override fun onDisconnected(p0: Int) {
                if(p0==EMError.USER_LOGIN_ANOTHER_DEVICE){
                    startActivity<LoginActivity>()
                    finish()
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()
        //初始化Bottombar
        val tab = bottomBar.getTabWithId(R.id.tab_conversation)
        tab.setBadgeCount(EMClient.getInstance().chatManager().unreadMessageCount)
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)

    }
}
