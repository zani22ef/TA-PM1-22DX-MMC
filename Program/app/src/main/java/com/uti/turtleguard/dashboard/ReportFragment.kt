package com.uti.turtleguard.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.uti.turtleguard.AddReport
import com.uti.turtleguard.MainActivity
import com.uti.turtleguard.R
import com.uti.turtleguard.databinding.ActivityAddReportBinding


class ReportFragment : Fragment() {


    private var data: String? = null

    fun setData(data: String) {
        this.data = data
    }

    private lateinit var listView: ListView
    private lateinit var adapter: ReportAdapter
    private lateinit var binding: ActivityAddReportBinding

    private val reports = mutableListOf<Report>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_report, container, false)
        val floatingBtnAdd = view.findViewById<FloatingActionButton>(R.id.floatingBtnAdd)
        listView = view.findViewById(R.id.listView)

        // Inisialisasi array title dan description
        val titles = arrayOf(
            "Penculikan penyu di pantai selatan",
            "Perburuan penyu liar di laut dalam",
            "Penyelundupan telur penyu di pelabuhan",
            "Perdagangan ilegal cangkang penyu di pasar",
            "Pembalakan liar yang merusak habitat penyu",
            "Pencurian artefak bersejarah tentang penyu dari museum",
            "Penyelundupan produk berbasis penyu di perbatasan negara",
            "Penelitian ilegal terhadap penyu di laut lepas",
            "Pemalsuan produk kecantikan dari minyak penyu",
            "Eksploitasi anak untuk perburuan penyu di pantai",
            "Pencurian penyu untuk dijual sebagai hewan peliharaan eksotis",
            "Penyelundupan daging penyu di restoran mewah",
            "Pencurian data penelitian tentang konservasi penyu",
            "Peretasan sistem informasi pusat penelitian penyu",
            "Penyelundupan hewan penyu eksotis di bandara internasional",
            "Pemalsuan dokumen pengiriman penyu di terminal bus",
            "Kejahatan lingkungan akibat perusakan habitat penyu",
            "Pencurian identitas untuk klaim donasi konservasi penyu",
            "Perjudian ilegal tentang balapan penyu",
            "Penyalahgunaan kekuasaan dalam proyek konservasi penyu"
        )

        val descriptions = arrayOf(
            "Kasus penculikan penyu yang ditemukan di pantai selatan. Penyelidikan sedang berlangsung untuk menemukan pelakunya.",
            "Perburuan penyu liar di laut dalam mengancam populasi penyu. Upaya konservasi sedang ditingkatkan.",
            "Penyelundupan telur penyu di pelabuhan berhasil digagalkan. Pelaku telah ditangkap.",
            "Perdagangan ilegal cangkang penyu di pasar lokal ditemukan. Penyelidikan lebih lanjut dilakukan.",
            "Pembalakan liar yang merusak habitat penyu di hutan konservasi. Langkah pencegahan sedang diterapkan.",
            "Pencurian artefak bersejarah tentang penyu dari museum. Barang-barang berharga sedang dicari.",
            "Penyelundupan produk berbasis penyu di perbatasan negara terdeteksi. Penyelidikan sedang dilakukan.",
            "Penelitian ilegal terhadap penyu di laut lepas tanpa izin resmi. Tindakan hukum sedang dipertimbangkan.",
            "Pemalsuan produk kecantikan dari minyak penyu ditemukan di pasaran. Produk berbahaya disita.",
            "Eksploitasi anak untuk perburuan penyu di pantai terungkap. Langkah hukum diambil terhadap pelaku.",
            "Pencurian penyu untuk dijual sebagai hewan peliharaan eksotis sedang meningkat. Patroli ditingkatkan.",
            "Penyelundupan daging penyu di restoran mewah di kota besar. Restoran tersebut sedang diselidiki.",
            "Pencurian data penelitian tentang konservasi penyu dari pusat riset. Keamanan data ditingkatkan.",
            "Peretasan sistem informasi pusat penelitian penyu menyebabkan kerugian besar. Tindakan pencegahan dilakukan.",
            "Penyelundupan hewan penyu eksotis di bandara internasional berhasil dicegah. Hewan diselamatkan.",
            "Pemalsuan dokumen pengiriman penyu di terminal bus ditemukan. Penyelidikan lebih lanjut dilakukan.",
            "Kejahatan lingkungan akibat perusakan habitat penyu oleh pembuangan limbah ilegal terdeteksi.",
            "Pencurian identitas untuk klaim donasi konservasi penyu. Warga diimbau untuk berhati-hati.",
            "Perjudian ilegal tentang balapan penyu terungkap. Pelaku dijerat hukum.",
            "Penyalahgunaan kekuasaan dalam proyek konservasi penyu oleh pejabat terkait sedang diselidiki."
        )


        // Populate reports list
        for (i in titles.indices) {
            reports.add(Report(titles[i], descriptions[i]))
        }


        floatingBtnAdd.setOnClickListener{

            Toast.makeText(requireContext(), "Add terbuka", Toast.LENGTH_LONG).show()
            val intent = Intent(requireActivity(),AddReport::class.java)
            startActivity(intent)
//            requireActivity().finish()


//            val inputN = binding.inputNama.text.toString()
//            val inputL = binding.inputLaporan.text.toString()
//
//            reports.add(Report(inputN,inputL))
        }

        adapter = ReportAdapter(requireContext(), reports)
        listView.adapter = adapter

        return view
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment).addToBackStack(null).commit()
    }
}
