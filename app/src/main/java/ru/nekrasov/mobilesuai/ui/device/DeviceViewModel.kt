package ru.nekrasov.mobilesuai.ui.device

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ru.nekrasov.mobilesuai.MainActivity

class DeviceViewModel : ViewModel() {
    var flashlightChecked by mutableStateOf(false)
        private set
    fun onBackButtonClick(currentContext: Context){
        val intent = Intent(currentContext, MainActivity::class.java)
        currentContext.startActivity(intent)
    }
    fun onFlashlightCheckedChange(flashlight: Boolean){
        flashlightChecked = flashlight
    }
    fun onVibrationButtonClick(){
        TODO()
    }
}