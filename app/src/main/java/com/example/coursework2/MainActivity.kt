package com.example.coursework2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
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

        val dreamImgList = arrayOf(R.drawable.green_smile , R.drawable.green_smile , R.drawable.red_smile,
            R.drawable.green_smile, R.drawable.red_smile ,R.drawable.red_smile , R.drawable.red_smile
        )
        val lenImgList = arrayOf(R.drawable.green_smile , R.drawable.green_smile , R.drawable.red_smile,
            R.drawable.green_smile, R.drawable.red_smile ,R.drawable.red_smile , R.drawable.red_smile
        )
        val descList = arrayOf("beans","beaaans")

        for (i in 0 ..6){
            val newSleep = SleepModel()
            newSleep.sleepDesc = descList[0]
            newSleep.dreamImg = dreamImgList[i]
            newSleep.lengthImg = lenImgList[i]
            list.add(newSleep)

        }

        return list

    }
}


