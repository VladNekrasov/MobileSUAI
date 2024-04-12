package ru.nekrasov.mobilesuai.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.nekrasov.mobilesuai.domain.Note

@Dao
interface NotesDao {
    @Query("SELECT * FROM note")
    suspend fun all():List<Note>

    @Query("SELECT * FROM note ORDER BY note_id ASC")
    fun allFlow(): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note): Long

    @Update
    suspend fun update(note: Note)

    @Query("DELETE FROM note WHERE note_id= :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM note")
    suspend fun deleteAll()
}