package com.uti.turtleguard.config

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.uti.turtleguard.config.Constant.Companion.DB_NAME
import com.uti.turtleguard.config.Constant.Companion.DB_VERSION

class Lite (context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        //        BUat variable untuk create table
        val table = "CREATE TABLE pengguna(id INTEGER PRIMARY KEY AUTOINCREMENT, nama_lengkap VARCHAR(100), username CHAR (8), password VARCHAR(100))"
//        eksekusi kueri
        db?.execSQL(table)
//        simpan data
        val insert = "INSERT INTO pengguna VALUES('ADMIN','root','admin')"
        db?.execSQL(insert)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}