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
        setContentView(R.layout.view_edit_chunk)

        val intent = intent

        // Get the data from the intent
        val intentData: Uri? = intent.data

        if (intentData != null && intentData.scheme == "https" && intentData.host == "drmctchr.com" && intentData.path == "/viewsleep") {
            // Parse the query parameters from the URI
            val username: String? = intentData.getQueryParameter("username")
            val date: String? = intentData.getQueryParameter("date")
            val hours: String? = intentData.getQueryParameter("hours")
            val mins: String? = intentData.getQueryParameter("mins")
            val rat: String? = intentData.getQueryParameter("rat")
            val txt: String? = intentData.getQueryParameter("txt")

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