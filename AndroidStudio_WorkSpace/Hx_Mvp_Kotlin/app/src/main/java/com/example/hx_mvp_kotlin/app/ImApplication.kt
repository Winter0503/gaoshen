package com.example.hx_mvp_kotlin.app

import android.app.Application
import android.provider.CalendarContract
import cn.bmob.v3.Bmob
import com.example.hx_mvp_kotlin.BuildConfig
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions

/**
 * Create by 心跳 on 2019/8/4 21:14
 * Blog : https://mp.csdn.net/
 * escription :
 */
class ImApplication : Application() {

    companion object{
        lateinit var instances: ImApplication
    }

    override fun onCreate() {
        super.onCreate()

        instances=this
        val options =EMOptions()
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false)
// 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true)
// 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true)
//初始化
        EMClient.getInstance().init(applicationContext, options)
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)

        Bmob.initialize(this,"c2a928ba23a54b2c92b966f646f23ce6")
    }
}