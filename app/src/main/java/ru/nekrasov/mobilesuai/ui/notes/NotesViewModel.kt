package ru.nekrasov.mobilesuai.ui.notes

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import ru.nekrasov.mobilesuai.MainActivity

class NotesViewModel : ViewModel() {
    fun onBackButtonClick(currentContext: Context){
        val intent = Intent(currentContext, MainActivity::class.java)
        currentContext.startActivity(intent)
    }

    fun onAddNoteClicked(){
        TODO()
    }
}