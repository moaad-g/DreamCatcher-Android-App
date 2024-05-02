package com.example.coursework2

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.coursework2.db.ChunkDatabase
import java.lang.Integer.parseInt

class EditFragment (chunkId: Int) : DialogFragment() {

    interface EditFragmentListener {
        fun onUpdateTime(hour: Int, minute: Int)
        fun onUpdateText(text:String)
    }

    var listener: EditFragmentListener? = null

    val newID = chunkId;
    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = when (tag) {
                "Time" -> inflater.inflate(R.layout.edit_chunk, null)
                "Text" -> inflater.inflate(R.layout.edit_text, null)
                else -> inflater.inflate(R.layout.edit_chunk, null)
            }
            view.findViewById<Button>(R.id.submit_new_chunk).setOnClickListener {
                when (tag) {
                    "Time" -> changeTime(view , newID)
                    "Text" -> changeDream(view, newID)
                    else -> changeTime(view, newID)
                }
            }
            builder.setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun changeTime(view:View , chunkId: Int){
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
                if (db != null) {
                    db.editChunkTime(chunkId , chunkTime)
                    val toast = Toast.makeText(this.context, "Time Change Saved", Toast.LENGTH_SHORT)
                    toast.show()
                    (activity as? EditFragmentListener)?.onUpdateTime(chunkTime / 60, chunkTime % 60)
                    this.dismiss()
                }
            }

        } else {
            val toast = Toast.makeText(this.context, "Please Input a Time", Toast.LENGTH_SHORT)
            toast.show()

        }
    }
    private fun changeDream(view:View , chunkId: Int){
        val dreamText = view.findViewById<EditText>(R.id.dream_input)
        val db = this.context?.let { ChunkDatabase(it) };
        if (dreamText.text.isNotEmpty()){
            if (db != null) {
                db.editChunkText(chunkId , dreamText.text.toString())
                val toast = Toast.makeText(this.context, "Dream data saved", Toast.LENGTH_SHORT)
                toast.show()
                (activity as? EditFragmentListener)?.onUpdateText(dreamText.text.toString())
                this.dismiss()
            }
        } else {
            val toast = Toast.makeText(this.context, "Please Input Text", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}