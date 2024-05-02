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
import android.util.Log
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
        setContentView(R.layout.view_external)
        setSupportActionBar(findViewById(R.id.app_bar))

        val interpretButton = findViewById<Button>(R.id.interpret)
        val shareButton = findViewById<Button>(R.id.share)

        val description = findViewById<TextView>(R.id.show_desc)
        val hours = findViewById<TextView>(R.id.show_hours)
        val title = findViewById<TextView>(R.id.view_title)
        val mins = findViewById<TextView>(R.id.show_mins)
        val date = findViewById<TextView>(R.id.date_text_view)
        val sleepQual = findViewById<TextView>(R.id.sleep_quality_text)
        val copyButton = findViewById<Button>(R.id.copy_JSON)

        val intent = intent
        val intentData: Uri? = intent.data

        if (intentData != null && intentData.scheme == "https" && intentData.host == "drmctchr.com" && intentData.path == "/viewsleep") {
            // Parse the query parameters from the URI
            val username = intentData.getQueryParameter("username")
            val datein= intentData.getQueryParameter("date")
            val hoursin = intentData.getQueryParameter("hours")
            val minsin = intentData.getQueryParameter("mins")
            val rat= intentData.getQueryParameter("rat")
            val txt= intentData.getQueryParameter("txt")

            val ratText = rat.toString()+"/100"

            description.text = txt;
            date.text = datein
            hours.text = hoursin
            mins.text = minsin
            sleepQual.text = ratText

            if (username != "User" ){
                val newtitle = "$username 's Sleep Details"
                title.text = newtitle

            }



            // Now you have all the parameters, you can use them as needed
            Log.d("TAG", "Username: $username")
            Log.d("TAG", "Date: $date")
            Log.d("TAG", "Hours: $hours")
            Log.d("TAG", "Mins: $mins")
            Log.d("TAG", "Rat: $rat")
            Log.d("TAG", "Txt: $txt")
        } else {
            // Handle the case where the URI is not what you expected
            Log.d("TAG", "Invalid URI")
        }


    }
}