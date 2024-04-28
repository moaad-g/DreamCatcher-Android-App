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
import java.util.Date


class ViewSleep : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_edit_chunk)
    }
}