package com.ubaya.todoapp_160419144.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity //Will create todo table in database
data class Todo(
@ColumnInfo(name="title")
    var title:String,
@ColumnInfo(name="notes")
    var notes:String,
@ColumnInfo(name="priority")
    var priority: Int,
@ColumnInfo(name="is_done")
    var is_done: Int, //is_done is set to INT because based on SQLite documentation, it doesn't have a separate Boolean datatype ("True" or "False").
    //That's why we just use Integer as 0 for False, 1 for True.
    ){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
