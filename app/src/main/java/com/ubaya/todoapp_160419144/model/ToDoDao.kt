package com.ubaya.todoapp_160419144.model

import androidx.room.*

@Dao //Marks the class as Data Access Object
interface ToDoDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo:Todo)

    @Query("SELECT * FROM todo")
    suspend fun selectAllToDo():List<Todo>

    @Query("SELECT * FROM todo WHERE uuid = :id")
    suspend fun selectTodo(id:Int):Todo

    @Delete
    suspend fun deleteTodo(todo:Todo)
}