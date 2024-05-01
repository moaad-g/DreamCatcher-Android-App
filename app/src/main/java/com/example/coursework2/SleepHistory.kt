package com.example.coursework2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework2.db.ChunkDatabase
import kotlin.math.log

class SleepHistory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sleep_history)
        val db = ChunkDatabase(this);
        var sleepList = db.readChunks()
        print(sleepList)
        val recyclerView = findViewById<RecyclerView>(R.id.sleep_recycler)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        var adapter = SleepListAdapter(sleepList)
        recyclerView.adapter = adapter


        val sleepSpinner = findViewById<Spinner>(R.id.sort_sleep)

        sleepSpinner.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> sleepList =
                        sleepList.sortedByDescending { it.date }.toMutableList()
                    1 -> sleepList =
                        sleepList.sortedByDescending { it.rating }.toMutableList()
                    2 -> sleepList =
                        sleepList.sortedByDescending { it.sleepTime }.toMutableList()
                    3 -> sleepList =
                        sleepList.sortedByDescending { it.id }.toMutableList()
                }
                adapter = SleepListAdapter(sleepList)
                recyclerView.adapter = adapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

    }

}


