package com.uti.turtleguard

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.uti.turtleguard.config.Lite
import com.uti.turtleguard.dashboard.Bottom_Nav
import com.uti.turtleguard.dashboard.ReportFragment
import com.uti.turtleguard.databinding.ActivityAddReportBinding
import com.uti.turtleguard.form.LoginFormFragment

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


        val sharedPreferences = getSharedPreferences("namauser", Context.MODE_PRIVATE)
        // Ambil nama pengguna yang login dari penyimpanan lokal
        val loggedInUser = sharedPreferences.getString("logged_in_user", null)

        // Membuat instance Lite dengan menggunakan context fragment
        val lite = Lite(this)

        // Memanggil metode getFirstName() dari instance Lite
        val idPengguna = lite.getUserId(loggedInUser)

        binding.btnLapor.setOnClickListener(){
            val laporan =  binding.inputLaporan.text.toString()
            val keterangan = binding.inputKeterangan.text.toString()
            val lokasi = binding.inputLokasi.text.toString()
            var status = "Belum Ditindak"
            if (laporan.length>1 && keterangan.length>1 && lokasi.length>1){
                val laporan = Lite.Reportsinsert(laporan, keterangan, lokasi, status, idPengguna.toString().toInt())
                val hasil = lite.insertReport(laporan)
                if (hasil != -1L) {
                    Toast.makeText(this, "Laporan Berhasil dikirim", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "Gagal memasukkan data", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Semua form wajib diisi", Toast.LENGTH_LONG).show()
            }
        }
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