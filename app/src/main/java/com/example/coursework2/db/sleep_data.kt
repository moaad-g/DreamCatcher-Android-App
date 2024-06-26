package com.example.coursework2.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.coursework2.SleepModel
import com.google.gson.Gson


const val DATABASE_VERSION = 7
private const val DATABASE_NAME = "sleepdb"
private const val CHUNKS_TABLE = "chunks"

private const val CHUNK_ID = "_id"
private const val COLUMN_DREAM_TEXT = "dreamtext"
private const val COLUMN_DREAM_RATING = "rating"
private const val COLUMN_DREAM_DATE = "datetime"
private const val COLUMN_HAS_IMAGE = "img"
private const val CHUNK_TIME = "chunk"
class ChunkDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    //Initialise database table
    override fun onCreate(db: SQLiteDatabase) {
        //here's some raw SQL
        val CREATE_CHUNKS_TABLE = "CREATE TABLE $CHUNKS_TABLE($CHUNK_ID INTEGER PRIMARY KEY,"+
                "$COLUMN_DREAM_TEXT TEXT," +
                "$COLUMN_DREAM_RATING INTEGER," +
                "$COLUMN_DREAM_DATE INTEGER," +
                "$CHUNK_TIME INTEGER," +
                "$COLUMN_HAS_IMAGE INTEGER)"
        db.execSQL(CREATE_CHUNKS_TABLE)
    }

    //If we need to change the database schema
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $CHUNKS_TABLE")
        onCreate(db)
    }

    fun readChunks(): MutableList<SleepModel>{
        val chunkList = arrayListOf<SleepModel>()

        val db = this.readableDatabase
        val query = "select * from $CHUNKS_TABLE"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val id = Integer.parseInt(cursor.getString(0))
                val text = cursor.getString(1)
                val rating = cursor.getInt(2)
                val datet = cursor.getLong(3)
                val chunkt = cursor.getInt(4)
                val newSleep = SleepModel()
                newSleep.dreamtext = text
                newSleep.rating = rating
                newSleep.date = datet
                newSleep.sleepTime = chunkt
                newSleep.id = id
                chunkList.add(newSleep)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return chunkList;
    }

    fun exportChunks(): String? {
        val chunkList = arrayListOf<SleepModel>()
        val gson = Gson()

        val db = this.readableDatabase
        val query = "select * from $CHUNKS_TABLE limit 100"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val id = Integer.parseInt(cursor.getString(0))
                val text = cursor.getString(1)
                val rating = cursor.getInt(2)
                val datet = cursor.getLong(3)
                val chunkt = cursor.getInt(4)
                val newSleep = SleepModel()
                newSleep.dreamtext = text
                newSleep.rating = rating
                newSleep.date = datet
                newSleep.sleepTime = chunkt
                newSleep.id = id
                chunkList.add(newSleep)
            } while (cursor.moveToNext())
        }
        cursor.close()
        val chunksJSON = gson.toJson(chunkList)

        return chunksJSON

    }

    fun addChunk(dreamText: String, chunkTime: Int, dreamDate: Long, dreamRating: Int, hasImg: Boolean) {
        //Inserting values requires a wrapping
        val values = ContentValues()
        var imgInt = 0

        if (hasImg) {
            imgInt = 1
        }
        values.put(CHUNK_TIME, chunkTime)
        values.put(COLUMN_DREAM_RATING, dreamRating)
        values.put(COLUMN_DREAM_DATE, dreamDate)
        values.put(COLUMN_DREAM_TEXT, dreamText)
        values.put(COLUMN_HAS_IMAGE, imgInt)
        val db = this.writableDatabase

        db.insert(CHUNKS_TABLE, null, values)
    }

    fun editChunkTime(id: Int, chunkTime: Int) {
        val values = ContentValues()
        values.put(CHUNK_TIME, chunkTime)

        val db = this.writableDatabase
        db.update(CHUNKS_TABLE,values,"$CHUNK_ID = ?", arrayOf(id.toString()))
    }

    fun editChunkText(id: Int, chunkText: String) {
        val values = ContentValues()
        values.put(COLUMN_DREAM_TEXT, chunkText)
        val db = this.writableDatabase
        db.update(CHUNKS_TABLE,values,"$CHUNK_ID = ?", arrayOf(id.toString()))
    }
    fun getChunkByID(id: Int) : SleepModel? {
        //Inserting values requires a wrapping
        val query = "select * from $CHUNKS_TABLE where $CHUNK_ID == $id"
        val db = this.readableDatabase
        val newSleep = SleepModel()
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            newSleep.id = Integer.parseInt(cursor.getString(0))
            newSleep.dreamtext =  cursor.getString(1)
            newSleep.rating = cursor.getInt(2)
            newSleep.date = cursor.getLong(3)
            newSleep.sleepTime = cursor.getInt(4)
            val imgInt = cursor.getInt(5)
            if (imgInt == 1){
                newSleep.hasImg= true
            } else {
                newSleep.hasImg= false
            }
            cursor.close()
            return newSleep
        }
        cursor.close()
        return null
    }
}

