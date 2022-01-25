package com.example.nauczyciel.entities
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="studentsubject_table")
data class Student_Subject(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var jointID: Long=0L,
    @ColumnInfo(name="idSubject")
    var subjectID: Long=0L,
    @ColumnInfo(name="idStudent")
    var studentID: String,


    )