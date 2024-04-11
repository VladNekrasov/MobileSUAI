package ru.nekrasov.mobilesuai.ui.device

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
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
    fun onFlashlightCheckedChange(flashlight: Boolean, context: Context){
        flashlightChecked = flashlight
        val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            if (context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                val cameraIdList = cameraManager.cameraIdList
                var cameraId: String? = null
                for (id in cameraIdList) {
                    val characteristics = cameraManager.getCameraCharacteristics(id)
                    val available = characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)
                    val lensFacing = characteristics.get(CameraCharacteristics.LENS_FACING)
                    if (available != null && available && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                        cameraId = id
                        break
                    }
                }
                if (cameraId != null) {
                    if (flashlightChecked)
                        cameraManager.setTorchMode(cameraId, true)
                    else
                        cameraManager.setTorchMode(cameraId, false)
                } else {
                    Log.e("Flashlight", "No back camera with flashlight available")
                }
            } else {
                Log.e("Flashlight", "No flashlight available")
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: Exception) {
            Log.e("Flashlight", "Exception: ${e.message}")
        }
    }
    fun onVibrationButtonClick(context: Context){
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationEffect1: VibrationEffect =
            VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.cancel()
        vibrator.vibrate(vibrationEffect1)
    }
}