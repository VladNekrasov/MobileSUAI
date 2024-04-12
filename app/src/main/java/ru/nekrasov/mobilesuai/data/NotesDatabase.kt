package ru.nekrasov.mobilesuai.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nekrasov.mobilesuai.domain.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}