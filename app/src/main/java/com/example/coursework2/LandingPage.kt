package com.example.coursework2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.coursework2.db.ChunkDatabase


class LandingPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_page)
        setSupportActionBar(findViewById(R.id.app_bar))
        val db = ChunkDatabase(this);


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return true
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