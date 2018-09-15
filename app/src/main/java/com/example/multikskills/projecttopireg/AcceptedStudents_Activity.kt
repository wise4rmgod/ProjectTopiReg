package com.example.multikskills.projecttopireg

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class AcceptedStudents_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acceptedstudentsactivity)
        var fullname=intent.getStringExtra("fullname")

        Toast.makeText(this,fullname,Toast.LENGTH_SHORT).show()
    }
}
