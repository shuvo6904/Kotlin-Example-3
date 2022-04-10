package com.example.kotlinexample3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinexample3.R
import com.example.kotlinexample3.model.User

class RecyclerViewAdapter(val clickListener : OnItemClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var userList = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.itemView.setOnClickListener {
            clickListener.onItemEditClick(userList[position])
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val tvName = view.findViewById<TextView>(R.id.nameId)
        val tvEmail = view.findViewById<TextView>(R.id.emailId)
        val tvGender = view.findViewById<TextView>(R.id.genderId)
        val tvStatus = view.findViewById<TextView>(R.id.statusId)

        fun bind(data : User){

            tvName.text = data.name
            tvEmail.text = data.email
            tvGender.text = data.gender
            tvStatus.text = data.status

        }

    }


    interface OnItemClickListener{

        fun onItemEditClick(user : User)

    }
}
























