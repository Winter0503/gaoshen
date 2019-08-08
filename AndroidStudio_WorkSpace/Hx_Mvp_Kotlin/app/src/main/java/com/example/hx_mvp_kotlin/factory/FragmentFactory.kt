package com.example.hx_mvp_kotlin.factory

import android.support.v4.app.Fragment
import com.example.hx_mvp_kotlin.R
import com.example.hx_mvp_kotlin.ui.fragment.ContactFragment
import com.example.hx_mvp_kotlin.ui.fragment.ConversationFragment
import com.example.hx_mvp_kotlin.ui.fragment.DynamicFragment

/**
 * Create by 心跳 on 2019/8/8 8:02
 * Blog : https://mp.csdn.net/
 * escription :
 */
class FragmentFactory private constructor(){

    val conversation by lazy {
        ConversationFragment()
    }

    val contact by lazy {
        ContactFragment()
    }

    val dynamic by lazy {
        DynamicFragment()
    }

    companion object{
        val instance=FragmentFactory()
    }

    fun getFragment(tabId: Int) : Fragment?{
        when(tabId){
            R.id.tab_conversation -> return conversation
            R.id.tab_contacts -> return contact
            R.id.tab_dynamic -> return dynamic
        }

        return null
    }
}