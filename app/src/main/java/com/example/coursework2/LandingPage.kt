package com.example.coursework2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.coursework2.db.ChunkDatabase
import com.google.gson.internal.`$Gson$Types`.arrayOf
import java.io.OutputStreamWriter
import java.io.File
import java.io.FileOutputStream


class LandingPage : ToolbarBase() {
    private val db = ChunkDatabase(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_page)
        setSupportActionBar(findViewById(R.id.app_bar))
    }
    fun toSleepList(view : View){
        val newIntent = Intent(this,SleepHistory::class.java);
        startActivity(newIntent);
    }
    fun toNewChunk(view : View){
        val newIntent = Intent(this,AddSleep::class.java);
        startActivity(newIntent);
    }
}