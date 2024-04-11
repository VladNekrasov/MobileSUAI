package ru.nekrasov.mobilesuai.ui.home

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ru.nekrasov.mobilesuai.DeviceActivity
import ru.nekrasov.mobilesuai.GeolocationActivity
import ru.nekrasov.mobilesuai.NotesActivity

class HomeViewModel : ViewModel() {
    var darkChecked by mutableStateOf(false)
        private set
    fun onDeviceButtonClick(currentContext: Context){
        val intent = Intent(currentContext, DeviceActivity::class.java)
        currentContext.startActivity(intent)
    }
    fun onGeolocationButtonClick(currentContext: Context){
        val intent = Intent(currentContext, GeolocationActivity::class.java)
        currentContext.startActivity(intent)
    }
    fun onNotesButtonClick(currentContext: Context){
        val intent = Intent(currentContext, NotesActivity::class.java)
        currentContext.startActivity(intent)
    }
    fun onThemeCheckedChange(theme: Boolean){
        darkChecked = theme
    }
}