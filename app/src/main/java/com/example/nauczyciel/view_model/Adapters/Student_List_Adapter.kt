package com.example.nauczyciel.view_model.Adapters
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button
import com.example.nauczyciel.R
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.nauczyciel.view_model.Student_Handler
import com.example.nauczyciel.entities.Student
class Student_List_Adapter(private val students:LiveData<List<Student>>,private val viewModel:Student_Handler):RecyclerView.Adapter<Student_List_Adapter.Student_List_Holder>() {
    inner class Student_List_Holder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textViewID = view.findViewById<TextView>(R.id.student_id)
        val textViewFirstName = view.findViewById<TextView>(R.id.first_name)
        val textViewLastName = view.findViewById<TextView>(R.id.last_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Student_List_Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.one_student_row, parent, false)
        return Student_List_Holder(view)
    }

    override fun onBindViewHolder(holder: Student_List_Holder, position: Int) {
        holder.textViewID.text=students.value?.get(position)?.studentID.toString()
        holder.textViewFirstName.text=students.value?.get(position)?.FirstName
        holder.textViewLastName.text=students.value?.get(position)?.LastName
       // holder.view
    }

    override fun getItemCount()=students.value?.size?:0


}