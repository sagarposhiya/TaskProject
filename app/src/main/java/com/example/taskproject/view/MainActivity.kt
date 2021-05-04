package com.example.taskproject.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskproject.R
import com.example.taskproject.adapter.PersonAdapter
import com.example.taskproject.viewmodel.PersonViewModel

class MainActivity : AppCompatActivity() {

    lateinit var edName : EditText
    lateinit var btnAdd : Button
    lateinit var rvPerson : RecyclerView
    lateinit var context: Context
    lateinit var personViewModel : PersonViewModel
    lateinit var name : String
    internal lateinit var personAdapter: PersonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getInit()
        setEvents()
    }

    private fun setEvents() {
        btnAdd.setOnClickListener {
            name = edName.text.toString()

            if (name.isEmpty()){
                edName.error = "Please Enter Name"
            } else{
                    personViewModel.insertData(context,name)
                    edName.setText("")
            }
        }


        personViewModel.getPersonDetails(context)!!.observe(this, Observer {
            val size = it.size

            Handler().postDelayed(Runnable {
                Toast.makeText(context, "$size values inserted" , Toast.LENGTH_SHORT).show()
                personAdapter = PersonAdapter(context,it)
                rvPerson.adapter = personAdapter
            },1500)

        })
    }

    private fun getInit() {
        context = this@MainActivity
        personViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)
        edName = findViewById(R.id.edPersonName)
        btnAdd = findViewById(R.id.btnAdd)
        rvPerson = findViewById(R.id.rvPersons)
        val layoutManager = LinearLayoutManager(applicationContext)
        rvPerson.layoutManager = layoutManager
    }

//    private val viewModel: PersonViewModel by lazy {
//        return@lazy when {
//            activity != null -> {
//                ViewModelProviders.of(activity as FragmentActivity).get(PersonViewModel::class.java)
//            }
//            else -> {
//                ViewModelProviders.of(this).get(PersonViewModel::class.java)
//            }
//        }
//    }

    fun Fragment.getViewModelProvider() =
        activity?.let(ViewModelProviders::of) ?: ViewModelProviders.of(this)

    inline fun <reified T : ViewModel> Fragment.getViewModel() =
        getViewModelProvider().get(T::class.java)
}