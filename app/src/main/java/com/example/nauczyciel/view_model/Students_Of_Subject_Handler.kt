package com.example.nauczyciel.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.nauczyciel.*
import com.example.nauczyciel.entities.Student
import com.example.nauczyciel.entities.Subject
import com.example.nauczyciel.entities.Student_Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Students_Of_Subject_Handler (application: Application):AndroidViewModel(application){
    private val database_Dao: Database_DAO
    var selectedStudents:MutableList<String>
    var studentsOfSubject:LiveData<List<Student>>
    //var currentStudentSubject:Student_Subject
    var chosenSubject:Subject
    var chosenStudent:Student
    init{
        database_Dao=Database_Helper.getInstance(application).databaseDao
        studentsOfSubject=database_Dao.getAllStudents()
        selectedStudents= arrayListOf<String>()
        chosenSubject=Subject(0,"","","","")
        chosenStudent=Student("","","")
        //currentStudentSubject=Student_Subject(0L,0L,"")
    }
    fun getStudentsOfSubject(subject:Long): LiveData<List<Student>>{
        studentsOfSubject=database_Dao.getStudentsOfSubject(subject)
        return studentsOfSubject;
    }
    fun getStudentsNotOfSubject(subject:Long): LiveData<List<Student>>{
        studentsOfSubject=database_Dao.getStudentsNotOfSubject(subject)
        return studentsOfSubject;
    }
    fun getallstudents():LiveData<List<Student>>{
        studentsOfSubject=database_Dao.getAllStudents()
        return studentsOfSubject
    }
    fun Add_Student_To_Subject_Dao(student_Subject: Student_Subject){
        viewModelScope.launch(Dispatchers.IO){
            database_Dao.insertStudentToSubject(student_Subject)
        }
    }


}