package com.example.taskproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskproject.R
import com.example.taskproject.models.Person

internal class PersonAdapter(context: Context, var personList : List<Person>) :
RecyclerView.Adapter<PersonAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: PersonAdapter.ViewHolder, position: Int) {
        val person = personList[position]
        holder.txtName.text = person.name

    }

    internal  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var txtName : TextView = view.findViewById(R.id.txtName)
    }
}