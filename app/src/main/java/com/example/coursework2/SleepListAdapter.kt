package com.example.coursework2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class SleepListAdapter (private val sleepList: MutableList<SleepModel>) : RecyclerView.Adapter<SleepListAdapter.ViewHolder>() {

    /*
     * Inflate our views using the layout defined in row_layout.xml
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_layout, parent, false)

        return ViewHolder(view)

    }

    /*
     * Bind the data to the child views of the ViewHolder
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = sleepList[position]

        holder.lenView.setImageResource(item.lengthImg)
        holder.dreamView.setImageResource(item.dreamImg)
        holder.txtView.text = item.dreamtext
    }

    /*
     * Number of models in the array
     */
    override fun getItemCount(): Int {
        return sleepList.size
    }

    fun getItemAt(position: Int): SleepModel {
        return sleepList[position]
    }

    /*
     * The parent class that handles layout inflation and child view use
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var lenView = itemView.findViewById<ImageView>(R.id.length_icon)
        var dreamView = itemView.findViewById<ImageView>(R.id.dream_icon)
        var txtView = itemView.findViewById<TextView>(R.id.sleep_description)
    }
}