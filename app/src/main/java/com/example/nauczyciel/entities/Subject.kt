package com.example.nauczyciel.entities
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="subjects_table")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var subjectID: Long=0L,
    @ColumnInfo(name="subjectName")
    var Name:String,
    @ColumnInfo(name="StartHour")
    var SHour:String,
    @ColumnInfo(name="EndHour")
    var EHour:String,
    @ColumnInfo(name="Day_of_Week")
    var Day:String,

    )