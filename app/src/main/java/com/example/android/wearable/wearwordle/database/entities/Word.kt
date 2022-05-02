package com.example.android.wearable.wearwordle.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class Word(@PrimaryKey val uid: Int, @ColumnInfo(name = "word") val word: String?)

@Entity(tableName = "words_en")
class EnglishWord(uid: Int, word: String?): Word(uid, word)

@Entity(tableName = "words_sp")
class SpanishWord(uid: Int, word: String?): Word(uid, word)

@Entity(tableName = "words_fr")
class FrenchWord(uid: Int, word: String?): Word(uid, word)