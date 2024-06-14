package com.uti.turtleguard

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.uti.turtleguard.config.Lite
import com.uti.turtleguard.onboarding.ViewPagerFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        panggil class lite
        val lite = Lite(this)
        lite.writableDatabase
        supportFragmentManager.beginTransaction().replace(R.id.frmMain, ViewPagerFragment()).commit()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.AddReport)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}