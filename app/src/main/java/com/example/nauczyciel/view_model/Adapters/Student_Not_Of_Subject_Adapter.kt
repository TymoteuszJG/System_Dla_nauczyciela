package com.example.nauczyciel.view_model.Adapters
import android.view.View
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button
import android.widget.CheckBox
import com.example.nauczyciel.R
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.nauczyciel.view_model.Student_Handler
import com.example.nauczyciel.entities.Student
import com.example.nauczyciel.view_model.Students_Not_Of_Subject_Handler

class Student_Not_Of_Subject_Adapter(private val students:LiveData<List<Student>>, private val viewModel: Students_Not_Of_Subject_Handler, context: Context):RecyclerView.Adapter<Student_Not_Of_Subject_Adapter.Student_Not_Of_Subject_Holder>() {
    private val context: Context? = context
    inner class Student_Not_Of_Subject_Holder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textViewID = view.findViewById<TextView>(R.id.student_id)
        val textViewFirstName = view.findViewById<TextView>(R.id.first_name)
        val textViewLastName = view.findViewById<TextView>(R.id.last_name)
        val myView = view
        val checkbok=view.findViewById<CheckBox>(R.id.add_Student_To_Subject_Checkbox)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Student_Not_Of_Subject_Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.one_student_not_of_subject_checkmark_row, parent, false)
        return Student_Not_Of_Subject_Holder(view)
    }

    override fun onBindViewHolder(holder: Student_Not_Of_Subject_Holder, position: Int) {
        holder.textViewID.text=students.value?.get(position)?.studentID.toString()
        holder.textViewFirstName.text=students.value?.get(position)?.FirstName
        holder.textViewLastName.text=students.value?.get(position)?.LastName
        holder.checkbok.setOnCheckedChangeListener {buttonView, isChecked ->
                    //Add student to temp list of selected
                    if (isChecked) {
                        viewModel.selectedStudents.add(students.value?.get(position)?.studentID!!)
                   } else {
                        var ind =
                            viewModel.selectedStudents.indexOf(students.value?.get(position)?.studentID!!)
                        viewModel.selectedStudents.removeAt(ind)
                    }
                }
    }

    override fun getItemCount()=students.value?.size?:0


}
