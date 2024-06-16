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
        val table = "CREATE TABLE pengguna(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nama_lengkap VARCHAR(100)," +
                " username CHAR(8)," +
                " password VARCHAR(100)," +
                "hak_akses VARCHAR(20))"
//        eksekusi kueri
        db?.execSQL(table)

        // Buat variable untuk create table report
        val tableReport = "CREATE TABLE report(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "laporan VARCHAR(255)," +
                "keterangan VARCHAR(255)," +
                "lokasi VARCHAR(100)," +
                "statusLaporan VARCHAR(50)," +
                "idPengguna INTEGER," +
                "FOREIGN KEY(idPengguna) REFERENCES pengguna(id))"

        // Eksekusi kueri untuk membuat tabel report
        db?.execSQL(tableReport)
//        simpan data
        val insert = "INSERT INTO pengguna(nama_lengkap, username, password) VALUES" +
                "('ADMIN','root','admin','admin')," +
                "('HERU ARBIANTO', 'heru', 'arbi','user')," +
                "('Zani Eko F', 'zani', 'eko','user')"
        db?.execSQL(insert)
        val insertuser = "INSERT INTO pengguna(id, nama_lengkap, username, password) VALUES" +
                "(2,'user','user','user','user')"
        db?.execSQL(insertuser)

//        Insert Report
        val insertreport = "INSERT INTO tableReport(laporan, keterangan, lokasi, statusLaporan, idPengguna) VALUES" +
                "('Penyelundupan telur penyu di peabuhan','ada indikasi penyelundupan telur penyu di pelabuhan','Pelabuhan Bakauheni','Belum Ditindak', 2)," +
                "('Perburuan Penyu','terdapat pemburu yang sedang berburu penyu','Pantai Mutun','Belum Ditindak', 2)," +
                "('Perdagangan ilegal Cangkang Penyu','Ditemukan perdagangan cangkang penyu ilegal di pasar','Pasar Bambbu Kuning','Belum Ditindak', 2)"
        db?.execSQL(insertreport)


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
    fun getFirstName(loggedInUser: String?): String? {
        val db = readableDatabase
        val query = "SELECT nama_lengkap FROM pengguna WHERE username = ?"
        val cursor = db.rawQuery(query, arrayOf(loggedInUser))
        var firstName: String? = null
        if (cursor != null && cursor.moveToFirst()) {
            val fullName = cursor.getString(cursor.getColumnIndexOrThrow("nama_lengkap"))
            firstName = fullName.split(" ").firstOrNull()
            cursor.close()
        }
        db.close()
        return firstName
    }
    // Fungsi untuk mengecek apakah username sudah ada di tabel pengguna
    fun isUsernameExists(username: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM pengguna WHERE username = ?"
        val cursor = db.rawQuery(query, arrayOf(username))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }
//code percobaan
fun getAllPengguna(): List<Pengguna> {
    val penggunaList = mutableListOf<Pengguna>()
    val db = readableDatabase
    val query = "SELECT nama_lengkap, username, password FROM pengguna"
    val cursor = db.rawQuery(query, null)

    if (cursor.moveToFirst()) {
        do {
            val namaLengkap = cursor.getString(cursor.getColumnIndexOrThrow("nama_lengkap"))
            val username = cursor.getString(cursor.getColumnIndexOrThrow("username"))
            val password = cursor.getString(cursor.getColumnIndexOrThrow("password"))
            penggunaList.add(Pengguna(namaLengkap, username, password))
        } while (cursor.moveToNext())
    }

    cursor.close()
    db.close()
    return penggunaList
}
}