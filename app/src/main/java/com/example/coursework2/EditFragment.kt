package com.example.coursework2

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.coursework2.db.ChunkDatabase
import java.lang.Integer.parseInt
import java.util.Date
import java.util.zip.Inflater

class EditFragment (chunkId: Int) : DialogFragment() {

    val newID = chunkId;
    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = when (tag) {
                "EditChunk" -> inflater.inflate(R.layout.edit_chunk, null)
                "EditDate" -> inflater.inflate(R.layout.edit_date, null)
                else -> inflater.inflate(R.layout.edit_chunk, null)
            }
            view.findViewById<Button>(R.id.submit_new_chunk).setOnClickListener {
                when (tag) {
                    "EditChunk" -> checkTime(view , newID)
                    "EditDate" -> checkTime(view, newID)
                    else -> checkTime(view, newID)
                }
            }
            builder.setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun checkTime(view:View , chunkId: Int){
        val minuteTime = view.findViewById<EditText>(R.id.getMins)
        val hourTime = view.findViewById<EditText>(R.id.getHours)
        val db = this.context?.let { ChunkDatabase(it) };

        if (hourTime.text.isNotEmpty() || minuteTime.text.isNotEmpty()){
            if (((minuteTime.text).toString().toIntOrNull() ?: 0) > 59){
                val toast = Toast.makeText(this.context, "Please Input a Valid Time", Toast.LENGTH_SHORT)
                toast.show()

            } else {
                var chunkTime = 0;
                if (hourTime.text.isNotEmpty()){
                    chunkTime += (parseInt(hourTime.text.toString())*60)
                }
                if (minuteTime.text.isNotEmpty()){
                    chunkTime += (parseInt(minuteTime.text.toString()))
                }
                Log.d("TAG", chunkId.toString())
                if (db != null) {
                    db.editChunkTime(chunkId , chunkTime)
                }

            }

        } else {
            val toast = Toast.makeText(this.context, "Please Input a Time", Toast.LENGTH_SHORT)
            toast.show()

        }



    }

}