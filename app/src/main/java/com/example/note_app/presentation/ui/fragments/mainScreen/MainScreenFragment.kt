package com.example.note_app.presentation.ui.fragments.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.note_app.R
import com.example.note_app.databinding.FragmentMainScreenBinding
import com.example.note_app.domain.models.Note
import com.example.note_app.presentation.ui.fragments.mainScreen.adapter.MainScreenAdapter
import com.example.note_app.presentation.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private val viewModel by viewModels<MainScreenViewModel>()
    private val adapter = MainScreenAdapter(onClick = ::onClick)

    private fun onClick(note: Note) {
        findNavController().navigate(R.id.detailFragment, bundleOf(KEY_NOTES to note))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickers()
        initListener()
    }

    private fun initListener() {
        viewModel.getAllNote()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getAllNoteStates.collect {
                when (it) {
                    is UiState.EmptyState -> {
                        Toast.makeText(requireContext(), "Empty state", Toast.LENGTH_SHORT).show()
                    }

                    is UiState.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(requireContext(), "Error ${it.message}", Toast.LENGTH_SHORT)
                            .show()
                    }

                    is UiState.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    is UiState.Success -> {
                        binding.progressBar.isVisible = false
                        binding.rvNote.adapter = adapter
                        adapter.addNotes(it.data)
                    }
                }
            }
        }
    }

    private fun initClickers() {
        binding.apply {
            btnAdd.setOnClickListener {
                viewModel.addNote(
                    Note(
                        id = (0..9999).random(),
                        tittle = etTittle.toString(),
                        description = etDescription.toString()
                    )
                )
            }
        }
    }

    companion object {
        const val KEY_NOTES = "notes"
    }
}