package com.ubaya.todoapp_160419144.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.ubaya.todoapp_160419144.model.Todo
import com.ubaya.todoapp_160419144.model.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    //Coroutine its a way to provide control for coroutine
    val todoLD = MutableLiveData<List<Todo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
        //Dispatches tells you on which thread should I run this block of code (Main, IO, Default)

    fun refresh(){
        loadingLD.value = true
            todoLoadErrorLD.value = false
        //launch => create a job. known as fire and forgets builder
            launch{
                val db = Room.databaseBuilder(
                    getApplication(),
                    TodoDatabase::class.java,
                    "newtododb"
                ).build()
                todoLD.value = db.todoDao().selectAllToDo()
                //todo list livedata is populated with data selected from DB
            }
    }

    fun clearTask(todo: Todo){
        launch{
            val db = Room.databaseBuilder(
                getApplication(),
                TodoDatabase::class.java, "newtododb"
            ).build()
            db.todoDao().deleteTodo(todo)
            //DeleteToDo = clear task simply delete single todo based on the selected obj
            todoLD.value = db.todoDao().selectAllToDo()
        }
    }
}