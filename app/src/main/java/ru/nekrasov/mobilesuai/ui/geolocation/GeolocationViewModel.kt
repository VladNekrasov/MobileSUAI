package ru.nekrasov.mobilesuai.ui.geolocation

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ru.nekrasov.mobilesuai.MainActivity
import ru.nekrasov.mobilesuai.domain.Location

class GeolocationViewModel : ViewModel() {
    var location  by mutableStateOf(Location())
        private set

    fun onBackButtonClick(currentContext: Context){
        val intent = Intent(currentContext, MainActivity::class.java)
        currentContext.startActivity(intent)
    }

    fun onLocationChange(latitude: String, longitude: String){
        location = Location(
            latitude = latitude,
            longitude = longitude
        )
    }

    fun onGeolocationButtonClick(){
        TODO()
    }
}