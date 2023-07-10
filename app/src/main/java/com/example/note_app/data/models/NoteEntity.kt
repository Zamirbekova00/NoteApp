package com.example.note_app.data.models

import android.provider.ContactsContract.CommonDataKinds.Note.NOTE
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = NOTE)
data class NoteEntity(
    @PrimaryKey()
    @ColumnInfo(name = "note_id")
    val id: Int,
    @ColumnInfo(name = "note_tittle")
    val tittle: String,
    @ColumnInfo(name = "note_description")
    val description: String
) {
    companion object {
        const val NOTE = "note"
    }
}

