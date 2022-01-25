package com.example.nauczyciel.entities
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="students_table")
data class Student(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="id")
    var studentID: String,
    @ColumnInfo(name="first_name")
    var FirstName:String,
    @ColumnInfo(name="last_name")
    var LastName:String,

    )