package com.example.note_app.presentation.ui.fragments.mainScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.note_app.databinding.ItemNotesBinding
import com.example.note_app.domain.models.Note

class MainScreenAdapter(private val onClick: (note: Note) -> Unit) :
    Adapter<MainScreenAdapter.MainScreenViewHolder>() {

    private val list = arrayListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenViewHolder {
        return MainScreenViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun addNotes(note: List<Note>) {
        list.clear()
        list.addAll(note)
        notifyDataSetChanged()
    }

    fun getNotes(position: Int): Note {
        return list[position]
    }

    override fun onBindViewHolder(holder: MainScreenViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class MainScreenViewHolder(private val binding: ItemNotesBinding) :
        ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.apply {
                tvTittle.text = note.tittle
                tvDescription.text = note.description
            }
            itemView.setOnClickListener {
                onClick(getNotes(adapterPosition))
            }
        }
    }
}