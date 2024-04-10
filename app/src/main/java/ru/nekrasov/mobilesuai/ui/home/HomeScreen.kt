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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import ru.nekrasov.mobilesuai.ui.theme.MobileSUAITheme

@Composable
fun HomeScreen(
    vm: HomeViewModel = viewModel()
){

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
            style = typography.titleLarge
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Устройство"
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Геолокация"
                )
            }
            Button(
                onClick = { /*TODO*/ },
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