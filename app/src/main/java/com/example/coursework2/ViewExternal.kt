package com.example.coursework2

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.text.format.DateFormat
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.coursework2.db.ChunkDatabase
import com.google.gson.Gson
import java.io.File
import java.util.Date

class ViewExternal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_edit_chunk)

        val intent = getIntent()
        val intent_action = intent.action
        val intent_data = intent.data

        var sleepObj = SleepModel();



        val editChunkButton = findViewById<Button>(R.id.chunk_edit_button)
        val interpretButton = findViewById<Button>(R.id.interpret)
        val shareButton = findViewById<Button>(R.id.share)

        val description = findViewById<TextView>(R.id.show_desc)
        val hours = findViewById<TextView>(R.id.show_hours)
        val mins = findViewById<TextView>(R.id.show_mins)
        val date = findViewById<TextView>(R.id.date_text_view)
        val copyButton = findViewById<Button>(R.id.copy_JSON)
        val imageView = findViewById<ImageView>(R.id.image)
        val webView = findViewById<WebView>(R.id.dream_interpreter)
        val dreamDateMillis = sleepObj.date


    } 
}