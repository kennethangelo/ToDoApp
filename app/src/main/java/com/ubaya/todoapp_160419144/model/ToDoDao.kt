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
    @Query("SELECT * FROM todo WHERE is_done = 0 ORDER BY priority DESC")
    suspend fun selectAllToDo():List<Todo>

    //Arguments of the method will be bound to the bind arguments in the
    //SQL statement
    @Query("SELECT * FROM todo WHERE uuid = :id")
    suspend fun selectTodo(id:Int):Todo

    //Query for ToDo Data
    @Query("UPDATE todo SET title=:title, notes=:notes, priority=:priority WHERE uuid=:id")
    suspend fun update(title:String, notes:String, priority:Int, id:Int)

    //Query when user checks the list
    @Query("UPDATE todo SET is_done = 1 WHERE uuid = :id")
    suspend fun checkToDo(id:Int)

    @Delete
    suspend fun deleteTodo(todo:Todo)
}