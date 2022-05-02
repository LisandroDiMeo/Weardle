package com.example.android.wearable.wearwordle.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.wearable.wearwordle.database.daos.WordDao
import com.example.android.wearable.wearwordle.database.entities.EnglishWord
import com.example.android.wearable.wearwordle.database.entities.FrenchWord
import com.example.android.wearable.wearwordle.database.entities.SpanishWord
import com.example.android.wearable.wearwordle.database.entities.Word

@Database(entities = [Word::class, EnglishWord::class, FrenchWord::class, SpanishWord::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordsDao(): WordDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        val database : (context: Context) -> AppDatabase = { appContext ->
            if(instance == null){
                synchronized(this){
                    instance = Room
                        .databaseBuilder(appContext, AppDatabase::class.java, "data")
                        .createFromAsset(DATABASE_FILE)
                        .build()
                }
            }
            instance!!
        }

        private const val DATABASE_FILE = "database/data.sqlite"
    }
}
