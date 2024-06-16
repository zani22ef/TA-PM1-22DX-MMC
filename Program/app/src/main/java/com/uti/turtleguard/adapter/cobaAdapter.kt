package com.uti.turtleguard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uti.turtleguard.R
import com.uti.turtleguard.config.Lite

class cobaAdapter (private val penggunaList: List<Lite.Pengguna>): RecyclerView.Adapter<cobaAdapter.PenggunaViewHolder>(){
class PenggunaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val namaLengkapTextView: TextView = itemView.findViewById(R.id.namaLengkapTextView)
    val usernameTextView: TextView = itemView.findViewById(R.id.usernameTextView)
    val passwordTextView: TextView = itemView.findViewById(R.id.passwordTextView)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenggunaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cobalayout, parent, false)
        return PenggunaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return penggunaList.size
    }

    override fun onBindViewHolder(holder: PenggunaViewHolder, position: Int) {
        val pengguna = penggunaList[position]
        holder.namaLengkapTextView.text = "Nama : "+ pengguna.namaLengkap
        holder.usernameTextView.text = pengguna.username
        holder.passwordTextView.text = pengguna.password
    }
}