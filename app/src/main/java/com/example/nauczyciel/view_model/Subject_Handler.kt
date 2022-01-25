package com.example.nauczyciel.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.nauczyciel.Add_Subject
import com.example.nauczyciel.Database_DAO
import com.example.nauczyciel.Database_DAO_Impl
import com.example.nauczyciel.Database_Helper
import com.example.nauczyciel.entities.Student
import com.example.nauczyciel.entities.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Subject_Handler(application: Application):AndroidViewModel(application) {
    private val database_Dao:Database_DAO
    var subject:Subject
    val subjects:LiveData<List<Subject>>
    //var studentsOfSubject: LiveData<List<Student>>

    init{
        database_Dao=Database_Helper.getInstance(application).databaseDao
        // database_Dao.insertStudent(Student(99,"asda","asd"))
        subjects=database_Dao.getAllSubjects()
        //selectedStudents= arrayListOf<Long>()
        //studentsOfSubject=database_Dao.getAllStudents()
        subject=Subject(0L,"","","","")


    }
 //   fun getStudentsOfSubject(subject:Long): LiveData<List<Student>>{
    //    studentsOfSubject=database_Dao.getStudentsOfSubject(subject)
   //     return studentsOfSubject;
  //  }
   // fun getStudentsNotOfSubject(subject:Long): LiveData<List<Student>>{
   //     studentsOfSubject=database_Dao.getStudentsNotOfSubject(subject)
    //    return studentsOfSubject;
 //   }
    fun Add_Subject(subject:Subject){
        viewModelScope.launch(Dispatchers.IO) {
            // database_Dao.insertStudent(Student(99,"asd","sdgfas"))
           // if(!database_Dao.isInSubjectTable(subject.subjectID))
                database_Dao.insertSubject(subject)
        }
    }
    fun Purge(){
        viewModelScope.launch(Dispatchers.IO) {
            // database_Dao.insertStudent(Student(99,"asd","sdgfas"))
            database_Dao.DeleteAllStudents()

        }
    }
}