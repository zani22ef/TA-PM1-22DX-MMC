package com.uti.turtleguard.config

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.uti.turtleguard.config.Constant.Companion.DB_NAME
import com.uti.turtleguard.config.Constant.Companion.DB_VERSION

class Lite (context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    data class Pengguna(val namaLengkap: String, val username: String, val password: String)
    override fun onCreate(db: SQLiteDatabase?) {
        //        BUat variable untuk create table
        val table = "CREATE TABLE pengguna(id INTEGER PRIMARY KEY AUTOINCREMENT, nama_lengkap VARCHAR(100), username CHAR(8), password VARCHAR(100),hak_akses VARCHAR(100))"
//        eksekusi kueri
        db?.execSQL(table)
//        simpan data
        val insert = "INSERT INTO pengguna(nama_lengkap, username, password) VALUES('ADMIN','root','admin','admin')"
        db?.execSQL(insert)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun login(username: String, password: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM pengguna WHERE username=? AND password=?"
        val cursor = db.rawQuery(query, arrayOf(username, password))
        val count = cursor.count
        cursor.close()
        db.close()
        return count > 0
    }

    fun insertPengguna(pengguna: Pengguna): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nama_lengkap", pengguna.namaLengkap)
            put("username", pengguna.username)
            put("password", pengguna.password)
        }
        return db.insert("pengguna", null, values)
    }

}