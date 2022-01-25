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
import com.example.nauczyciel.view_model.Adapters.Student_List_Adapter
import com.example.nauczyciel.view_model.Adapters.Students_Of_Subject_Adapter
import com.example.nauczyciel.entities.Student_Subject
import com.example.nauczyciel.view_model.Student_Handler
import com.example.nauczyciel.view_model.Subject_Handler
import com.example.nauczyciel.view_model.Students_Of_Subject_Handler
import com.example.nauczyciel.view_model.view_model_factiory.Students_Handler_Factory
import com.example.nauczyciel.view_model.view_model_factiory.Student_Of_Subject_Factory

import android.util.Log
import com.example.nauczyciel.view_model.view_model_factiory.Subject_Handler_Factory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Students_Of_Subject.newInstance] factory method to
 * create an instance of this fragment.
 */
class Students_Of_Subject : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModel_List_Student: Student_Handler
    private lateinit var viewModel_List_Students_Of_Subjects:Students_Of_Subject_Handler
    private lateinit var viewModel_List_Subject: Subject_Handler

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
        return inflater.inflate(R.layout.fragment_students__of__subject, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factoryStudent= Students_Handler_Factory((requireNotNull(this.activity).application))
        val factoryStudent_Of_Subject=Student_Of_Subject_Factory((requireNotNull(this.activity).application))
        val factorySubject=Subject_Handler_Factory((requireNotNull(this.activity).application))
        viewModel_List_Student= ViewModelProvider(requireActivity(), factoryStudent).get(Student_Handler::class.java)
        viewModel_List_Subject=ViewModelProvider(requireActivity(), factorySubject).get(Subject_Handler::class.java)
        viewModel_List_Students_Of_Subjects= ViewModelProvider(requireActivity(), factoryStudent_Of_Subject).get(Students_Of_Subject_Handler::class.java)
        viewModel_List_Students_Of_Subjects.chosenSubject=viewModel_List_Subject.subject;
        val studentListAdapter= Students_Of_Subject_Adapter(viewModel_List_Students_Of_Subjects.getStudentsOfSubject(viewModel_List_Students_Of_Subjects.chosenSubject.subjectID),viewModel_List_Students_Of_Subjects,this.requireContext())
        //val studentListAdapter= Students_Of_Subject_Adapter(viewModel_List_Students_Of_Subjects.studentsOfSubject,viewModel_List_Students_Of_Subjects,this.requireContext())

        //val studentListAdapter= Students_Of_Subject_Adapter(viewModel_List_Students_Of_Subjects.getallstudents(),viewModel_List_Students_Of_Subjects,this.requireContext())
        //val studentListAdapter=Students_Of_Subject_Adapter(viewModel_List_Student.students,viewModel_List_Students_Of_Subjects,this.requireContext())
        //viewModel_List_Student.students.observe(viewLifecycleOwner,{studentListAdapter.notifyDataSetChanged()})
        viewModel_List_Students_Of_Subjects.studentsOfSubject.observe(viewLifecycleOwner,{studentListAdapter.notifyDataSetChanged()})
        val layoutManager= LinearLayoutManager(view.context)

        //Log.d(studentListAdapter.Students_Of_Subject_Holder())
        view.findViewById<RecyclerView>(R.id.student_of_subject_recyclerView).let{
            it.adapter=studentListAdapter
            it.layoutManager=layoutManager
        }
        view.findViewById<Button>(R.id.Add_Student_To_Group_Nav).setOnClickListener{
            it.findNavController().navigate(R.id.Action_students_Of_Subject_to_add_students_to_subject)

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
         * @return A new instance of fragment Students_Of_Subject.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Students_Of_Subject().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}