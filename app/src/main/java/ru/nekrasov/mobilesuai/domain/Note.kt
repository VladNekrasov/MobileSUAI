package ru.nekrasov.mobilesuai.domain
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
class Note (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    var id: Long?,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "text")
    var text: String
){
    constructor(title: String, text: String) : this(null, title, text)
}