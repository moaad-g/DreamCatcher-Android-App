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
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.coursework2.EditFragment
import com.example.coursework2.R
import com.google.gson.Gson
import java.io.File
import java.util.Date

class Settings : ToolbarBase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)
        setSupportActionBar(findViewById(R.id.app_bar))

        val setUsernameButton = findViewById<Button>(R.id.new_username)
        val newUsername = findViewById<EditText>(R.id.edit_username)


        setUsernameButton.setOnClickListener{
            if (newUsername.text.toString().isNotEmpty() && newUsername.text.toString() != "username" ){
                val sharedpreferences = getSharedPreferences("preferences", MODE_PRIVATE)
                val editor = sharedpreferences.edit()
                editor.putString("username", newUsername.text.toString())
                editor.apply()
                val toast = Toast.makeText(this, "Username Changed", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                val toast = Toast.makeText(this, "Please Set a Username", Toast.LENGTH_SHORT)
                toast.show()
            }


        }

    }
}