package com.example.note_app.data.repositories

import com.example.note_app.data.local.NoteDao
import com.example.note_app.data.mappers.toEntity
import com.example.note_app.data.mappers.toNote
import com.example.note_app.domain.models.Note
import com.example.note_app.domain.repositories.NoteRepository
import com.example.note_app.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {

    override suspend fun addNote(note: Note) {
        noteDao.addNote(note.toEntity())
    }

    override fun getNote(): Flow<Resource<List<Note>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val list = noteDao.getNote()
                val data = list.map { it.toNote() }
                emit(Resource.Success(data))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note.toEntity())
    }
}