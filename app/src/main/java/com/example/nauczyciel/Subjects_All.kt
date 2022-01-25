package com.example.nauczyciel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nauczyciel.entities.Subject
import com.example.nauczyciel.view_model.Adapters.Subject_List_Adapter
import com.example.nauczyciel.view_model.Student_Handler
import com.example.nauczyciel.view_model.Subject_Handler
import com.example.nauczyciel.view_model.view_model_factiory.Subject_Handler_Factory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Subjects_All.newInstance] factory method to
 * create an instance of this fragment.
 */
class Subjects_All : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModel_List: Subject_Handler
    private lateinit var tempSub:Subject

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
        return inflater.inflate(R.layout.fragment_subjects__all, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory_list= Subject_Handler_Factory((requireNotNull(this.activity).application))
        viewModel_List= ViewModelProvider(requireActivity(), factory_list).get(Subject_Handler::class.java)
        val subjectListAdapter= Subject_List_Adapter(viewModel_List.subjects,viewModel_List, this.requireContext())
        viewModel_List.subjects.observe(viewLifecycleOwner,{subjectListAdapter.notifyDataSetChanged()})
        val layoutManager= LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.subject_recyclerView).let{
            it.adapter=subjectListAdapter
            it.layoutManager=layoutManager
        }
        view.findViewById<Button>(R.id.Nav_Add_Subject_Button).setOnClickListener{

            it.findNavController().navigate(R.id.Action_Subject_List_To_Add_Subject)

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
         * @return A new instance of fragment Subjects_All.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Subjects_All().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}