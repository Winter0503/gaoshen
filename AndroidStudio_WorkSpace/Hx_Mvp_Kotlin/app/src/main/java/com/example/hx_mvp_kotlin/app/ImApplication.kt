package com.example.hx_mvp_kotlin.app

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioManager
import android.media.SoundPool
import android.provider.CalendarContract
import cn.bmob.v3.Bmob
import com.example.hx_mvp_kotlin.BuildConfig
import com.example.hx_mvp_kotlin.R
import com.example.hx_mvp_kotlin.adapter.EMMessageListenerAdapter
import com.example.hx_mvp_kotlin.app.ImApplication.Companion.instances
import com.example.hx_mvp_kotlin.ui.activity.ChatActivity
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMOptions
import com.hyphenate.chat.EMTextMessageBody
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Create by 心跳 on 2019/8/4 21:14
 * Blog : https://mp.csdn.net/
 * escription :
 */
class ImApplication : Application() {

    companion object{
        lateinit var instances: ImApplication
    }

    val soundPool= SoundPool(2,AudioManager.STREAM_MUSIC,0)

    val duan by lazy{
        soundPool.load(instances,R.raw.duan,0)
    }
    val yulu by lazy{
        soundPool.load(instances,R.raw.yulu,0)
    }



    val messageListener=object : EMMessageListenerAdapter(){
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            if(isForeground()){
                soundPool.play(duan,1f,1f,0,0,1f)
            }else{
                soundPool.play(yulu,1f,1f,0,0,1f)
                showNotification(p0)
            }
        }
    }

    private fun showNotification(p0: MutableList<EMMessage>?) {
        p0!!.forEach {
            var contentText =getString(R.string.no_text_message)
            if(it.type ==EMMessage.Type.TXT){
                contentText= (it.body as EMTextMessageBody).message
            }

            val intent= Intent(this, ChatActivity::class.java)
            intent.putExtra("username",it.conversationId())
//            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val taskStackBuilder = TaskStackBuilder.create(this).addParentStack(ChatActivity::class.java)
                .addNextIntent(intent)
            val pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

            val notificationManafer =getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val notification = Notification.Builder(this)
                .setContentTitle(getString(R.string.receive_new_message))
                .setContentText(contentText)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.mipmap.avatar1))
                .setSmallIcon(R.mipmap.ic_contact)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .notification

            notificationManafer.notify(1,notification)

        }
    }

    override fun onCreate() {
        super.onCreate()

        instances=this
        val options =EMOptions()
// 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true)
// 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true)
//初始化
        EMClient.getInstance().init(applicationContext, options)
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)

        Bmob.initialize(this,"c2a928ba23a54b2c92b966f646f23ce6")

        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }

    private  fun isForeground(): Boolean{
        val activityManager=getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (runningAppProcess in activityManager.runningAppProcesses){
            if(runningAppProcess.processName==packageName){
                return runningAppProcess.importance==ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            }
        }
        return false
    }
}