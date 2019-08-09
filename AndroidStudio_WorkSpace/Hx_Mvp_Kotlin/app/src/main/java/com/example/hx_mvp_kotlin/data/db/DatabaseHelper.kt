package com.example.hx_mvp_kotlin.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.hx_mvp_kotlin.app.ImApplication
import org.jetbrains.anko.db.*

/**
 * Create by 心跳 on 2019/8/9 12:16
 * Blog : https://mp.csdn.net/
 * escription :
 */
class DatabaseHelper(ctx: Context=ImApplication.instances) :
    ManagedSQLiteOpenHelper(ctx,
        NAME, null,
        VERSION
    ) {

    companion object{

        val NAME= "im.db"
        val VERSION=1
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.createTable(ContactTable.NAME,true,
            ContactTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            ContactTable.CONTACT to TEXT)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.dropTable(ContactTable.NAME,true)
        onCreate(p0)
    }
}