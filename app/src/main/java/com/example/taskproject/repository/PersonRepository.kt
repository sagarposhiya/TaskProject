package com.example.taskproject.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.taskproject.database.AppDatabase
import com.example.taskproject.models.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Appendable

class PersonRepository {

    companion object{

        var appDatabase : AppDatabase? = null

        var person : LiveData<List<Person>>? = null

        fun initDatabase(context: Context) : AppDatabase{
            return AppDatabase.getDataseClient(context)
        }

        fun insertPerson(context: Context,name : String){
            appDatabase = initDatabase(context)

            CoroutineScope(IO).launch {
                val person = Person(name)
                appDatabase!!.personDao().insertData(person)
            }
        }


        fun getPerson(context: Context) : LiveData<List<Person>> {
                appDatabase = initDatabase(context)

                person = appDatabase!!.personDao().getPersons();

            return person as LiveData<List<Person>>
        }
    }
}