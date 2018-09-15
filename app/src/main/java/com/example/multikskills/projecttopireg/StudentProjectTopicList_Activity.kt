package com.example.multikskills.projecttopireg

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.multikskills.projecttopireg.Adapter.PendingstudentsAdapter
import com.example.multikskills.projecttopireg.Adapter.StudentProjectList_Adapter
import com.example.multikskills.projecttopireg.Model.AcceptedClass
import com.example.multikskills.projecttopireg.Model.PendingClass
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore

class StudentProjectTopicList_Activity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_project_topic_list_)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewstudent)
        //set layout manager to recycler view
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

         var id=intent.getStringExtra("id")

        db.collection("projecttopic").whereEqualTo("id",id)
                .addSnapshotListener(EventListener { documentSnapshots, e ->
                    if (e != null) {
                        Log.e("TAG", "Listen failed!", e)
                        return@EventListener
                    }

                    val users = ArrayList<AcceptedClass>()
                    for (dc in documentSnapshots) {

                        // users.add(PendingClass(dc.document.getString("email"),"","","","","", ""))
                        var test=  dc.toObject(AcceptedClass::class.java)

                        //  Toast.makeText(applicationContext, test.toString(), Toast.LENGTH_SHORT).show()
                        // dc.document.getString("email")
                        users.add(test)


                        val adapter = StudentProjectList_Adapter(users,applicationContext)

                        //now adding the adapter to recyclerview
                        recyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()
                        //  Toast.makeText(applicationContext,dc.document.getString("email"), Toast.LENGTH_SHORT).show()
                    }

                    //creating our adapter
                })


    }
}
