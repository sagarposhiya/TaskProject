package com.example.taskproject.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.taskproject.models.Person
import com.example.taskproject.repository.PersonRepository

class PersonViewModel : ViewModel() {

 var personLiveDataList :LiveData<List<Person>>? = null

    fun insertData(context: Context, name : String){
        PersonRepository.insertPerson(context,name)
    }

    fun getPersonDetails(context: Context) : LiveData<List<Person>>{
       personLiveDataList =  PersonRepository.getPerson(context)
        return personLiveDataList as LiveData<List<Person>>
    }
}