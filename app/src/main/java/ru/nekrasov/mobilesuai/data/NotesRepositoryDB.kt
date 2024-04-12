package ru.nekrasov.mobilesuai.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.nekrasov.mobilesuai.domain.Note
import ru.nekrasov.mobilesuai.domain.NotesRepository
import javax.inject.Inject

class NotesRepositoryDB @Inject constructor(
    private val notesDao: NotesDao
): NotesRepository
{
    override suspend fun add(note: Note): Long = withContext(Dispatchers.IO) {
        notesDao.insert(note)
    }

    override suspend fun update(note: Note) = withContext(Dispatchers.IO) {
        notesDao.update(note)
    }

    override fun allFlow(): Flow<List<Note>> {
        return notesDao.allFlow()
    }

    override suspend fun deleteAll()= withContext(Dispatchers.IO) {
        notesDao.deleteAll()
    }

    override suspend fun deleteById(id: Long) = withContext(Dispatchers.IO){
        notesDao.deleteById(id)
    }
}