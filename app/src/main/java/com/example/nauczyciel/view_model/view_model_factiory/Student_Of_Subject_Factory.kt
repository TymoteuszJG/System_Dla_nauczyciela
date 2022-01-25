package com.example.nauczyciel.view_model.view_model_factiory
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nauczyciel.view_model.Students_Of_Subject_Handler
import com.example.nauczyciel.view_model.Students_Not_Of_Subject_Handler
import com.example.nauczyciel.view_model.Subject_Handler
import java.lang.IllegalArgumentException

class Student_Of_Subject_Factory(private val application: Application): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(Students_Of_Subject_Handler::class.java)){
            return Students_Of_Subject_Handler(application) as T
        }
        if(modelClass.isAssignableFrom(Students_Not_Of_Subject_Handler::class.java)){
            return Students_Not_Of_Subject_Handler(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
