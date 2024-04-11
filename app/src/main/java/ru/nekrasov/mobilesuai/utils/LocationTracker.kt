package ru.nekrasov.mobilesuai.utils

import android.location.Location


interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}