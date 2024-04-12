package ru.nekrasov.mobilesuai.ui.notes

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.nekrasov.mobilesuai.MainActivity
import ru.nekrasov.mobilesuai.domain.Note
import ru.nekrasov.mobilesuai.domain.NotesUseCase
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesUseCase: NotesUseCase
) : ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes.asStateFlow()
    var selected  by mutableStateOf<Note?>(null)
        private set

    init {
        viewModelScope.launch {
            notesUseCase.notesFlow().collect {newNotes ->
                _notes.value = newNotes
            }
        }
    }
    fun onBackButtonClick(currentContext: Context){
        selected?.let {
            selected=null
        } ?: run {
            val intent = Intent(currentContext, MainActivity::class.java)
            currentContext.startActivity(intent)
        }
    }

    fun onDeleteButtonClick(){
        viewModelScope.launch {
            selected?.id?.let { it1 -> notesUseCase.delete(it1) }
            selected = null
        }
    }

    fun onNoteChange(title: String, text: String){
        selected = Note(
            id = this.selected?.id,
            title = title,
            text = text
        )
    }

    fun onEditComplete(){
        if (selected?.title?.isNotEmpty() == true || selected?.text?.isNotEmpty() == true) {
            viewModelScope.launch {
                notesUseCase.save(selected!!)
                selected=null
            }
        }
    }

    fun onNoteSelected(note: Note){
        selected=note
    }

    fun onAddNoteClicked(){
        if (selected==null){
            selected=Note(
                title = "",
                text = ""
            )
        }
    }
}