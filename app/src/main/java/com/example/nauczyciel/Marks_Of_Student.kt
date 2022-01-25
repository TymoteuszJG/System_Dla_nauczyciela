package com.example.nauczyciel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nauczyciel.entities.Mark
import com.example.nauczyciel.view_model.Adapters.Mark_List_Adapter
import com.example.nauczyciel.view_model.Adapters.Students_Of_Subject_Adapter
import com.example.nauczyciel.view_model.Mark_Handler
import com.example.nauczyciel.view_model.Student_Handler
import com.example.nauczyciel.view_model.Students_Of_Subject_Handler
import com.example.nauczyciel.view_model.Subject_Handler
import com.example.nauczyciel.view_model.view_model_factiory.Marks_Handler_Factory
import com.example.nauczyciel.view_model.view_model_factiory.Student_Of_Subject_Factory
import com.example.nauczyciel.view_model.view_model_factiory.Students_Handler_Factory
import com.example.nauczyciel.view_model.view_model_factiory.Subject_Handler_Factory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Marks_Of_Student.newInstance] factory method to
 * create an instance of this fragment.
 */
class Marks_Of_Student : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModel_List_Students_Of_Subjects: Students_Of_Subject_Handler
    private lateinit var viewModel_Marks_Of_Subject:Mark_Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marks__of__student, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val factoryStudent_Of_Subject= Student_Of_Subject_Factory((requireNotNull(this.activity).application))
        val factoryMarks=Marks_Handler_Factory((requireNotNull(this.activity).application))


        viewModel_List_Students_Of_Subjects= ViewModelProvider(requireActivity(), factoryStudent_Of_Subject).get(Students_Of_Subject_Handler::class.java)
        viewModel_Marks_Of_Subject=ViewModelProvider(requireActivity(), factoryMarks).get(Mark_Handler::class.java)
     // var x=viewModel_List_Students_Of_Subjects.chosenSubject
      //val Marks_Adapter=Mark_List_Adapter(viewModel_Marks_Of_Subject.getAllMarks(),viewModel_Marks_Of_Subject)
        val Marks_Adapter=Mark_List_Adapter(viewModel_Marks_Of_Subject.GetProperMarks(viewModel_List_Students_Of_Subjects.chosenStudent.studentID,viewModel_List_Students_Of_Subjects.chosenSubject.subjectID),viewModel_Marks_Of_Subject)
       var x=viewModel_List_Students_Of_Subjects.chosenStudent
        var y=viewModel_List_Students_Of_Subjects.chosenSubject
        //viewModel_Marks_Of_Subject.Add_Mark(Mark(0L,1L,"ID",4.5))
        viewModel_Marks_Of_Subject.selectedmarks.observe(viewLifecycleOwner,{Marks_Adapter.notifyDataSetChanged()})
        val layoutManager= LinearLayoutManager(view.context)

        //Log.d(studentListAdapter.Students_Of_Subject_Holder())
        view.findViewById<RecyclerView>(R.id.Marks_Recycle_View).let{
            it.adapter=Marks_Adapter
            it.layoutManager=layoutManager
        }
        view.findViewById<Button>(R.id.Nav_Add_Mark_Button).setOnClickListener{
            it.findNavController().navigate(R.id.Action_Marks_To_Add_Marks)

        }
        //  viewModel_List.Purge()
        //  var std=Student("","","124","132")
        // viewModel_List.Add_Student(std)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Marks_Of_Student.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Marks_Of_Student().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}