package com.example.centerapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.centerapp.models.Appointment

class ListRecepAdapter(var appointmentsList: List<Appointment>) :
    RecyclerView.Adapter<ListRecepAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_recep_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment = appointmentsList[position]
        holder.patientName.text = appointment.patient.fullName
        holder.vaccineName.text = appointment.vaccine.name
        holder.appointmentTime.text = appointment.appointmentDate.toLocaleString()
        if (appointment.forToday) holder.view?.setBackgroundColor(Color.GREEN)
    }

    override fun getItemCount(): Int {
        return appointmentsList.size
    }

    inner class ViewHolder(val view: View?) : RecyclerView.ViewHolder(view!!) {
        val patientName: TextView = itemView.findViewById(R.id.patientName)
        val vaccineName: TextView = itemView.findViewById(R.id.vaccineName)
        val appointmentTime: TextView = itemView.findViewById(R.id.appointmentTime)

    }
}