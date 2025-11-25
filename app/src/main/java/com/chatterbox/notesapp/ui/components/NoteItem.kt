package com.chatterbox.notesapp.ui.components

import android.R.attr.text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chatterbox.notesapp.data.local.entity.NoteEntity
import com.chatterbox.notesapp.ui.theme.NotesAppTheme

@Composable
fun NoteItem(
    note: NoteEntity,
    onNoteClick: (NoteEntity) -> Unit,
    onDeleteClick: (NoteEntity) -> Unit
) {
    Card(
        modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        onClick = {
            onNoteClick(note)
        }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = note.title,
                style = MaterialTheme.typography.titleLarge
            )
            IconButton(
                modifier = Modifier.padding(5.dp),
                onClick = {
                    onDeleteClick(note)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Home"
                )
            }
        }
    }
}

