package com.example.centerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.centerapp.models.Appointment

class PendingAppointmentAdapter(
    private val appointmentsList: List<Appointment>,
    val acceptAction: (Long) -> Unit,
    val declineAction: (Long) -> Unit,
) :
    RecyclerView.Adapter<PendingAppointmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.appointment_action_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment = appointmentsList[position]
        holder.patientName.text = appointment.patient.fullName
        holder.vaccineName.text = appointment.vaccine.name
        holder.appointmentTime.text = appointment.appointmentDate.toLocaleString()
        holder.acceptAppointmentButton.setOnClickListener { acceptAction(appointment.id) }
        holder.declineAppointmentButton.setOnClickListener { declineAction(appointment.id) }
    }

    override fun getItemCount(): Int {
        return appointmentsList.size
    }

    inner class ViewHolder(val view: View?) : RecyclerView.ViewHolder(view!!) {
        val patientName: TextView = itemView.findViewById(R.id.patientName)
        val vaccineName: TextView = itemView.findViewById(R.id.vaccineName)
        val appointmentTime: TextView = itemView.findViewById(R.id.appointmentTime)
        val acceptAppointmentButton: Button = itemView.findViewById(R.id.acceptAppointmentButton)
        val declineAppointmentButton: Button = itemView.findViewById(R.id.declineAppointmentButton)

    }
}