package com.chatterbox.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chatterbox.notesapp.data.local.DatabaseProvider
import com.chatterbox.notesapp.model.NotesViewModel
import com.chatterbox.notesapp.ui.theme.NotesAppTheme
import com.chatterbox.notesapp.ui.screens.NoteScreen
import com.chatterbox.notesapp.ui.screens.NoteEditorScreen

/*
* todo: set up the database
* todo: create the dao and the crud interface
* todo: create the display for the note cards
* todo: create the display for writing the notes
* todo: logic: create, edit, delete, read
* */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = DatabaseProvider.getDatabase(this)
        val notesViewModel = NotesViewModel(db.noteDao())
        setContent {
            NotesAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "notes_list"
                ) {
                    composable("notes_list") {
                        NoteScreen(
                            viewModel = notesViewModel,
                            onNoteClick = {
                                navController.navigate("note_editor")
                            },
                            navController = navController
                        )
                    }

                    composable("note_editor") {
                        NoteEditorScreen(
                            viewModel = notesViewModel,
                            onNavigationBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotesAppTheme {

    }
}
