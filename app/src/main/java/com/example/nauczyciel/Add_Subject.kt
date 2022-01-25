package com.example.nauczyciel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import com.example.nauczyciel.entities.Subject
import com.example.nauczyciel.view_model.Student_Handler
import com.example.nauczyciel.view_model.Subject_Handler
import com.example.nauczyciel.view_model.view_model_factiory.Subject_Handler_Factory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Add_Subject.newInstance] factory method to
 * create an instance of this fragment.
 */
class Add_Subject : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModelSubjects: Subject_Handler
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
        return inflater.inflate(R.layout.fragment_add__subject, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factoryAdd= Subject_Handler_Factory((requireNotNull(this.activity).application))
        val spinner: Spinner = view.findViewById<Spinner>(R.id.Spinner_Day_of_Week)
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.Day_Of_Week_Array,
            R.layout.spinner_item
        ).also{adapter->
            //Layout of dropdown menu
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            //Attach adapter to spinner
            spinner.adapter = adapter

        }
        viewModelSubjects= ViewModelProvider(requireActivity(),factoryAdd).get(Subject_Handler::class.java)
        view.findViewById<Button>(R.id.Add_Subject_Button).apply{
            setOnClickListener{

                    var day:String=spinner.selectedItem.toString()

                    val subject= Subject(0L,view.findViewById<EditText>(R.id.editText_Subject_Name).text.toString(),view.findViewById<EditText>(R.id.editText_Subject_SHour).text.toString(),view.findViewById<EditText>(R.id.editText_Subject_EHour).text.toString(),day)
                    viewModelSubjects.Add_Subject(subject)

                //view.findNavController().navigate(R.id.Action_Main_To_Students)
            }

        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Add_Subject.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Add_Subject().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}