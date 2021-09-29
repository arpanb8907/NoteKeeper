package com.example.notekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.items_notes.*

class MainActivity : AppCompatActivity(), inotesRvadapter {
    lateinit var viewmodel: notesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = notesRVadapter(this,this)
        recyclerview.adapter = adapter

        viewmodel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(notesViewModel::class.java)

        viewmodel.allnotes.observe(this, Observer { list->list?.let{
            adapter.update(it)
        }

        })
    }

    override fun onitemclicked(notes: notes) {
        viewmodel.delete(notes)
    }

    fun submit(view: View) {
        val note = input.text.toString()
        if(note.isNotEmpty()){
            viewmodel.insert(notes(note))
        }
    }


}