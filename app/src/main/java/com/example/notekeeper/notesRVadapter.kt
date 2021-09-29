package com.example.notekeeper

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class notesRVadapter(private val context:Context,private val listener: inotesRvadapter): RecyclerView.Adapter<notesRVadapter.notesviewholder>() {

    val allnotes = ArrayList<notes>()

    inner class notesviewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val text = itemView.findViewById<TextView>(R.id.text)
        val delete = itemView.findViewById<ImageView>(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesviewholder {
        val currviewholder = notesviewholder(LayoutInflater.from(context).inflate(R.layout.items_notes,parent,false))
        currviewholder.delete.setOnClickListener{
            listener.onitemclicked(allnotes[currviewholder.adapterPosition])
        }

        return currviewholder
    }

    override fun onBindViewHolder(holder: notesviewholder, position: Int) {
        val curr_notes = allnotes[position]
        holder.text.text = curr_notes.text

    }

    override fun getItemCount(): Int {
        return allnotes.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun update(newlist:List<notes>){
        allnotes.clear()
        allnotes.addAll(newlist)
        notifyDataSetChanged()
    }

}
interface inotesRvadapter{

    fun onitemclicked(notes: notes)
}