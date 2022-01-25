package com.example.nauczyciel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.nauczyciel.entities.Student
import com.example.nauczyciel.view_model.Student_Handler
import com.example.nauczyciel.view_model.view_model_factiory.Students_Handler_Factory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Add_Student.newInstance] factory method to
 * create an instance of this fragment.
 */
class Add_Student : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModelStudents:Student_Handler
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
        return inflater.inflate(R.layout.fragment_add__student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factoryAdd=Students_Handler_Factory((requireNotNull(this.activity).application))
        viewModelStudents=ViewModelProvider(requireActivity(),factoryAdd).get(Student_Handler::class.java)
        view.findViewById<Button>(R.id.Add_Student_Button).apply{
            setOnClickListener{
                var id=view.findViewById<EditText>(R.id.editTextID).text.toString()

                if(id!="" ){
                    val student=Student(id,view.findViewById<EditText>(R.id.editTextFirstName).text.toString(),view.findViewById<EditText>(R.id.editTextLastName).text.toString())
                    viewModelStudents.Add_Student(student)
                }
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
         * @return A new instance of fragment Add_Student.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Add_Student().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}