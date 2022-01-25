package com.example.nauczyciel.view_model.Adapters
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.util.Log;
import android.widget.TextView
import android.content.Context
import android.widget.Button
import android.widget.CheckBox
import com.example.nauczyciel.R
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.nauczyciel.view_model.Student_Handler
import com.example.nauczyciel.entities.Student
import com.example.nauczyciel.entities.Subject
import com.example.nauczyciel.view_model.Subject_Handler

class Subject_List_Adapter(private val subjects:LiveData<List<Subject>>,private val viewModel:Subject_Handler,context:Context):RecyclerView.Adapter<Subject_List_Adapter.Subject_List_Holder>() {
    private val context:Context?=context
    inner class Subject_List_Holder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textViewID = view.findViewById<TextView>(R.id.subjectID)
        val textViewName = view.findViewById<TextView>(R.id.SubjectName)
        val textViewStartHour = view.findViewById<TextView>(R.id.StartHour)
        val textViewEndHour = view.findViewById<TextView>(R.id.EndHour)
        val myView=view


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Subject_List_Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.one_subject_row, parent, false)
        return Subject_List_Holder(view)
    }

    override fun onBindViewHolder(holder: Subject_List_Holder, position: Int) {
        holder.textViewID.text=subjects.value?.get(position)?.subjectID.toString()
        holder.textViewName.text=subjects.value?.get(position)?.Name
        holder.textViewEndHour.text=subjects.value?.get(position)?.EHour
        holder.textViewStartHour.text=subjects.value?.get(position)?.SHour
        holder.myView.setOnClickListener(){
            viewModel.subject=Subject(subjects.value?.get(position)?.subjectID!!,subjects.value?.get(position)?.Name!!,"","","")
           // Log.d(viewModel.subject.Name,viewModel.subject.subjectID.toString())
            holder.myView.findNavController().navigate(R.id.Action_Subject_List_To_Students_of_Subject)
        }


        // holder.view
    }

    override fun getItemCount()=subjects.value?.size?:0


}