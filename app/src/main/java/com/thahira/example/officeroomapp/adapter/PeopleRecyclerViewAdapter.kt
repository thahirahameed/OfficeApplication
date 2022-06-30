package com.thahira.example.officeroomapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thahira.example.officeroomapp.R
import com.thahira.example.officeroomapp.model.PeopleItem


class PeopleRecyclerViewAdapter (
    private val listOfPeople: MutableList<PeopleItem> = mutableListOf()
    ) : RecyclerView.Adapter<PeopleViewHolder>(){

    fun setPeopleData(people: List<PeopleItem>)
    {
        listOfPeople.clear()
        listOfPeople.addAll(people)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {

        LayoutInflater.from(parent.context)
            .inflate(R.layout.people_recycler,parent,false)
            .apply {
                return  PeopleViewHolder(this)
            }
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
       val people = listOfPeople[position]
        holder.setInformationToTheViewHolder(people)
    }

    override fun getItemCount(): Int = listOfPeople.size
}

class PeopleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.name)
    private val email: TextView = itemView.findViewById(R.id.email)
    private val post: TextView = itemView.findViewById(R.id.post)
    private val avatar: ImageView = itemView.findViewById(R.id.avatar)

    fun setInformationToTheViewHolder(peopleItem: PeopleItem){
        name.text= peopleItem.firstName + peopleItem.lastName
        email.text = peopleItem.email
        post.text = peopleItem.jobtitle

        Glide.with(itemView)
            .load(peopleItem.avatar)
            .override(200,200)
            .centerCrop()
            .into(avatar)
    }
}
