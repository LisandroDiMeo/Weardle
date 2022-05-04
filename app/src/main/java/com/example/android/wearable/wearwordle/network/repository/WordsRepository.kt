package com.example.android.wearable.wearwordle.network.repository

import com.example.android.wearable.wearwordle.database.daos.WordDao
import com.example.android.wearable.wearwordle.database.entities.Word
import com.example.android.wearable.wearwordle.presentation.viewmodels.symbols.Language
import kotlinx.coroutines.flow.Flow
import kotlin.reflect.full.createInstance

class WordsRepository constructor(private val wordDao: WordDao) {

    fun fetchRandomWord(language: Language) = WordsTable().tableFrom(language).fetchWord(this)

    fun fetchRandomWordEnglish() = wordDao.findEnglishWordsById()

    fun fetchRandomWordSpanish() = wordDao.findSpanishWordsById()

    fun fetchRandomWordFrench() = wordDao.findFrenchWordsById()

    fun tableSize(language: Language) = WordsTable().tableFrom(language).tableSize(this)

    fun frenchWordsTableSize() = wordDao.getFrenchWordsCount()

    fun spanishWordsTableSize() = wordDao.getSpanishWordsCount()

    fun englishWordsTableSize() = wordDao.getEnglishWordsCount()


}

class WordsTable {
    fun tableFrom(language: Language) : Table =
        WordsTable::class.nestedClasses.first { subclass -> (subclass.createInstance() as Table).canHandle(language) } .createInstance() as Table

    abstract class Table {
        open fun canHandle(language: Language): Boolean = false
        abstract fun fetchWord(repository: WordsRepository): Flow<Word>
        abstract fun tableSize(repository: WordsRepository): Flow<Int>
    }

    class EnglishWords: Table(){
        override fun canHandle(language: Language): Boolean = language == Language.EN
        override fun fetchWord(repository: WordsRepository): Flow<Word> =
            repository.fetchRandomWordEnglish()

        override fun tableSize(repository: WordsRepository): Flow<Int> =
            repository.englishWordsTableSize()
    }

    class SpanishWords: Table(){
        override fun canHandle(language: Language): Boolean = language == Language.SP
        override fun fetchWord(repository: WordsRepository): Flow<Word> =
            repository.fetchRandomWordSpanish()
        override fun tableSize(repository: WordsRepository): Flow<Int> =
            repository.spanishWordsTableSize()
    }

    class FrenchWords: Table(){
        override fun canHandle(language: Language): Boolean = language == Language.FR
        override fun fetchWord(repository: WordsRepository): Flow<Word> =
            repository.fetchRandomWordFrench()
        override fun tableSize(repository: WordsRepository): Flow<Int> =
            repository.frenchWordsTableSize()
    }

}

