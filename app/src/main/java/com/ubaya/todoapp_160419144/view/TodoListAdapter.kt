package com.ubaya.todoapp_160419144.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.todoapp_160419144.R
import com.ubaya.todoapp_160419144.databinding.TodoItemLayoutBinding
import com.ubaya.todoapp_160419144.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(
    val todoList: ArrayList<Todo>,
    val adapterOnClick: (Todo) -> Unit)
    : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(), TodoCheckedChangeListener, TodoEditClick{

    class TodoViewHolder(var view: TodoItemLayoutBinding): RecyclerView.ViewHolder(view.root)
//    class TodoViewHolder(var view: View): RecyclerView.ViewHolder(view)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoListAdapter.TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.todo_item_layout, parent,false)
        //Replace the original inflate layout with DataBindingUtil inflation method
        //Therefore the view obj now can access any variable defined in layout
        val view = DataBindingUtil.inflate<TodoItemLayoutBinding>(inflater, R.layout.todo_item_layout, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListAdapter.TodoViewHolder, position: Int) {
        //Access particular to do obj based on position and set it into the variable to do in view
        holder.view.todo = todoList[position]
        //Important to instanstiate the listener
        //Refer to the adapter obj => alteady implement the interface for itself
        holder.view.listener = this
        holder.view.editListener = this
//        holder.view.checkTask.setText(todoList[position].title.toString())
//        holder.view.imgEdit.setOnClickListener{
//            //This code is used to setup the listener whenever user clicks on pencil edit button.
//            //It navigates to EditToDoFragment
//            //Also passes the args of selected uuid
//            val action = ToDoListFragmentDirections.actionEditToDoFragment(todoList[position].uuid)
//            Navigation.findNavController(it).navigate(action)
//        }
//        holder.view.checkTask.setOnCheckedChangeListener{ compoundButton, isChecked ->
//            //If the task is checked => should be removed from database
//           if(isChecked == true){
//               adapterOnClick(todoList[position])
//           }
//        }
    }

    override fun onCheckChanges(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked){
            adapterOnClick(obj)
        }
    }

    override fun onTodoEditCLick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = ToDoListFragmentDirections.actionEditToDoFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }

    fun updateTodoList(newTodoList: List<Todo>){
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}