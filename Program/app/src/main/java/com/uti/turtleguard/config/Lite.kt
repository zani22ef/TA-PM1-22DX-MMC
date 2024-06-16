package com.uti.turtleguard.config

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.uti.turtleguard.config.Constant.Companion.DB_NAME
import com.uti.turtleguard.config.Constant.Companion.DB_VERSION

class Lite (context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    data class Pengguna(val namaLengkap: String, val username: String, val password: String)
    data class Reports(val laporan: String, val keterangan: String, val lokasi: String, val statusLaporan: String)
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

        //        simpan data
        val insert = "INSERT INTO pengguna(nama_lengkap, username, password, hak_akses) VALUES" +
                "('ADMIN','root','admin','admin')," +
                "('HERU ARBIANTO', 'heru', 'arbi','user')," +
                "('Zani Eko F', 'zani', 'eko','user')"
        db?.execSQL(insert)
        val insertuser = "INSERT INTO pengguna(id, nama_lengkap, username, password, hak_akses) VALUES" +
                "(7887,'user','user','user','user')"
        db?.execSQL(insertuser)

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


//        Insert Report
        val insertreport = "INSERT INTO report(laporan, keterangan, lokasi, statusLaporan, idPengguna) VALUES" +
                "('Penyelundupan telur penyu di peabuhan','ada indikasi penyelundupan telur penyu di pelabuhan','Pelabuhan Bakauheni','Belum Ditindak', 7887)," +
                "('Perburuan Penyu','terdapat pemburu yang sedang berburu penyu','Pantai Mutun','Belum Ditindak', 7887)," +
                "('Perdagangan ilegal Cangkang Penyu','Ditemukan perdagangan cangkang penyu ilegal di pasar','Pasar Bambbu Kuning','Belum Ditindak', 7887)"
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

//    ambil semua data report
fun getAllReport(): List<Reports> {
    val reportList = mutableListOf<Reports>()
    val db = readableDatabase
    val query = "SELECT laporan, keterangan, lokasi, statusLaporan FROM report" // Ubah tableReport menjadi report
    val cursor = db.rawQuery(query, null)

    if (cursor.moveToFirst()) {
        do {
            val laporan = cursor.getString(cursor.getColumnIndexOrThrow("laporan"))
            val keterangan = cursor.getString(cursor.getColumnIndexOrThrow("keterangan"))
            val lokasi = cursor.getString(cursor.getColumnIndexOrThrow("lokasi"))
            val statusLaporan = cursor.getString(cursor.getColumnIndexOrThrow("statusLaporan")) // Ubah status menjadi statusLaporan
            reportList.add(Reports(laporan, keterangan, lokasi, statusLaporan))
        } while (cursor.moveToNext())
    }

    cursor.close()
    db.close()
    return reportList
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