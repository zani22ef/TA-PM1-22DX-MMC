package com.uti.turtleguard

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.uti.turtleguard.dashboard.ReportFragment
import com.uti.turtleguard.databinding.ActivityAddReportBinding

class AddReport : AppCompatActivity() {
    private lateinit var inputNama : EditText
    private lateinit var inputLaporan : EditText
    private lateinit var inputLokasi : EditText
    private lateinit var btnLaporan : Button
    private lateinit var backImage : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityAddReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.AddReport)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
//
//    private fun replaceFragment(fragment: Fragment){
//        val fragmentManager = this.supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.frameLayout, fragment).addToBackStack(null).commit()
//    }
}