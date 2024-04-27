package com.example.coursework2.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.coursework2.SleepModel

const val DATABASE_VERSION = 2
private const val DATABASE_NAME = "sleepdb"
private const val CHUNKS_TABLE = "chunks"

private const val CHUNK_ID = "_id"
private const val COLUMN_DREAM_TEXT = "dreamtext"
private const val COLUMN_DREAM_RATING = "rating"
private const val COLUMN_DREAM_DATE = "datetime"
private const val VN_PATH = "vnpath"
private const val CHUNK_TIME = "chunk"
class ChunkDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    //Initialise database table
    override fun onCreate(db: SQLiteDatabase) {
        //here's some raw SQL
        val CREATE_CHUNKS_TABLE = "CREATE TABLE $CHUNKS_TABLE($CHUNK_ID INTEGER PRIMARY KEY,"+
                "$COLUMN_DREAM_TEXT TEXT," +
                "$COLUMN_DREAM_RATING INTEGER," +
                "$COLUMN_DREAM_DATE DATE," +
                "$CHUNK_TIME INTEGER," +
                "$VN_PATH INTEGER)"
        db.execSQL(CREATE_CHUNKS_TABLE)
        print("here")
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
                val newSleep = SleepModel()
                newSleep.dreamtext = text
                chunkList.add(newSleep)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return chunkList;
    }

    fun addChunk(text: String) {
        //Inserting values requires a wrapping
        val values = ContentValues()

        //of course, it's key-value pairs
        values.put(COLUMN_DREAM_TEXT, text)
        val db = this.writableDatabase

        //pop it in, and don't worry about the nullColumnHack, it's probably fine
        db.insert(CHUNKS_TABLE, null, values)
    }
}
