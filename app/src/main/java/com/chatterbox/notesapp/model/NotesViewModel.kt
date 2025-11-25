package com.chatterbox.notesapp.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatterbox.notesapp.data.local.dao.NoteDao
import com.chatterbox.notesapp.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotesViewModel(
    private val noteDao: NoteDao
) : ViewModel() {

    val notes = noteDao
        .getAllNotes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _selectedNote = MutableStateFlow<NoteEntity?>(null)
    val selectedNote: StateFlow<NoteEntity?> = _selectedNote.asStateFlow()

    fun onNoteSelected(note: NoteEntity) {
        _selectedNote.value = note
    }

    fun setSelectedNote(note: NoteEntity?) {
        _selectedNote.value = note
    }
    fun addNote(title: String, content: String): Int {
        var noteId = -1

        viewModelScope.launch {
            // No need to return anything here now, we'll fetch it
            val newNote = NoteEntity(title = title, content = content)
            val newId = noteDao.insertNote(newNote).toInt()
            noteId = newId
            // After inserting, fetch the new note by its ID and set it as selected
            noteDao.getNoteById(newId).firstOrNull()?.let {
                setSelectedNote(it)
            }
        }
        return noteId
    }

    fun updateNote(note: NoteEntity) {
        viewModelScope.launch {
            noteDao.updateNote(note)
        }
    }


    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            noteDao.deleteNote(note)
        }
    }

    fun deleteNoteById(id: Int) {
        viewModelScope.launch {
            val noteToDelete = noteDao.getNoteById(id).firstOrNull()
            noteToDelete?.let {
                noteDao.deleteNote(it)
            }
        }
    }
}
