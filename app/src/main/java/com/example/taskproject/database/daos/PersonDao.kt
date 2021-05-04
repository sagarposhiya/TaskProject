package com.example.taskproject.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskproject.models.Person

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertData(loginTableModel: Person)

    @Query("SELECT * FROM Person")
    fun getPersons() : LiveData<List<Person>>


}