package com.example.madlevel2example.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2example.R
import com.example.madlevel2example.databinding.ItemReminderBinding
import com.example.madlevel2example.model.ReminderModel

class ReminderAdapter(private val reminders: List<ReminderModel>) :
    RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context


        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_reminder, parent, false)
        )
    }


    override fun getItemCount(): Int {
        return reminders.size

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(reminders[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemReminderBinding.bind(itemView)

        fun databind(reminder: ReminderModel) {
            binding.tvReminder.text = reminder.reminderText
        }
    }
}