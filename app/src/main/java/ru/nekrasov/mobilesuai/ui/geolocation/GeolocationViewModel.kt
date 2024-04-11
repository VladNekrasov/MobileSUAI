package ru.nekrasov.mobilesuai.ui.geolocation

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.nekrasov.mobilesuai.MainActivity
import ru.nekrasov.mobilesuai.domain.Location
import ru.nekrasov.mobilesuai.utils.LocationTracker
import javax.inject.Inject

@HiltViewModel
class GeolocationViewModel @Inject constructor(
    private val locationTracker: LocationTracker
) : ViewModel() {
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
        viewModelScope.launch {
            val newLocation = locationTracker.getCurrentLocation()
            newLocation?.let {
                location = Location(
                    latitude = it.latitude.toString(),
                    longitude = it.longitude.toString()
                )
            }
        }
    }
}