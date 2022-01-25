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
import com.example.nauczyciel.entities.Student
import com.example.nauczyciel.view_model.Adapters.Student_List_Adapter
import com.example.nauczyciel.view_model.Student_Handler
import com.example.nauczyciel.view_model.view_model_factiory.Students_Handler_Factory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Students_All.newInstance] factory method to
 * create an instance of this fragment.
 */
class Students_All : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModel_List: Student_Handler

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
        return inflater.inflate(R.layout.fragment_students__all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory_list= Students_Handler_Factory((requireNotNull(this.activity).application))
        viewModel_List=ViewModelProvider(requireActivity(), factory_list).get(Student_Handler::class.java)
        val studentListAdapter=Student_List_Adapter(viewModel_List.students,viewModel_List)
        viewModel_List.students.observe(viewLifecycleOwner,{studentListAdapter.notifyDataSetChanged()})
        val layoutManager=LinearLayoutManager(view.context)

        view.findViewById<RecyclerView>(R.id.student_recyclerView).let{
           it.adapter=studentListAdapter
           it.layoutManager=layoutManager
        }
        view.findViewById<Button>(R.id.Nav_Button).setOnClickListener{
            it.findNavController().navigate(R.id.Action_Students_List_To_Add)

        }
      //  viewModel_List.Purge()
      //  var std=Student("","","124","132")
       // viewModel_List.Add_Student(std)

    }
}