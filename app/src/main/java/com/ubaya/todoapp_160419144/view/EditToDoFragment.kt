package com.ubaya.todoapp_160419144.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.todoapp_160419144.R
import com.ubaya.todoapp_160419144.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_to_do.*

class EditToDoFragment : Fragment() {
    private lateinit var viewModel: DetailTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_to_do, container, false)
    }

    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer{
            //Populate the loaded Todo into edit text title and notes
            txtTitle.setText(it.title)
            txtNotes.setText(it.notes)
            //Populate the radio based on priority field
            when (it.priority){
                1 -> rdoLow.isChecked = true
                2 -> rdoMedium.isChecked = true
                3 -> rdoHigh.isChecked = true
            }
        })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        //Change the UI
        txtToDoTitle.text = "Edit To Do"
        btnAdd.text = "Save Changes"

        //Load the data
        //Read UUID sent from the adapter
        val uuid = EditToDoFragmentArgs.fromBundle(requireArguments()).uuid
        //Fetch from LiveModel with UUID supplied as parameter
        viewModel.fetch(uuid)
        //Observe the LiveData (ToDo)
        observeViewModel()

        btnAdd.setOnClickListener {
            val radio = view.findViewById<RadioButton>(rdoPriority.checkedRadioButtonId)
            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(), radio.tag.toString().toInt(), uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
}