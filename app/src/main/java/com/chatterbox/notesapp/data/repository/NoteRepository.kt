//package com.chatterbox.notesapp.data.repository
//
//import kotlinx.coroutines.flow.Flow
//import com.chatterbox.notesapp.data.local.entity.NoteEntity
//import com.chatterbox.notesapp.data.local.dao.NoteDao
//
//class NoteRepository(private val noteDao: NoteDao) {
//
//    // Get all notes as a Flow (auto updates UI)
//    val allNotes: Flow<List<NoteEntity>> = noteDao.getAllNotes()
//
//    // Insert a note
//    suspend fun insert(note: NoteEntity) {
//        noteDao.insertNote(note)
//    }
//
//    // Update a note
//    suspend fun update(note: NoteEntity) {
//        noteDao.updateNote(note)
//    }
//
//    // Delete a note
//    suspend fun delete(note: NoteEntity) {
//        noteDao.deleteNote(note)
//    }
//
//    // Optional: Get note by ID
//    suspend fun getNoteById(id: Int): Flow<NoteEntity?> {
//        return noteDao.getNoteById(id)
//    }
//}
