package com.uti.turtleguard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uti.turtleguard.R
import com.uti.turtleguard.config.Lite

class ReportAncamanAdapter(private val reportList: List<Lite.Reports>) : RecyclerView.Adapter<ReportAncamanAdapter.ReportViewHolder>() {

    class ReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val laporanTextView: TextView = itemView.findViewById(R.id.tvLaporan)
        val keteranganTextView: TextView = itemView.findViewById(R.id.tvKeterangan)
        val lokasiTextView: TextView = itemView.findViewById(R.id.tvLokasi)
        val statusTextView: TextView = itemView.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layoutreportancamanpenyu, parent, false)
        return ReportViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reportList.size
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val report = reportList[position]
        holder.laporanTextView.text = report.laporan
        holder.keteranganTextView.text = report.keterangan
        holder.lokasiTextView.text = report.lokasi
        holder.statusTextView.text = report.statusLaporan
    }
}
