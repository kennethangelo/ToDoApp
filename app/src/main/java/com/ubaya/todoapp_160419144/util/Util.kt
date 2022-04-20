package com.ubaya.todoapp_160419144.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubaya.todoapp_160419144.model.TodoDatabase

val DB_NAME= "newtododb"

fun buildDB(context: Context):TodoDatabase{
    //Add all migration policies into the builder (separate with Comma)
    val db = Room.databaseBuilder(
        context, TodoDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
        .build()
    return db
}

//Means this migration is used for upgrading the DB from ver. 1 to 2
val MIGRATION_1_2 = object: Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase){
        //New priority column added. Integer type.
        //Because it must have value -> add the "DEFAULT 3 (priority'll set to 3 for the old data) NOT NULL"
        database.execSQL(
            "ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL"
        )
    }
}

//Means this migration is used for upgrading the DB from ver. 2 to 3
val MIGRATION_2_3 = object: Migration(2,3){
    override fun migrate(database: SupportSQLiteDatabase){
        //New priority column added. Integer type.
        //Because it must have value -> add the "DEFAULT 3 (is_done'll set to 0 for the old data) NOT NULL"
        database.execSQL(
            "ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL"
        )
    }
}