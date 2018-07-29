package com.example.multikskills.projecttopireg


import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.widget.RecyclerView
import com.example.multikskills.projecttopireg.Model.PendingClass
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import com.example.multikskills.projecttopireg.Adapter.PendingstudentsAdapter
import com.google.firebase.firestore.*


class PendingStudents : AppCompatActivity() {


    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending_students)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewpending)


        //set layout manager to recycler view
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
       // val decoration = DividerItemDecoration(applicationContext, VERTICAL)
      //  recyclerView.addItemDecoration(decoration)
        //set com.example.multikskills.projecttopireg.Adapter to recycler view

        //  FirebaseCrash.log("App Started");

        db.collection("students")
                .addSnapshotListener(EventListener { documentSnapshots, e ->
                    if (e != null) {
                        Log.e("TAG", "Listen failed!", e)
                        return@EventListener
                    }

                    val users = ArrayList<PendingClass>()
                    for (dc in documentSnapshots) {

                        // users.add(PendingClass(dc.document.getString("email"),"","","","","", ""))
                            var test=  dc.toObject(PendingClass::class.java)


                      //  Toast.makeText(applicationContext, test.toString(), Toast.LENGTH_SHORT).show()
                        // dc.document.getString("email")
                        users.add(test)


                        val adapter = PendingstudentsAdapter(users,applicationContext)

                        //now adding the adapter to recyclerview
                        recyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()
                        //  Toast.makeText(applicationContext,dc.document.getString("email"), Toast.LENGTH_SHORT).show()


                    }


                    //creating our adapter
                })


    }
}

