package com.example.madlevel2example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2example.adapter.ReminderAdapter
import com.example.madlevel2example.databinding.ActivityMainBinding
import com.example.madlevel2example.model.ReminderModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val reminders = arrayListOf<ReminderModel>()
    private val reminderAdapter = ReminderAdapter(reminders)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initViews()
    }

    private fun initViews() {
        binding.btnAddReminder.setOnClickListener() {
            val reminder = binding.etReminder.text.toString()
            addReminder(reminder)
        }
        rvReminders.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)

        rvReminders.adapter = reminderAdapter

        rvReminders.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        createItemTouchHelper().attachToRecyclerView(rvReminders)
    }


    private fun addReminder(reminder: String) {
        if (reminder.isNotBlank()) {
            val reminderModel = ReminderModel(reminder)
            reminders.add(reminderModel)
            reminderAdapter.notifyDataSetChanged()
            etReminder.text?.clear()
        } else {
            Snackbar.make(etReminder, "you must fill in the input field", Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                reminders.removeAt(position)
                reminderAdapter.notifyDataSetChanged()
            }

        }

        return ItemTouchHelper(callback)

    }
}