package com.example.note_app.data.mappers

import com.example.note_app.data.models.NoteEntity
import com.example.note_app.domain.models.Note

fun Note.toEntity(): NoteEntity {
    return NoteEntity(id, tittle, description)
}

fun NoteEntity.toNote(): Note {
    return Note(id, tittle, description)
}