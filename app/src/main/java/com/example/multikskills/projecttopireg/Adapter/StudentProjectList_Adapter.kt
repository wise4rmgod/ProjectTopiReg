package com.example.multikskills.projecttopireg.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.multikskills.projecttopireg.Model.AcceptedClass
import com.example.multikskills.projecttopireg.R

class StudentProjectList_Adapter (private val userList: ArrayList<AcceptedClass>, private val mContext: Context) : RecyclerView.Adapter<StudentProjectList_Adapter.ViewHolder>()  {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.studentprojecttopiclist, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(userList[position])
        holder.itemView.setOnClickListener{

        /**    val i = Intent(mContext, AdminAcceptAndReject_Activivty::class.java)
            //pass data though intent using puExtra
            i.putExtra("topic", userList[position].topic)
            // i.putExtra("fullname", userList[position].fullname)
            i.putExtra("state", userList[position].statement)
            i.putExtra("status", userList[position].status)
            i.putExtra("obj", userList[position].objective)
            i.putExtra("method", userList[position].methodology)
            i.putExtra("significance", userList[position].significance)
            // i.putExtra("key", userList[position].)
            mContext.startActivity(i)  **/

        }


    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: AcceptedClass) {
            val projectopic = itemView.findViewById(R.id.projectopic) as TextView
            val projectstatus = itemView.findViewById(R.id.projectstatus) as TextView

            projectstatus.text = user.status
            projectopic.text=user.topic

        }
    }
}