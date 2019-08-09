package com.example.hx_mvp_kotlin.data.db

import com.example.hx_mvp_kotlin.extentions.toVarargArray
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Create by 心跳 on 2019/8/9 14:06
 * Blog : https://mp.csdn.net/
 * escription :
 */
class IMDatabase{

    companion object{
        val databaseHelper= DatabaseHelper()
        val instance = IMDatabase()
    }

    fun saveContact(contact : Contact){
        databaseHelper.use {
            insert(ContactTable.NAME,*contact.map.toVarargArray())
        }
    }

    fun getAllContacts():List<Contact>  =
        databaseHelper.use {
            select(ContactTable.NAME).parseList(object : MapRowParser<Contact> {
                override fun parseRow(columns: Map<String, Any?>): Contact =  Contact(columns.toMutableMap())
            })
        }

    fun  deleteAllContacts() {
        databaseHelper.use{
            delete(ContactTable.NAME,null,null)
        }
    }
}
















