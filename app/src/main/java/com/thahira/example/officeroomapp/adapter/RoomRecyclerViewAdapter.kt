package com.thahira.example.officeroomapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.thahira.example.officeroomapp.R
import com.thahira.example.officeroomapp.model.RoomsItem

class RoomRecyclerViewAdapter(
    private val listOfRooms: MutableList<RoomsItem> = mutableListOf()
): RecyclerView.Adapter<RoomRecyclerViewAdapter.RoomViewHolder>() {

    fun setRoomData(room: List<RoomsItem>) {
        listOfRooms.clear()
        listOfRooms.addAll(room)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.room_recycler, parent, false)
            .apply {
                return RoomViewHolder(this)
            }
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = listOfRooms[position]

        holder.setInformationToRoomViewHolder(room)
    }

    override fun getItemCount(): Int = listOfRooms.size


    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val rootView: View = itemView
        private val roomId: TextView = itemView.findViewById(R.id.room_id)
        private val occupancy: TextView = itemView.findViewById(R.id.maxOccupancy)
        private val occupied: Button = itemView.findViewById(R.id.occupied)


        fun setInformationToRoomViewHolder(roomItem: RoomsItem) {
            roomId.text = roomItem.id
            occupancy.text = "Max Occupancy: "+roomItem.maxOccupancy.toString()
            var isOccupied = roomItem.isOccupied

            occupied.setOnClickListener {
                if (isOccupied) {
                    isOccupied = false
                    occupied.text = "UNOCCUPIED"
                    occupied.setBackgroundColor(
                        ContextCompat.getColor(
                            rootView.context,
                            R.color.purple_700
                        )
                    )
                } else {
                    isOccupied = true
                    occupied.text = "OCCUPIED"
                    occupied.setBackgroundColor(
                        ContextCompat.getColor(
                            rootView.context,
                            R.color.black
                        )
                    )
                }

            }
        }

    }

}