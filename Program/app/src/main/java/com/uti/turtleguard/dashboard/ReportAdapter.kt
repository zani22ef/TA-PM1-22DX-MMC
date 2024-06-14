package com.uti.turtleguard.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.uti.turtleguard.R

data class Report(val title: String, val description: String)

class ReportAdapter(private val context: Context, private val dataSource: List<Report>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.list_item_report, parent, false)
            holder = ViewHolder()
            holder.titleTextView = view.findViewById(R.id.report_title) as TextView
            holder.descriptionTextView = view.findViewById(R.id.report_description) as TextView
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val report = getItem(position) as Report
        holder.titleTextView?.text = report.title
        holder.descriptionTextView?.text = report.description

        return view
    }

    private class ViewHolder {
        var titleTextView: TextView? = null
        var descriptionTextView: TextView? = null
    }
}
