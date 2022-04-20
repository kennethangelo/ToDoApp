package com.ubaya.todoapp_160419144.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.ubaya.todoapp_160419144.model.Todo
import com.ubaya.todoapp_160419144.model.TodoDatabase
import com.ubaya.todoapp_160419144.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val todoLD = MutableLiveData<Todo>()
    fun addTodo(list: List<Todo>){
        launch{
            val db = buildDB(getApplication())
            //*list => convert individual element of list into its individual obj (Todo)
            //and set it as separated param
            db.todoDao().insertAll(*list.toTypedArray())
        }
    }

    fun fetch(uuid:Int){
        launch{
            val db = buildDB(getApplication())
            //This fetch function use selectToDo from DAO.
            //Will load a single To do and returns it.
            todoLD.value = db.todoDao().selectTodo(uuid)
        }
    }

    fun update(title:String, notes:String, priority:Int, uuid:Int){
        launch{
            val db = buildDB(getApplication())
            //Save to do changes
            db.todoDao().update(title, notes, priority, uuid)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}