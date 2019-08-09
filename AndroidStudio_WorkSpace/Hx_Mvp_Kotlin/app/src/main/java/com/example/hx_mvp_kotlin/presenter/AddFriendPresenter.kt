package com.example.hx_mvp_kotlin.presenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.hx_mvp_kotlin.contract.AddFriendContract
import com.example.hx_mvp_kotlin.data.AddFriendItem
import com.example.hx_mvp_kotlin.data.db.IMDatabase
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.doAsync

/**
 * Create by 心跳 on 2019/8/9 10:47
 * Blog : https://mp.csdn.net/
 * escription :  添加好友的Model层
 */
class AddFriendPresenter(val view: AddFriendContract.View): AddFriendContract.Presenter{

    val addFriendItems = mutableListOf<AddFriendItem>()
    override fun search(key: String) {
        //从 bmob 查询用户
        val query = BmobQuery<BmobUser>()
        query.addWhereContains("username",key)
            .addWhereNotEqualTo("username",EMClient.getInstance().currentUser)
        query.findObjects(object : FindListener<BmobUser>(){
            override fun done(p0: MutableList<BmobUser>?, p1: BmobException?) {
                if(p1==null){
                    //处理数据
                    //创建AddFriendItem的集合
                    val allContact = IMDatabase.instance.getAllContacts()
                    doAsync {
                        p0!!.forEach {
                            //比对好友列表
                            var isAdded=false
                            for (contact in allContact) {
                                if(contact.name == it.username){
                                    isAdded=true
                                }
                            }

                            val addFriendItem= AddFriendItem(it.username,it.createdAt,isAdded)
                            addFriendItems.add(addFriendItem)

                        }
                        uiThread { view.onSearchSuccess() }
                    }
                }
                else view.onSearchFailed()
            }

        })

    }

}