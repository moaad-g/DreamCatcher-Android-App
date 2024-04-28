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
        var sleepObj = db.getChunkByID(sleepId)

        val editChunkButton = findViewById<Button>(R.id.chunk_edit_button)
        val editDateButton = findViewById<Button>(R.id.date_edit_button)
        val description = findViewById<TextView>(R.id.show_desc)
        val hours = findViewById<TextView>(R.id.show_hours)
        val mins = findViewById<TextView>(R.id.show_mins)
        val date = findViewById<TextView>(R.id.date_text_view)

        if (sleepObj != null) {
            var hourval = sleepObj.sleepTime/60
            var minuteval = sleepObj.sleepTime - hourval * 60
            description.text = sleepObj.dreamtext
            date.text = DateFormat.format("yyyy-MM-dd", Date(sleepObj.date)).toString()
            mins.text = minuteval.toString()
            hours.text = hourval.toString()
        }

        editChunkButton.setOnClickListener {
            EditFragment(sleepId).show(supportFragmentManager, "EditChunk")
        }

        editDateButton.setOnClickListener {
            EditFragment(sleepId).show(supportFragmentManager, "EditDate")
        }

    }
}