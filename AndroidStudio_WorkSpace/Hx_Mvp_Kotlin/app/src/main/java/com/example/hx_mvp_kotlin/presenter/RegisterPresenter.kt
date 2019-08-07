package com.example.hx_mvp_kotlin.presenter

import android.net.sip.SipSession
import cn.bmob.v3.BmobUser
import com.example.hx_mvp_kotlin.contract.RegisterContract
import com.example.hx_mvp_kotlin.extentions.isValidPassWord
import com.example.hx_mvp_kotlin.extentions.isValidUserName
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync


/**
 * escription :
 */
class RegisterPresenter(val view : RegisterContract.View) : RegisterContract.Presenter{
    override fun register(userName: String, password: String, confirmpassword: String) {
        if(userName.isValidUserName()){
            //用户名合法
            if(password.isValidPassWord()){
                //密码合法
                if(confirmpassword.equals(password)){
                    view.onStartRegister()
                    registerBmob(userName,password)
                }else view.onConfirmPasswordError()
            }else view.onPasswordError()
        }else view.onUserNameError()
    }

    private fun registerBmob(userName: String, password: String) {
        val user = BmobUser()
        user.username=userName
        user.setPassword(password)
        user.signUp(object : SaveListener<BmobUser>() {
            override fun done(user: BmobUser?, e: BmobException?) {
                if (e == null) {
                    registerEaseMob(userName,password)
                } else {
                    if(e.errorCode==202) view.onUserExist()
                   view.onRegisterFailed()
                }
            }
        })


    }

    private fun registerEaseMob(userName: String, password: String) {
        doAsync {
            try{
                EMClient.getInstance().createAccount(userName,password) //同步方法
                uiThread { view.onRegisterSuccess() }
            }catch (e : HyphenateException){
                uiThread { view.onRegisterFailed() }
            }
        }

    }

}
