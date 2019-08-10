package com.example.hx_mvp_kotlin.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.hx_mvp_kotlin.R
import com.example.hx_mvp_kotlin.adapter.EMMessageListenerAdapter
import com.example.hx_mvp_kotlin.adapter.MessageListAdapter
import com.example.hx_mvp_kotlin.contract.ChatContract
import com.example.hx_mvp_kotlin.presenter.ChatPresenter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 * Create by 心跳 on 2019/8/8 20:06
 * Blog : https://mp.csdn.net/
 * escription :
 */
class ChatActivity :BaseActivity(),ChatContract.View{


    override fun getLayoutResId(): Int = R.layout.activity_chat

    lateinit var username :String
    val presenter=ChatPresenter(this)

    val messageListener = object : EMMessageListenerAdapter(){

        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            presenter.addMessage(username,p0)
            runOnUiThread {
                recyclerView.adapter!!.notifyDataSetChanged()
                scrollToBottom()
            }
        }

    }
    override fun init() {
        super.init()
        initHeader()
        initEditText()
        initRecyclerView()
        send.setOnClickListener { send()}

        EMClient.getInstance().chatManager().addMessageListener(messageListener)

        presenter.loadMessage(username)
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(context)
            adapter= MessageListAdapter(context,presenter.messages)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if(newState == RecyclerView.SCROLL_STATE_IDLE){
                        val lineaLayoutManager = layoutManager as LinearLayoutManager
                        if(lineaLayoutManager.findFirstCompletelyVisibleItemPosition()==0){
                            presenter.loadMoreMessages(username)
                        }
                    }
                }
            })
        }
    }

    fun send(){
        hideSoftKeyboar()
        val message = edit.text.trim().toString()
        presenter.sendMessage(username,message)
    }

    private fun initEditText() {
        edit.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                send.isEnabled = !p0.isNullOrEmpty()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })

        edit.setOnEditorActionListener { textView, i, keyEvent ->
            send()
            true
        }
    }

    private fun initHeader() {
        back.visibility= View.VISIBLE
        back.setOnClickListener { finish() }

        username = intent.getStringExtra("username")
        val titleString =String.format(getString(R.string.chat_title),username)
        headerTitle.text=titleString
    }

    override fun onStartSendMessage() {
        //刷新列表，显示一条正在发送的消息
        recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onSendMessageSuccess() {
        recyclerView.adapter!!.notifyDataSetChanged()
        toast(R.string.send_message_success)
        edit.text.clear()

        scrollToBottom()
    }

    private fun scrollToBottom() {
        recyclerView.scrollToPosition(presenter.messages.size-1)
    }

    override fun onSendMessageFailed() {
        toast(R.string.send_message_failed)
        recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }

    override fun onMessageLoaded() {
        recyclerView.adapter!!.notifyDataSetChanged()
        scrollToBottom()
    }

    override fun onMoreMessageLoaded(size: Int) {
        recyclerView.adapter!!.notifyDataSetChanged()
        recyclerView.scrollToPosition(size)
    }
}