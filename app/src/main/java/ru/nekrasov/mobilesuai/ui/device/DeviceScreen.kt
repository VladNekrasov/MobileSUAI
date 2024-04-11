package ru.nekrasov.mobilesuai.ui.device

import android.content.Context
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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.nekrasov.mobilesuai.ui.theme.MobileSUAITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceScreen(
    vm: DeviceViewModel = viewModel()
) {
    val context = LocalContext.current
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
                    Text(text = "Работа с устройством")
                }
            )
        }
    ) {innerPadding ->
        MenuDevice(
            flashlightChecked = vm.flashlightChecked,
            context = context,
            onFlashlightChecked = {flashlight, context -> vm.onFlashlightCheckedChange(flashlight, context) },
            onVibrationButtonClick = {context -> vm.onVibrationButtonClick(context)},
            innerPadding = innerPadding
        )
    }
}

@Composable
fun MenuDevice(
    flashlightChecked: Boolean,
    context: Context,
    onVibrationButtonClick: (Context) -> Unit,
    onFlashlightChecked: (Boolean, Context) -> Unit,
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
            Button(
                onClick = { onVibrationButtonClick(context) },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Вибрация"
                )
            }
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(text = "Фонарик")
                Switch(
                    checked = flashlightChecked,
                    onCheckedChange = { onFlashlightChecked(it, context) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeviceScreenPreview() {
    MobileSUAITheme {
        DeviceScreen()
    }
}