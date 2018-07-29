package com.example.multikskills.projecttopireg.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
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
        holder.itemView.setOnClickListener{

            val i = Intent(mContext,AdminAcceptAndReject_Activivty::class.java)
            //pass data though intent using puExtra
            //   i.putExtra("title", studentspending.date)
            //  i.putExtra("message", studentspending.email)
            //   i.putExtra("date", studentspending.id)
            //   i.putExtra("key", journals.getKey())
            mContext.startActivity(i)


        }

        //Click listener of button delete
        /**     holder.delete.setOnClickListener {

        //point databaseReference to Movies
        database = FirebaseDatabase.getInstance().getReference("journal")
        //remove value of child movie.getKey()
        database.child(journals.getKey()).setValue(null)
        //remove item from list
        journal.removeAt(position)
        //notofy datachanged to adapter
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, journal.size)
        Toast.makeText(mContext, "Item Deleted", Toast.LENGTH_SHORT).show()

        } **/


        //Click listener of Button Edit
        /**    holder.edit.setOnClickListener {

        val i = Intent(mContext, EditEntryActivity::class.java)
        //pass data though intent using puExtra
        i.putExtra("title", journals.getTitle())
        i.putExtra("message", journals.getMessage())
        i.putExtra("date", journals.getDate())
        i.putExtra("key", journals.getKey())
        mContext.startActivity(i)
        }
         **/

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

        }
    }
}
