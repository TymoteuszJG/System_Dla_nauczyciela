package com.example.nauczyciel.view_model.view_model_factiory
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nauczyciel.view_model.Student_Handler
import java.lang.IllegalArgumentException

class Students_Handler_Factory (private val application: Application):ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(Student_Handler::class.java)){
            return Student_Handler(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}