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


class AddSleep : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_previous_night)
        val saveButton = findViewById<Button>(R.id.submit)
        val dreamText = findViewById<EditText>(R.id.dream_input)
        val dreamDate = findViewById<CalendarView>(R.id.select_date)
        val dreamRating = findViewById<SeekBar>(R.id.seekBar)
        val hourTime = findViewById<EditText>(R.id.getHours)
        val minuteTime = findViewById<EditText>(R.id.getMins)
        val db = ChunkDatabase(this);
        saveButton.isEnabled = false


        minuteTime.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                // Check if the entered value is above 60 and limit it
                if ((s.toString().toIntOrNull() ?: 0) > 60) {
                    minuteTime.setText("")
                    minuteTime.setSelection(minuteTime.text.length)
                }
                saveButton.isEnabled = hourTime.text.isNotEmpty() || minuteTime.text.isNotEmpty()
            }
        })

        hourTime.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                saveButton.isEnabled = hourTime.text.isNotEmpty() || minuteTime.text.isNotEmpty()
            }
        })

        saveButton.setOnClickListener {
            val dt = dreamText.text.toString()
            if (hourTime.text.isNotEmpty())
            //val chunkTime = (parseInt(hourTime.text.toString())*60)+(parseInt(minuteTime.text.toString()))
            Log.d("Tag","im here")
            db.addChunk(dt)
        }
    }
}
