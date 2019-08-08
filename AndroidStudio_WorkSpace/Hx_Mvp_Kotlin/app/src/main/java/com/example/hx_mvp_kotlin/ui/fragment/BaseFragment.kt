package com.example.hx_mvp_kotlin.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Create by 心跳 on 2019/8/3 8:41
 * Blog : https://mp.csdn.net/
 * escription :
 */
abstract class BaseFragment :Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(getlayoutResId(),null)

    abstract fun getlayoutResId() :Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    open fun init() {
        //初始化公共的功能，子类可以重写该方法，

    }


}