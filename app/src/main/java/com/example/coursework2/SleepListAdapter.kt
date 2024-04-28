package com.example.coursework2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.text.format.DateFormat
import android.util.Log
import android.widget.AdapterView
import android.widget.ProgressBar
import java.util.Date


class SleepListAdapter (private val sleepList: MutableList<SleepModel>) : RecyclerView.Adapter<SleepListAdapter.ViewHolder>() {

    var selectedID : Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = sleepList[position]
        holder.sleepImage.setImageResource(R.drawable.red_smile)
        holder.dreamTextShort.text = item.dreamtext
        holder.dreamDate.text = DateFormat.format("yyyy-MM-dd", Date(item.date)).toString()
        holder.dreamRating.progress = item.rating

    }

    override fun getItemCount(): Int {
        return sleepList.size
    }

    fun getItemAt(position: Int): SleepModel {
        return sleepList[position]
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var sleepImage = itemView.findViewById<ImageView>(R.id.length_icon)
        var dreamTextShort = itemView.findViewById<TextView>(R.id.sleep_description)
        var dreamDate = itemView.findViewById<TextView>(R.id.dream_date)
        var dreamRating = itemView.findViewById<ProgressBar>(R.id.progress_bar)

        init {
            itemView.setOnClickListener {
                selectedID=(sleepList[adapterPosition].id)
                loadViewSleep(itemView.context, sleepList[adapterPosition].id)
            }
        }
        private fun loadViewSleep(context: Context, id: Int?) {
            val intent = Intent(context, ViewSleep::class.java)
            intent.putExtra("sleepId", id)
            context.startActivity(intent)
        }
    }
}