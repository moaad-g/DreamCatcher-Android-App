package com.example.coursework2

import android.R.attr.button
import android.content.Intent
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
import android.view.Menu
import java.util.Date


class AddSleep : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_previous_night)
        setSupportActionBar(findViewById(R.id.app_bar))

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
        fun backToHome(view : View){
            val newIntent = Intent(this,LandingPage::class.java);
            startActivity(newIntent);
        }

        saveButton.setOnClickListener {
            val dreamTextVal = dreamText.text.toString()
            var chunkTime = 0;
            if (hourTime.text.isNotEmpty()){
                chunkTime += (parseInt(hourTime.text.toString())*60)
            }
            if (minuteTime.text.isNotEmpty()){
                chunkTime += (parseInt(minuteTime.text.toString()))
            }
            Log.d("Tag",dreamRating.progress.toString())
            Log.d("Tag",dreamDate.date.toString())
            val dreamDateMillis = dreamDate.date
            val dreamDateString = DateFormat.format("yyyy-MM-dd", Date(dreamDateMillis)).toString()
            Log.d("Tag", dreamDateString)

            db.addChunk(dreamTextVal, chunkTime , dreamDate.date , dreamRating.progress)
            backToHome(saveButton)

        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }
}
