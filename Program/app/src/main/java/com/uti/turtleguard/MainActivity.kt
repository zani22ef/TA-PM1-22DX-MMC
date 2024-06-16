package com.uti.turtleguard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.uti.turtleguard.config.Lite
import com.uti.turtleguard.dashboard.Bottom_Nav
import com.uti.turtleguard.onboarding.ViewPagerFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        panggil class lite
        val lite = Lite(this)
        lite.writableDatabase
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.AddReport)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Pemeriksaan login
        val sharedPreferences = getSharedPreferences("namauser", Context.MODE_PRIVATE)
        val loggedInUser = sharedPreferences.getString("logged_in_user", null)
        if (loggedInUser != null) {
            // Pengguna sudah login, arahkan ke halaman utama
            supportFragmentManager.beginTransaction().replace(R.id.frmMain, Bottom_Nav()).commit()
//            supportFragmentManager.beginTransaction().replace(R.id.frmMain, CobaTampilFragment()).commit()
        } else {
            // Pengguna belum login, tampilkan onboarding
            supportFragmentManager.beginTransaction().replace(R.id.frmMain, ViewPagerFragment()).commit()
        }
    }

}