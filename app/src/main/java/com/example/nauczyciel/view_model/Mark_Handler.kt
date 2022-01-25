package com.example.nauczyciel.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.nauczyciel.Database_DAO
import com.example.nauczyciel.Database_DAO_Impl
import com.example.nauczyciel.Database_Helper
import com.example.nauczyciel.entities.Mark
import com.example.nauczyciel.entities.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Mark_Handler(application: Application):AndroidViewModel(application) {
    private val database_Dao:Database_DAO
    var mark:Mark
    var marks:LiveData<List<Mark>>
    var selectedmarks:LiveData<List<Mark>>
    init{
        database_Dao=Database_Helper.getInstance(application).databaseDao
        // database_Dao.insertStudent(Student(99,"asda","asd"))
        marks=database_Dao.getAllMarks()
        selectedmarks=database_Dao.getAllMarks()
        mark=Mark(0L,0L,"",0.0)


    }
    fun GetProperMarks(idStudent:String,idSubject:Long):LiveData<List<Mark>>{
        selectedmarks=database_Dao.GetFromMarks(idStudent,idSubject)
        return selectedmarks;

    }
    fun getAllMarks():LiveData<List<Mark>>{
        marks=database_Dao.getAllMarks()
        return marks;

    }
    fun Add_Mark(mark:Mark){
        viewModelScope.launch(Dispatchers.IO) {
            // database_Dao.insertStudent(Student(99,"asd","sdgfas"))
           // database_Dao.insertMark(Mark(0L,1L,"ID",5.0))
            database_Dao.insertMark(mark)
        }
    }
    fun DeleteAllMarks(){
        viewModelScope.launch(Dispatchers.IO) {
            // database_Dao.insertStudent(Student(99,"asd","sdgfas"))
            database_Dao.DeleteAllMarks()

        }
    }
}