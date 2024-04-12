package ru.nekrasov.mobilesuai.domain

import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun add(note: Note): Long
    suspend fun update(note: Note)
    suspend fun deleteById(id: Long)
    suspend fun deleteAll()
    fun allFlow(): Flow<List<Note>>
}