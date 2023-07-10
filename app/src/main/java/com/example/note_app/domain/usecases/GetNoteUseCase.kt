package com.example.note_app.domain.usecases

import com.example.note_app.domain.models.Note
import com.example.note_app.domain.repositories.NoteRepository
import com.example.note_app.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {

    suspend fun getNote(): Flow<Resource<List<Note>>> {
        return noteRepository.getNote()
    }
}