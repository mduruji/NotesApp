package com.chatterbox.notesapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.chatterbox.notesapp.data.local.entity.NoteEntity
import com.chatterbox.notesapp.model.NotesViewModel
import com.chatterbox.notesapp.ui.components.NoteItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    viewModel: NotesViewModel,
    onNoteClick: (NoteEntity) -> Unit
) {
    val notes by viewModel.notes.collectAsState()

    Scaffold(
        topBar = {
            // 2. Define the TopAppBar
            TopAppBar(
                title = {
                    Text("My Notes")
                },
                // Optional: Adds styling and colors matching your theme
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),

                actions = {
                    IconButton(
                        onClick = {
                            val newNote = NoteEntity(title = "New Note", content = "")
                            onNoteClick(newNote)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Note"
                        )
                    }
                }
            )
        }
    ) { innerPadding -> // 3. The content of your screen goes here
        LazyColumn(
            // Apply the padding provided by Scaffold to avoid content overlapping the TopAppBar
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(notes.filterNotNull()) { note ->
                NoteItem(
                    note = note,
                    onNoteClick = {
                        viewModel.onNoteSelected(note)
                        onNoteClick(note)
                    },
                    // The parameter in NoteItem should be named 'onDeleteClick' to be more descriptive
                    onDeleteClick = { viewModel.deleteNote(note) }
                )
            }
        }
    }
}
