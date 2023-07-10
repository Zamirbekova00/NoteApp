package com.example.note_app.domain.repositories

import com.example.note_app.domain.models.Note
import com.example.note_app.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun addNote(note: Note)

    fun getNote(): Flow<Resource<List<Note>>>

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)
}