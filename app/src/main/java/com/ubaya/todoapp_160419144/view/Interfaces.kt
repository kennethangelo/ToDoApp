package com.ubaya.todoapp_160419144.view

import android.view.View
import android.widget.CompoundButton
import com.ubaya.todoapp_160419144.model.Todo

interface TodoCheckedChangeListener{
    fun onCheckChanges(cb: CompoundButton,
    isChecked:Boolean,
    obj:Todo)
}

interface TodoEditClick{
    fun onTodoEditCLick(v: View)
}

interface RadioClick{
    fun onRadioClick(v:View, priority:Int, obj: Todo)
}

interface TodoSaveChangesClick{
    fun onTodoSaveChangesClick(v: View, obj: Todo)
}