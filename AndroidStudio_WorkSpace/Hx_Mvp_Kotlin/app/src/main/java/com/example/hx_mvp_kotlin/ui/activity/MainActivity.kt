package com.example.hx_mvp_kotlin.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.hx_mvp_kotlin.R
import com.example.hx_mvp_kotlin.factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int =R.layout.activity_main

    override fun init() {
        super.init()
        bottomBar.setOnTabSelectListener{ tabId ->
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(R.id.fragment_frame,FragmentFactory.instance.getFragment(tabId)!!)
            beginTransaction.commit()
        }
    }

}
