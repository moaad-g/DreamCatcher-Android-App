package com.example.coursework2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class SleepHistory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sleep_history)
        setSupportActionBar(findViewById(R.id.app_bar))

        val sleepList = populaterecyclelist()
        val recyclerView = findViewById<RecyclerView>(R.id.sleep_recycler)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = SleepListAdapter(sleepList)
        recyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }


    private fun populaterecyclelist(): ArrayList<SleepModel> {
        val list = ArrayList<SleepModel>()

        val dreamImgList = arrayOf(R.drawable.green_smile , R.drawable.red_smile,
            R.drawable.orange_smile
        )
        val lenImgList = arrayOf(R.drawable.time_green , R.drawable.time_orange , R.drawable.time_red
        )
        val descList = arrayOf("This day you slept for "+Random.nextInt(0,8)+" hours and had a good dream","This day you slept for "+Random.nextInt(0,8)+" hours and had a bad dream")

        for (i in 0 ..15){
            val newSleep = SleepModel()
            newSleep.sleepDesc = descList[Random.nextInt(0,2)]
            newSleep.dreamImg = dreamImgList[Random.nextInt(0,3)]
            newSleep.lengthImg = lenImgList[Random.nextInt(0,3)]
            list.add(newSleep)

        }

        return list

    }
}


