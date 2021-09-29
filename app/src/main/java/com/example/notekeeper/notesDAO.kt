package com.example.notekeeper

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface notesDao {

    @Query("Select * from notes_table order by id ASC")
    fun getAlphabetizedWords(): LiveData<List<notes>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: notes)

    @Delete
    suspend fun delete(notes:notes)
}
