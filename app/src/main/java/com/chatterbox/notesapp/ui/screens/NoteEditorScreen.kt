package com.chatterbox.notesapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chatterbox.notesapp.model.NotesViewModel

@Composable
fun NoteEditorScreen(
    viewModel: NotesViewModel
) {
    val selectedNote by viewModel.selectedNote.collectAsState()
    val title = selectedNote?.title ?: ""
    val content = selectedNote?.content ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title TextField
        OutlinedTextField(
            value = title,
            onValueChange = {
                /* TODO: Update title in ViewModel */
            },
            label = { Text("Title") }
        )

        // Content TextField
        OutlinedTextField(
            value = content,
            onValueChange = { /* TODO: Update content in ViewModel */ },
            label = { Text("Content") },
            modifier = Modifier.weight(1f) // Takes up remaining space
        )
    }
}
