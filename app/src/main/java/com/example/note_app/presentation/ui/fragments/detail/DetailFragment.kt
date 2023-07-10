package com.example.note_app.presentation.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.note_app.R
import com.example.note_app.databinding.FragmentDetailBinding
import com.example.note_app.domain.models.Note
import com.example.note_app.presentation.ui.fragments.mainScreen.MainScreenFragment.Companion.KEY_NOTES
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNotes()
    }

    private fun getNotes() {
        binding.apply {
            val note = (arguments?.getSerializable(KEY_NOTES)) as Note
            btnUpdate.setOnClickListener {
                viewModel.viewModelScope.launch {
                    viewModel.updateNote(
                        Note(
                            id = note.id,
                            tittle = binding.etTittle.toString(),
                            description = binding.etDescription.toString()
                        )
                    )
                }
                findNavController().navigateUp()
            }
            btnDelete.setOnClickListener {
                viewModel.viewModelScope.launch {
                    viewModel.deleteNote(note)
                }
                findNavController().navigateUp()
            }
        }
    }
}