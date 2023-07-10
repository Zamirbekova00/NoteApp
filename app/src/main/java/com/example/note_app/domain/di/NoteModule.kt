package com.example.note_app.domain.di

import android.content.Context
import androidx.room.Room
import com.example.note_app.data.local.NoteDao
import com.example.note_app.data.local.NoteDataBase
import com.example.note_app.data.repositories.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {

    @Singleton
    @Provides
    fun provideDiaryDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        NoteDataBase::class.java,
        "note_db"
    )

    @Provides
    fun provideNoteDao(noteDataBase: NoteDataBase) = noteDataBase.noteDao()

    @Provides
    fun provideNoteRepository(noteDao: NoteDao):
            NoteRepositoryImpl = NoteRepositoryImpl(noteDao)
}