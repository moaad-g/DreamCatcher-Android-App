package com.example.coursework2

import android.R.attr.label
import android.R.attr.text
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.text.format.DateFormat
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.coursework2.db.ChunkDatabase
import com.google.gson.Gson
import java.io.File
import java.util.Date
import java.net.URLEncoder




class ViewSleep : ToolbarBase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_edit_chunk)
        setSupportActionBar(findViewById(R.id.app_bar))

        val db = ChunkDatabase(this);
        val sleepId = intent.getIntExtra("sleepId", 1)
        var sleepObj = db.getChunkByID(sleepId)

        val editChunkButton = findViewById<Button>(R.id.chunk_edit_button)
        val interpretButton = findViewById<Button>(R.id.interpret)
        val shareButton = findViewById<Button>(R.id.share)

        val description = findViewById<TextView>(R.id.show_desc)
        val hours = findViewById<TextView>(R.id.show_hours)
        val mins = findViewById<TextView>(R.id.show_mins)
        val date = findViewById<TextView>(R.id.date_text_view)
        val copyButton = findViewById<Button>(R.id.copy_JSON)
        val imageView = findViewById<ImageView>(R.id.image)
        val dreamDateMillis = sleepObj?.date

        if (sleepObj != null) {
            var hourval = sleepObj.sleepTime / 60
            var minuteval = sleepObj.sleepTime - hourval * 60
            description.text = sleepObj.dreamtext
            date.text = DateFormat.format("yyyy-MM-dd", Date(sleepObj.date)).toString()
            mins.text = minuteval.toString()
            hours.text = hourval.toString()
            if (sleepObj.hasImg) {
                val imageFolder =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                val file = File(imageFolder, "$dreamDateMillis.jpg")
                if (file.exists()) {
                    imageView.setImageBitmap(BitmapFactory.decodeFile(file.absolutePath))
                }
            }
        }

        editChunkButton.setOnClickListener {
            EditFragment(sleepId).show(supportFragmentManager, "EditChunk")
        }

        shareButton.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND_MULTIPLE
            if (sleepObj != null) {
                shareIntent.putExtra(Intent.EXTRA_TEXT, createShareText(sleepObj))
                if (sleepObj.hasImg) {
                    val imageFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    val file = File(imageFolder, "$dreamDateMillis.jpg")
                    val uriList = ArrayList<Uri>()
                    val uri = FileProvider.getUriForFile(this, "${packageName}.fileprovider", file)
                    uriList.add(uri)
                    if (file.exists()) {
                        shareIntent.putExtra(Intent.EXTRA_STREAM, uriList)
                    }
                }
            }
            shareIntent.type = "image/*"
            startActivity(Intent.createChooser(shareIntent, "send to"))
        }

        interpretButton.setOnClickListener {
            val webpage: Uri = Uri.parse("https://dreaminterpreter.ai/")
            val intent = Intent(Intent.ACTION_VIEW, webpage)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

        copyButton.setOnClickListener {
            val gson = Gson()
            val chunkData = gson.toJson(sleepObj).toString()
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val chunkJson = ClipData.newPlainText("Sleep Data", chunkData)
            clipboard.setPrimaryClip(chunkJson)
        }

    }

    fun createShareText(sleepObj: SleepModel): String {
        var shareText = ""
        var baseUrl = "https://drmctchr.com/viewsleep?"
        var hours = sleepObj.sleepTime / 60
        var minutes = sleepObj.sleepTime - hours * 60
        val dateString = DateFormat.format("yyyy-MM-dd", Date(sleepObj.date)).toString()
        val sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "not_set")
        if (username != "not_set") {
            shareText += "Take a look at $username's sleep!\n"
            baseUrl += "username=$username&"
        } else {
            shareText += "Take a look at this sleep!\n"
            baseUrl += "username=User&"
        }
        baseUrl += "date=$dateString&hours=$hours&mins=$minutes&rat=${sleepObj.rating}&txt=${URLEncoder.encode(sleepObj.dreamtext, "UTF-8")}"


        shareText += "On $dateString I slept for $hours Hours and $minutes \n " +
                "I gave this sleep a rating of ${sleepObj.rating}/100. \n" +
                "This is a description of my dream: \n ${sleepObj.dreamtext} \n"+"\n$baseUrl\n"


        return shareText
    }
}
