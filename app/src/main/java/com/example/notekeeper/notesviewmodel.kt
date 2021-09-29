package com.example.notekeeper

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class notesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository:notesRepository
    val allnotes : LiveData<List<notes>>
    init {
        val dao = notesRoomDatabase.getDatabase(application).notesDao()
        repository = notesRepository(dao)
        allnotes = repository.allnotes
    }

    fun delete(notes:notes)=viewModelScope.launch (Dispatchers.IO){
        repository.delete(notes)
    }

    fun insert(notes:notes)=viewModelScope.launch (Dispatchers.IO){
        repository.insert(notes)
    }





}
