package com.example.coursework2

import android.R.attr.button
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.coursework2.db.ChunkDatabase
import java.lang.Integer.parseInt
import android.text.format.DateFormat
import android.widget.TextView
import java.util.Date


class ViewSleep : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_edit_chunk)

        val db = ChunkDatabase(this);
        val sleepId = intent.getIntExtra("sleepId", 1)
        Log.d("TAG", sleepId.toString())
        var sleepObj = db.getChunkByID(sleepId)

        val description = findViewById<TextView>(R.id.show_desc)
        val hours = findViewById<TextView>(R.id.show_hours)
        val mins = findViewById<TextView>(R.id.show_mins)
        val date = findViewById<TextView>(R.id.date_text_view)






    }
}