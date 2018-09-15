package com.example.multikskills.projecttopireg

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.multikskills.projecttopireg.Adapter.Acceptedstudents_Adapter
import com.example.multikskills.projecttopireg.Adapter.PendingstudentsAdapter
import com.example.multikskills.projecttopireg.Model.AcceptedClass
import com.example.multikskills.projecttopireg.Model.PendingClass
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore

class AcceptedStudents : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accepted_students)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewaccepted)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        db.collection("projecttopic").whereEqualTo("status","accepted")
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

                        val adapter = Acceptedstudents_Adapter(users,applicationContext)

                        //now adding the adapter to recyclerview
                        recyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()
                        //  Toast.makeText(applicationContext,dc.document.getString("email"), Toast.LENGTH_SHORT).show()


                    }


                    //creating our adapter
                })
    }
}
