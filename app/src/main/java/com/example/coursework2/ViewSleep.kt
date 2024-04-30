package com.example.coursework2

import android.R.attr.label
import android.R.attr.text
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.coursework2.db.ChunkDatabase
import com.google.gson.Gson
import java.util.Date


class ViewSleep : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_edit_chunk)

        val db = ChunkDatabase(this);
        val sleepId = intent.getIntExtra("sleepId", 1)
        var sleepObj = db.getChunkByID(sleepId)

        val editChunkButton = findViewById<Button>(R.id.chunk_edit_button)
        val editDateButton = findViewById<Button>(R.id.date_edit_button)
        val description = findViewById<TextView>(R.id.show_desc)
        val hours = findViewById<TextView>(R.id.show_hours)
        val mins = findViewById<TextView>(R.id.show_mins)
        val date = findViewById<TextView>(R.id.date_text_view)
        val copyButton = findViewById<Button>(R.id.copy_JSON)

        if (sleepObj != null) {
            var hourval = sleepObj.sleepTime/60
            var minuteval = sleepObj.sleepTime - hourval * 60
            description.text = sleepObj.dreamtext
            date.text = DateFormat.format("yyyy-MM-dd", Date(sleepObj.date)).toString()
            mins.text = minuteval.toString()
            hours.text = hourval.toString()
        }

        editChunkButton.setOnClickListener {
            EditFragment(sleepId).show(supportFragmentManager, "EditChunk")
        }

        copyButton.setOnClickListener {
            val gson = Gson()
            val chunkData = gson.toJson(sleepObj).toString()
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val chunkJson = ClipData.newPlainText("Sleep Data",chunkData )
            clipboard.setPrimaryClip(chunkJson)
        }



    }
}