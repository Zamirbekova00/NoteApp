package com.example.note_app.data.local

import androidx.room.*
import com.example.note_app.data.models.NoteEntity

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM NOTE notes")
    suspend fun getNote():List<NoteEntity>

    @Query("SELECT * FROM notes ORDER BY note_id DESC")
    suspend fun getNotesFromLast():List<NoteEntity>

    @Query("SELECT * FROM notes ORDER BY notes_tittle ASC")
    suspend fun getNotesSortByName():List<NoteEntity>

    @Update
    suspend fun updateNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)
}