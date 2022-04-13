package com.ubaya.todoapp_160419144.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity //Will create todo table in database
data class Todo(
@ColumnInfo(name="title")
    var title:String,
@ColumnInfo(name="notes")
    var notes:String
    ){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
