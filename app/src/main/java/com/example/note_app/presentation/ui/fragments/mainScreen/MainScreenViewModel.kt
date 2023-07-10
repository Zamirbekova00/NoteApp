package com.example.note_app.presentation.ui.fragments.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note_app.domain.models.Note
import com.example.note_app.domain.usecases.AddNoteUseCase
import com.example.note_app.domain.usecases.GetNoteUseCase
import com.example.note_app.domain.utils.Resource
import com.example.note_app.presentation.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getNoteUseCase: GetNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase
) : ViewModel() {

    private val _getAllNoteStates = MutableStateFlow<UiState<List<Note>>>(UiState.EmptyState())
    val getAllNoteStates = _getAllNoteStates.asStateFlow()

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            addNoteUseCase.addNote(note)
        }
    }

    fun getAllNote() {
        viewModelScope.launch {
            getNoteUseCase.getNote().collect() {
                when (it) {
                    is Resource.Loading -> {
                        _getAllNoteStates.value = UiState.Loading()
                    }
                    is Resource.Success -> {
                        _getAllNoteStates.value = UiState.Success(it.data as List<Note>)
                    }
                    is Resource.Error -> {
                        _getAllNoteStates.value = UiState.Error(it.message ?: "")
                    }
                }
            }
        }
    }
}