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
            "Perburuan harimau liar di hutan belantara",
            "Penyelundupan satwa langka di pelabuhan",
            "Perdagangan manusia di kota metropolitan",
            "Pembalakan liar di hutan konservasi",
            "Pencurian artefak bersejarah dari museum",
            "Penyelundupan narkotika di perbatasan negara",
            "Kejahatan cyber terorganisir di dunia maya",
            "Pemalsuan uang di industri percetakan ilegal",
            "Perbudakan anak di bawah umur di pedesaan",
            "Pencurian mobil mewah di pusat perbelanjaan",
            "Penyelundupan senjata api di zona konflik",
            "Pencurian data pribadi dari perusahaan besar",
            "Peretasan sistem keamanan bank nasional",
            "Penyelundupan hewan eksotis di bandara internasional",
            "Pemalsuan dokumen perjalanan di terminal bus",
            "Kejahatan lingkungan akibat pembuangan limbah ilegal",
            "Pencurian identitas untuk tujuan keuangan",
            "Perjudian ilegal di tempat persembunyian",
            "Penyalahgunaan kekuasaan dalam pemerintahan daerah"
        )

        val descriptions = arrayOf(
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae.",
            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repellendus, beatae. Voluptatibus magni sunt consectetur voluptatum impedit. Doloribus, sequi recusandae! Quas amet doloremque vel assumenda repellat eligendi rerum atque velit recusandae."
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
