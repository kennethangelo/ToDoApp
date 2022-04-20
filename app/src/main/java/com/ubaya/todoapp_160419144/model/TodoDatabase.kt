package com.ubaya.todoapp_160419144.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.todoapp_160419144.util.MIGRATION_1_2
import com.ubaya.todoapp_160419144.util.MIGRATION_2_3

//INCREASE DB VERSION BY 1, EVERY TIME YOU CHANGE THE DATA SCHEME!
@Database(entities = arrayOf(Todo::class), version = 3)
//Marks a class as a RoomDatabase
//Entities config is used to put entity (table) inside database.
//Version is used for migration purpose
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): ToDoDao

    companion object{
        //Writes to this field are immediately made visible to other threads
        @Volatile private var instance: TodoDatabase ?= null
        private val LOCK = Any()

        //Add all migration policies into the builder (separate with comma)
        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                TodoDatabase::class.java, "newtododb"
            )
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build()

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