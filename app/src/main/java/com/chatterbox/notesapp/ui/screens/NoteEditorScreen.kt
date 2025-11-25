package com.chatterbox.notesapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chatterbox.notesapp.data.local.entity.NoteEntity
import com.chatterbox.notesapp.model.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditorScreen(
    viewModel: NotesViewModel,
    onNavigationBack: () -> Unit
) {
    val selectedNote by viewModel.selectedNote.collectAsState()
    var title by remember { mutableStateOf(selectedNote?.title ?: "") }
    var content by remember { mutableStateOf(selectedNote?.content ?: "") }
    var isEditing by remember { mutableStateOf(true) }
    var noteId by remember { mutableIntStateOf(selectedNote?.id ?: 0) }

    Scaffold(
        topBar = {
            // 2. Define the TopAppBar
            CenterAlignedTopAppBar(

                navigationIcon = {
                    IconButton(
                        onClick = {
                            isEditing = false
                            if (title.isBlank() && content.isBlank()) {
                                viewModel.deleteNoteById(noteId)
                            }
                            viewModel.setSelectedNote(null)
                            onNavigationBack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Add Note"
                        )
                    }
                },

                title = {
                    Text(title)
                },
                // Optional: Adds styling and colors matching your theme
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),

                actions = {
                    if (isEditing) {
                        IconButton(
                            onClick = {
                                if (selectedNote != null) {
                                    viewModel.updateNote(
                                        NoteEntity(id = selectedNote!!.id, title = title, content = content)
                                    )
                                } else {
                                    if (title.isNotBlank() && content.isNotBlank()){
                                        noteId = viewModel.addNote(title = title, content = content)
                                    }
                                }
                                isEditing = false
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Done,
                                contentDescription = "Save Note"
                            )
                        }
                    } else {
                        IconButton(onClick = { isEditing = true }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit Note"
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title TextField
            OutlinedTextField(
                value = title,
                onValueChange = {
                    /* TODO: Update title in ViewModel */
                    title = it
                },
                readOnly = !isEditing,
                label = { Text("Title") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 1.dp)
            )

            // Content TextField
            OutlinedTextField(
                value = content,
                onValueChange = {
                    /* TODO: Update content in ViewModel */
                    content = it
                },
                readOnly = !isEditing,
                label = { Text("Content") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp)
                    .weight(1f) // Takes up remaining space
            )
        }
    }
}
