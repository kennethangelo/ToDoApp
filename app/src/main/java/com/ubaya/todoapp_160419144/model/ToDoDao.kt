package com.ubaya.todoapp_160419144.model

import androidx.room.*

@Dao //Marks the class as Data Access Object
//Main classes to define database interactions
//Can include a variety of query methods
interface ToDoDao {
    //Insert its parameters into the database
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    //Function that can be paused and resumed at a later time
    suspend fun insertAll(vararg todo:Todo)

    //This query is verified at compile time by Room to ensure that it compiles fine against the database
    @Query("SELECT * FROM todo")
    suspend fun selectAllToDo():List<Todo>

    //Arguments of the method will be bound to the bind arguments in the
    //SQL statement
    @Query("SELECT * FROM todo WHERE uuid = :id")
    suspend fun selectTodo(id:Int):Todo

    @Delete
    suspend fun deleteTodo(todo:Todo)
}