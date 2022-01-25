package com.example.nauczyciel.entities
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName="marks_table")
data class Mark(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var markID: Long=0L,
    @ColumnInfo(name="idSubject")
    var subjectID: Long=0L,
    @ColumnInfo(name="idStudent")
    var studentID: String,
    @ColumnInfo(name="mark")
    var mark:Double,

    )