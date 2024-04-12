package ru.nekrasov.mobilesuai.ui.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.nekrasov.mobilesuai.domain.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    vm: NotesViewModel
) {
    val context = LocalContext.current
    val notes by vm.notes.collectAsState()
    val selected = vm.selected
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
                    Text(text = "Заметки")
                }
            )
        },
        floatingActionButton = {
            if (selected==null) {
                FloatingActionButton(onClick = {vm.onAddNoteClicked()}) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
            else {
                FloatingActionButton(onClick = {vm.onEditComplete()}) {
                    Icon(Icons.Default.Check, contentDescription = "Check")
                }
            }
        }
    ) {
        innerPadding ->
        if (selected==null) {
            Notes(notes = notes, innerPadding = innerPadding, onNoteSelected = {note -> vm.onNoteSelected(note)})
        } else{
            NoteEdit(selected = selected, innerPadding = innerPadding, onNoteChange = {title, text -> vm.onNoteChange(title,text)})
        }
    }

}

@Composable
fun Notes(
    notes: List<Note>,
    innerPadding: PaddingValues,
    onNoteSelected: (Note) -> Unit
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.background)){
        items(notes) { note ->
            NoteItem(
                note = note,
                onNoteSelected = {onNoteSelected(note)}
            )
        }
    }
}

@Composable
fun NoteItem(note: Note,
             onNoteSelected: (Note) -> Unit
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .clickable { onNoteSelected(note) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.text,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEdit(
    selected: Note,
    innerPadding: PaddingValues,
    onNoteChange: (String,String) -> Unit
){
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
    ) {
        TextField(
            value = selected.title,
            onValueChange = {
                onNoteChange(it,selected.text) },
            label = { Text("Заголовок") },
            textStyle = TextStyle(fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.onPrimary
            )
        )
        BasicTextField(
            value = selected.text,
            textStyle = MaterialTheme.typography.bodyLarge
                .copy(color = MaterialTheme.colorScheme.onBackground),
            onValueChange = {
                onNoteChange(selected.title,it)
            },
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            decorationBox = {innerTextField ->
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)){
                    innerTextField()
                }
            },
            minLines = 20)
    }
}