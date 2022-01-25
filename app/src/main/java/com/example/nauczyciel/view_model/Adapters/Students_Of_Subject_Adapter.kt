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
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.nauczyciel.Students_Of_Subject
import com.example.nauczyciel.entities.Mark
import com.example.nauczyciel.view_model.Student_Handler
import com.example.nauczyciel.entities.Student
import com.example.nauczyciel.entities.Student_Subject
import com.example.nauczyciel.entities.Subject
import com.example.nauczyciel.view_model.Students_Of_Subject_Handler

class Students_Of_Subject_Adapter(private val students:LiveData<List<Student>>,private val viewModel:Students_Of_Subject_Handler,context: Context):RecyclerView.Adapter<Students_Of_Subject_Adapter.Students_Of_Subject_Holder>() {
    private val context: Context? = context
        //Students_Of_Subject_Handler
    inner class Students_Of_Subject_Holder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textViewID = view.findViewById<TextView>(R.id.student_id)
        val textViewFirstName = view.findViewById<TextView>(R.id.first_name)
        val textViewLastName = view.findViewById<TextView>(R.id.last_name)
        val myView = view
        val checkbok=view.findViewById<CheckBox>(R.id.add_Student_To_Subject_Checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Students_Of_Subject_Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.one_student_checkmark_row, parent, false)
        return Students_Of_Subject_Holder(view)
    }

    override fun onBindViewHolder(holder: Students_Of_Subject_Holder, position: Int) {
        holder.textViewID.text=students.value?.get(position)?.studentID.toString()
        holder.textViewFirstName.text=students.value?.get(position)?.FirstName
        holder.textViewLastName.text=students.value?.get(position)?.LastName
        holder.myView.setOnClickListener(){
            viewModel.chosenStudent= Student(students.value?.get(position)?.studentID!!,students.value?.get(position)?.FirstName!!,students.value?.get(position)?.LastName!!)
            var temp=students.value?.get(position)?.FirstName!!
            // Log.d(viewModel.subject.Name,viewModel.subject.subjectID.toString())
            holder.myView.findNavController().navigate(R.id.Action_students_Of_Subject_to_marks_Of_Students)
        }
        holder.checkbok.setOnCheckedChangeListener   {buttonView, isChecked ->
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