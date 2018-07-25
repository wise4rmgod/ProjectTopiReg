package com.example.multikskills.projecttopireg.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.multikskills.projecttopireg.AdminAcceptAndReject_Activivty
import com.example.multikskills.projecttopireg.MainMVP
import com.example.multikskills.projecttopireg.Model.PendingClass
import com.example.multikskills.projecttopireg.R


class PendingstudentsAdapter (
                    private val studentsList: MutableList<PendingClass>,
                    private val context: Context,
                    private val firestoreDB: FirebaseFirestore)
                : RecyclerView.Adapter<PendingstudentsAdapter.ViewHolder>(){
      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingstudentsAdapter.ViewHolder {
          val view = LayoutInflater.from(parent!!.context).inflate(R.layout.listofpendingstudents, parent, false)

          return ViewHolder(view)
      }

      override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          val student = studentsList[position]

          holder!!.pendingstudentsnamelist.text = student.pendingstudentsnamelist
       //   holder.regnum.text = student.regnum
        //  holder.score.text = student.Score

          holder.view.setOnClickListener {

              val i = Intent(context, AdminAcceptAndReject_Activivty::class.java)
              //pass data though intent using puExtra
              //   i.putExtra("title", studentspending.date)
              //  i.putExtra("message", studentspending.email)
              //   i.putExtra("date", studentspending.id)
              //   i.putExtra("key", journals.getKey())
              mContext.startActivity(i)


          }

          //holder.fullname.setOnClickListener { veiwMessage(student) }

      }

      override fun getItemCount(): Int {
          return studentsList.size
      }


      inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
          internal var email: TextView
        //  internal var regnum: TextView
        //  internal var score: TextView


          init {
              email = view.findViewById(R.id.pendingstudentsnamelist)
       //       regnum = view.findViewById(R.id.tv_sheet_regnumber)
        //      score = view.findViewById(R.id.tv_sheet_Score)


          }
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

