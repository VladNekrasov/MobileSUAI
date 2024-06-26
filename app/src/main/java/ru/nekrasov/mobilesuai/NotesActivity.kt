package ru.nekrasov.mobilesuai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import ru.nekrasov.mobilesuai.ui.notes.NotesScreen
import ru.nekrasov.mobilesuai.ui.notes.NotesViewModel
import ru.nekrasov.mobilesuai.ui.theme.MobileSUAITheme
@AndroidEntryPoint
class NotesActivity : ComponentActivity() {
    private val notesViewModelViewModel: NotesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileSUAITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NotesScreen(vm = notesViewModelViewModel)
                }
            }
        }
    }
}