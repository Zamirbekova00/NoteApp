package com.example.note_app.domain.usecases

import com.example.note_app.domain.models.Note
import com.example.note_app.domain.repositories.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {

    suspend fun addNote(note: Note){
        noteRepository.addNote(note)
    }
}