package com.ubaya.todoapp_160419144.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.ubaya.todoapp_160419144.model.Todo
import com.ubaya.todoapp_160419144.model.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()

    fun addTodo(list: List<Todo>){
        launch{
            val db = Room.databaseBuilder(
                getApplication(), TodoDatabase::class.java, "newtododb"
            ).build()
            //*list => convert individual element of list into its individual obj (Todo)
            //and set it as separated param
            db.todoDao().insertAll(*list.toTypedArray())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}