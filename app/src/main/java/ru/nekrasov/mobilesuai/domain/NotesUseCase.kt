package ru.nekrasov.mobilesuai.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesUseCase @Inject constructor(
    private val notesRepo: NotesRepository
) {
    fun notesFlow(): Flow<List<Note>> {
        return notesRepo.allFlow()
    }
    suspend fun save(note: Note){
        if (note.id == null) {
            notesRepo.add(note)
        } else {
            notesRepo.update(note)
        }
    }
    suspend fun delete(id: Long){
        notesRepo.deleteById(id)
    }
}