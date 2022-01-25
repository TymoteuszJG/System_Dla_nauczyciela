package com.example.nauczyciel.view_model.Adapters
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button
import com.example.nauczyciel.R
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.nauczyciel.view_model.Mark_Handler
import com.example.nauczyciel.entities.Mark
import com.example.nauczyciel.entities.Subject

class Mark_List_Adapter(private val marks:LiveData<List<Mark>>,private val viewModel:Mark_Handler):RecyclerView.Adapter<Mark_List_Adapter.Mark_List_Holder>() {
    inner class Mark_List_Holder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textViewMark = view.findViewById<TextView>(R.id.Mark)
       // val textViewStudentName = view.findViewById<TextView>(R.id.Student_Name)
        //val textViewSubjectName = view.findViewById<TextView>(R.id.Subject_Name)
       val myView=view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Mark_List_Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.one_mark_row, parent, false)
        return Mark_List_Holder(view)
    }

    override fun onBindViewHolder(holder: Mark_List_Holder, position: Int) {
        holder.textViewMark.text=marks.value?.get(position)?.mark.toString()



    }

    override fun getItemCount()=marks.value?.size?:0


}