package com.example.android.wearable.wearwordle.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.wearable.wearwordle.database.entities.EnglishWord
import com.example.android.wearable.wearwordle.database.entities.FrenchWord
import com.example.android.wearable.wearwordle.database.entities.SpanishWord
import com.example.android.wearable.wearwordle.database.entities.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("SELECT * FROM words_en ORDER BY  random() LIMIT 1")
    fun findEnglishWordsById(): Flow<EnglishWord>

    @Query("SELECT * FROM words_sp ORDER BY  random() LIMIT 1")
    fun findSpanishWordsById(): Flow<SpanishWord>

    @Query("SELECT * FROM words_fr ORDER BY  random() LIMIT 1")
    fun findFrenchWordsById(): Flow<FrenchWord>

    @Query("SELECT COUNT(*) FROM words_fr")
    fun getFrenchWordsCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM words_sp")
    fun getSpanishWordsCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM words_en")
    fun getEnglishWordsCount(): Flow<Int>

    @Query("SELECT * FROM words_en")
    fun getAllEnglishWords(): List<Word>

}
