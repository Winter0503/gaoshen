package com.example.hx_mvp_kotlin.extentions

/**
 * Create by 心跳 on 2019/8/6 17:28
 * Blog : https://mp.csdn.net/
 * escription :
 */
fun String.isValidUserName(): Boolean = this.matches(Regex("[a-zA-Z]\\w{2,19}$"))
fun String.isValidPassWord(): Boolean = this.matches(Regex("[0-9]{3,20}$"))

fun <K,V> MutableMap<K,V>.toVarargArray():Array<Pair<K,V>> =
    map{
        Pair(it.key,it.value)
    }.toTypedArray()
