package com.example.notekeeper

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class notesRepository(private val notesDao: notesDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allnotes: LiveData<List<notes>> = notesDao.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.

    suspend fun insert(notes: notes) {
        notesDao.insert(notes)
    }

    suspend fun delete(notes:notes){
        notesDao.delete(notes)
    }
}
