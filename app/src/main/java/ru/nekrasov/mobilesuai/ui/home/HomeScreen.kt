package ru.nekrasov.mobilesuai.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.nekrasov.mobilesuai.ui.theme.MobileSUAITheme

@Composable
fun HomeScreen(
    vm: HomeViewModel = viewModel()
){
    val context = LocalContext.current
    Column (
        modifier = Modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Некрасов Владислав группа 4136",
            style = typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { vm.onDeviceButtonClick(context) },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Устройство"
                )
            }
            Button(
                onClick = { vm.onGeolocationButtonClick(context) },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Геолокация"
                )
            }
            Button(
                onClick = { vm.onNotesButtonClick(context) },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Заметки"
                )
            }
            ThemeSwitcher(darkChecked = vm.darkChecked, onThemeCheckedChange = {theme -> vm.onThemeCheckedChange(theme)})
        }
    }
}

@Composable
fun ThemeSwitcher(
    darkChecked: Boolean,
    onThemeCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(text = "Тёмная тема")
        Switch(
            checked = darkChecked,
            onCheckedChange = { onThemeCheckedChange(it) }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MobileSUAITheme {
        HomeScreen()
    }
}