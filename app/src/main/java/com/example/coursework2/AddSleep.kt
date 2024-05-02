package com.example.coursework2

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.coursework2.db.ChunkDatabase
import java.lang.Integer.parseInt
import android.view.Menu
import android.widget.ImageView
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.Calendar


class AddSleep : ToolbarBase() {

    private var dreamImage : Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //get layout and buttons
        setContentView(R.layout.add_previous_night)
        setSupportActionBar(findViewById(R.id.app_bar))

        val saveButton = findViewById<Button>(R.id.submit)
        val imageButton = findViewById<Button>(R.id.take_photo)

        val dreamText = findViewById<EditText>(R.id.dream_input)
        val dreamDate = findViewById<CalendarView>(R.id.select_date)
        val dreamRating = findViewById<SeekBar>(R.id.seekBar)
        val hourTime = findViewById<EditText>(R.id.getHours)
        val minuteTime = findViewById<EditText>(R.id.getMins)
        val db = ChunkDatabase(this);
        saveButton.isEnabled = false
        var dreamDateMillis = dreamDate.date

        //check minutes is set and <60
        minuteTime.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if ((s.toString().toIntOrNull() ?: 0) > 59) {
                    minuteTime.setText("")
                    minuteTime.setSelection(minuteTime.text.length)
                }
                saveButton.isEnabled = hourTime.text.isNotEmpty() || minuteTime.text.isNotEmpty()
            }
        })

        //check hours is set
        hourTime.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                saveButton.isEnabled = hourTime.text.isNotEmpty() || minuteTime.text.isNotEmpty()
            }
        })


        fun backToHome(view : View){
            val newIntent = Intent(this,LandingPage::class.java);
            startActivity(newIntent);
        }

        dreamDate.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            dreamDateMillis = calendar.timeInMillis
        }

        //save the data
        saveButton.setOnClickListener {
            var hasImg = false
            val dreamTextVal = dreamText.text.toString()
            var chunkTime = 0;
            if (hourTime.text.isNotEmpty()){
                chunkTime += (parseInt(hourTime.text.toString())*60)
            }
            if (minuteTime.text.isNotEmpty()){
                chunkTime += (parseInt(minuteTime.text.toString()))
            }

            //check if image exists, if yes then save
            if (dreamImage != null){
                val dreamImageComp = ByteArrayOutputStream()
                dreamImage!!.compress(Bitmap.CompressFormat.JPEG , 100, dreamImageComp )
                val imageFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                val file = File(imageFolder, "$dreamDateMillis.jpg")
                val fileOutputStream = FileOutputStream(file)
                fileOutputStream.write(dreamImageComp.toByteArray())
                hasImg = true
            }


            db.addChunk(dreamTextVal, chunkTime , dreamDateMillis , dreamRating.progress , hasImg)
            backToHome(saveButton)

        }

        imageButton.setOnClickListener{
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, 1)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val imageView = findViewById<ImageView>(R.id.image)
            dreamImage = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(dreamImage)
        }
    }
}
