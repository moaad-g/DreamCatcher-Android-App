package com.example.coursework2

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.coursework2.db.ChunkDatabase
import java.io.File
import java.io.FileOutputStream

open class ToolbarBase : AppCompatActivity() {
    private val db = ChunkDatabase(this)
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_page)
        setSupportActionBar(findViewById(R.id.app_bar))
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile_option -> {
                val newIntent = Intent(this,Settings::class.java);
                startActivity(newIntent);
                true
            }
            R.id.export_option -> {
                if (checkWritePermissions()) {
                    val chunkData = db.exportChunks()
                    val downloadsFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    val file = File(downloadsFolder, "chunkData.json")
                    val fileOutputStream = FileOutputStream(file)
                    if (chunkData != null) {
                        fileOutputStream.write(chunkData.toByteArray())
                    }
                    fileOutputStream.close()
                } else {
                    val toast = Toast.makeText(this, "noperms", Toast.LENGTH_SHORT)
                    toast.show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun checkWritePermissions(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                2
            )
            return false
        } else {
            return true;
        }
    }
}