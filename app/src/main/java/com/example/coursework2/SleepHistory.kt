package com.example.coursework2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework2.db.ChunkDatabase
import kotlin.math.log

class SleepHistory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sleep_history)
        val db = ChunkDatabase(this);
        val sleepList = db.readChunks()
        print(sleepList)
        val recyclerView = findViewById<RecyclerView>(R.id.sleep_recycler)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = SleepListAdapter(sleepList)
        recyclerView.adapter = adapter
    }
}


