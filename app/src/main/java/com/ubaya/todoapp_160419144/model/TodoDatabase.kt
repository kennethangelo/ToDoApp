package com.ubaya.todoapp_160419144.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Todo::class), version = 1)
//Marks a class as a RoomDatabase
//Entities config is used to put entity (table) inside database.
//Version is used for migration purpose
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): ToDoDao

    companion object{
        //Writes to this field are immediately made visible to other threads
        @Volatile private var instance: TodoDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                TodoDatabase::class.java,
                //DB builder requires context, database class, and database name as String
                "newtododb").build()

        operator fun invoke(context:Context){
            if(instance != null){ //restricts the instantiation of a class to one "single" interface
                synchronized(LOCK){
                    //A thread that enters a sync method obtains a lock (an obj being locked = instance of the containing class)
                    //and no other thread can enter the method until the lock is released
                    instance ?: buildDatabase(context).also{
                        instance = it
                    }
                }
            }
        }
    }
}