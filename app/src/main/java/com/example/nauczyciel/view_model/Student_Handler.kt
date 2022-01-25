package com.example.nauczyciel.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.nauczyciel.Database_DAO
import com.example.nauczyciel.Database_DAO_Impl
import com.example.nauczyciel.Database_Helper
import com.example.nauczyciel.entities.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Student_Handler(application: Application):AndroidViewModel(application) {
    private val database_Dao:Database_DAO
    var student:Student
    val students:LiveData<List<Student>>
    init{
        database_Dao=Database_Helper.getInstance(application).databaseDao
       // database_Dao.insertStudent(Student(99,"asda","asd"))
        students=database_Dao.getAllStudents()
        student=Student("","","")


    }
    fun Add_Student(student:Student){
        viewModelScope.launch(Dispatchers.IO) {
            // database_Dao.insertStudent(Student(99,"asd","sdgfas"))
            if(!database_Dao.isInStudentsTable(student.studentID))
                database_Dao.insertStudent(student)
        }
    }

    fun Purge(){
        viewModelScope.launch(Dispatchers.IO) {
            // database_Dao.insertStudent(Student(99,"asd","sdgfas"))
            database_Dao.DeleteAllStudents()
            database_Dao.DeleteAllSB()
            database_Dao.DeleteAllStudents()
            database_Dao.DeleteAllSubject()

        }
    }
}