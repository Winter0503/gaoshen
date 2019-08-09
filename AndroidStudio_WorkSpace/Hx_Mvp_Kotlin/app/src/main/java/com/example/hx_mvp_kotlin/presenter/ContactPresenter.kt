package com.example.hx_mvp_kotlin.presenter

import com.example.hx_mvp_kotlin.contract.ContactContract
import com.example.hx_mvp_kotlin.data.ContactListItem
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync


/**
 * Create by 心跳 on 2019/8/8 16:23
 * Blog : https://mp.csdn.net/
 * escription :
 */
class ContactPresenter(val view : ContactContract.view) : ContactContract.presenter {

    val contactListItems = mutableListOf<ContactListItem >()
    override fun loadContact() {

        doAsync {
            //清空集合
            contactListItems.clear()
            try {
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                usernames.sortBy{ it[0] }
                usernames.forEachIndexed { index, s ->
                    //判断是否显示首字符
                    val showFirstLetter= index == 0 || s[0] !=usernames[index-1][0]
                    val contactListItem=ContactListItem(s,s[0].toUpperCase(),showFirstLetter)
                    contactListItems.add(contactListItem)

                }
                uiThread { view.onLoadContactsSuccess() }
            }catch (e: HyphenateException){
                uiThread { view.onLoadContactsFiled() }
            }
        }

    }
}