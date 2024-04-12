package ru.nekrasov.mobilesuai.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.nekrasov.mobilesuai.data.NotesRepositoryDB
import ru.nekrasov.mobilesuai.domain.NotesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindModule {
    @Singleton
    @Binds
    fun bindNotesRepository(notesRepository: NotesRepositoryDB): NotesRepository
}