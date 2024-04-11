package ru.nekrasov.mobilesuai.ui.geolocation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import ru.nekrasov.mobilesuai.domain.Location
import ru.nekrasov.mobilesuai.ui.theme.MobileSUAITheme


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun GeolocationScreen(
    vm: GeolocationViewModel = viewModel()
) {
    val context = LocalContext.current
    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
    )
    LaunchedEffect(true){
        locationPermissions.launchMultiplePermissionRequest()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = { vm.onBackButtonClick(context) }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                title = {
                    Text(text = "Геолокация")
                }
            )
        }
    ) {innerPadding ->
        MenuGeolocation(
            location = vm.location,
            onLocationChange = {latitude, longitude -> vm.onLocationChange(latitude, longitude)},
            onGeolocationButtonClick = {vm.onGeolocationButtonClick()},
            innerPadding = innerPadding)
    }
}

@Composable
fun MenuGeolocation(
    location: Location,
    onGeolocationButtonClick: () -> Unit,
    onLocationChange: (String, String) -> Unit,
    innerPadding: PaddingValues
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(text = "Ширина")
                TextField(
                    modifier = Modifier
                        .border(BorderStroke(2.dp, MaterialTheme.colorScheme.primary))
                        .padding(0.dp),
                    value = location.latitude,
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    onValueChange = { onLocationChange(it, location.longitude) }
                )
            }
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(text = "Долгота")
                TextField(
                    modifier = Modifier
                        .border(BorderStroke(2.dp, MaterialTheme.colorScheme.primary))
                        .padding(0.dp),
                    value = location.longitude,
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    onValueChange = { onLocationChange(location.latitude, it)}
                )
            }
            Button(
                onClick = { onGeolocationButtonClick() },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Определить координаты"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeviceScreenPreview() {
    MobileSUAITheme {
        GeolocationScreen()
    }
}