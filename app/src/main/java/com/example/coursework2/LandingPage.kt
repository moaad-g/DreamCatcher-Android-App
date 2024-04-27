package com.example.coursework2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class LandingPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_page)
    }

    fun toSleepList(view : View){
        val newIntent = Intent(this,SleepHistory::class.java);
        startActivity(newIntent);
    }
}