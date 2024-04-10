package ru.nekrasov.mobilesuai.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var darkChecked by mutableStateOf(false)
        private set
    fun onThemeCheckedChange(theme: Boolean){
        darkChecked = theme
    }
}