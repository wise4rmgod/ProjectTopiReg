package com.example.multikskills.projecttopireg


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
    private val mjournal = ArrayList<PendingClass>()
    lateinit var recyclerView: RecyclerView
    private val db = FirebaseFirestore.getInstance()
    lateinit var mAdapter: PendingstudentsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending_students)
        recyclerView = findViewById(R.id.recyclerviewpending)


        //initialize com.example.multikskills.projecttopireg.Adapter
        mAdapter = PendingstudentsAdapter(applicationContext, mjournal)
        //set layout manager to recycler view
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        val decoration = DividerItemDecoration(applicationContext, VERTICAL)
        recyclerView.addItemDecoration(decoration)
        //set com.example.multikskills.projecttopireg.Adapter to recycler view
        recyclerView.adapter = mAdapter
        //  FirebaseCrash.log("App Started");
        preparepending()
    }

    override fun onResume() {
        super.onResume()
        preparepending()
    }

    fun preparepending(){
        mjournal.clear()
        db.collection("students")
                .addSnapshotListener(EventListener { documentSnapshots, e ->
                    if (e != null) {
                        //  Log.e(TAG, "Listen failed!", e)
                        return@EventListener
                    }

                     val studentList = mutableListOf<PendingClass>()

                    for (doc in documentSnapshots) {

                      //  mjournal.add(PendingClass(doc.getString("email").toString()))

                        Toast.makeText(applicationContext, doc.getString("email"), Toast.LENGTH_SHORT).show()
                        val note = doc.toObject(PendingClass::class.java)

                        mjournal.add(note)

                        //notify data changed to recyclerView
                        mAdapter.notifyDataSetChanged()

                        Log.d("TAG", "Joklinztech: " + doc.data + doc.getString("email"))
                    }
                })
                }

    }

