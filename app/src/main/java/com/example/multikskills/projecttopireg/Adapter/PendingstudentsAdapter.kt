package com.example.multikskills.projecttopireg.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.multikskills.projecttopireg.AdminAcceptAndReject_Activivty
import com.example.multikskills.projecttopireg.MainMVP
import com.example.multikskills.projecttopireg.Model.PendingClass
import com.example.multikskills.projecttopireg.R


class PendingstudentsAdapter(private val userList: ArrayList<PendingClass>,private val mContext: Context) : RecyclerView.Adapter<PendingstudentsAdapter.ViewHolder>()  {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingstudentsAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.listofpendingstudents, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: PendingstudentsAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList[position])

    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: PendingClass) {
            val textViewName = itemView.findViewById(R.id.pendingstudentsnamelist) as TextView
            textViewName.text = user.fullname
          itemView.setOnClickListener {
              val i = Intent(itemView.context,AdminAcceptAndReject_Activivty::class.java)
              // pass data though intent using puExtra
              i.putExtra("id", user.id)
              i.putExtra("fullname", user.fullname)
              i.putExtra("statement",user.statement )
              i.putExtra("motivation",user.motivation )
              i.putExtra("imgurl",user.imgurl )
              i.putExtra("methodology",user.methodology )
              i.putExtra("objective",user.objective )
              i.putExtra("significance",user.significance )
              i.putExtra("regno",user.regno )
              i.putExtra("topic",user.topic )
              itemView.context.startActivity(i)
          }
        }
    }
}
