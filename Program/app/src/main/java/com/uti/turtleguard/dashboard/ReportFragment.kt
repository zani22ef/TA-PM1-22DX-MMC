package com.uti.turtleguard.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.uti.turtleguard.AddReport
import com.uti.turtleguard.MainActivity
import com.uti.turtleguard.R
import com.uti.turtleguard.adapter.ReportAncamanAdapter
import com.uti.turtleguard.config.Lite
import com.uti.turtleguard.databinding.ActivityAddReportBinding
import com.uti.turtleguard.databinding.FragmentReportBinding


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
//        val view = inflater.inflate(R.layout.fragment_report, container, false)
//        val floatingBtnAdd = view.findViewById<FloatingActionButton>(R.id.floatingBtnAdd)
        val binding = FragmentReportBinding.inflate(inflater, container, false)

        val recycler = binding.recyclerView
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val lite = Lite(requireContext())
        val report = lite.getAllReport()
        val ReportAdapter = ReportAncamanAdapter(reportList = report)
        binding.recyclerView.adapter = ReportAdapter

        binding.floatingBtnAdd.setOnClickListener{

            Toast.makeText(requireContext(), "Add terbuka", Toast.LENGTH_LONG).show()
            val intent = Intent(requireActivity(),AddReport::class.java)
            startActivity(intent)
        }


        return binding.root
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment).addToBackStack(null).commit()
    }
}
