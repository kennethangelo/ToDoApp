package com.ubaya.todoapp_160419144.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.todoapp_160419144.R
import com.ubaya.todoapp_160419144.model.Todo
import com.ubaya.todoapp_160419144.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_to_do.*

class CreateToDoFragment : Fragment() {
    private lateinit var viewModel:DetailTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_to_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        btnAdd.setOnClickListener {
            var todo = Todo(txtTitle.text.toString(), txtNotes.text.toString())
            val list = listOf(todo)
            viewModel.addTodo(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_SHORT)
            Navigation.findNavController(it).popBackStack()
        }
    }
}