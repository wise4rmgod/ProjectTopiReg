package com.example.multikskills.projecttopireg

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_admin_.*
import kotlinx.android.synthetic.main.content_admin_.*

class Admin_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_)
        setSupportActionBar(toolbar)
        var act=supportActionBar
        if (null != act){
            act.hide()
        }
        pendingstudents.setOnClickListener {
            var i=Intent(this,PendingStudents::class.java)
            startActivity(i)
        }
    }

}
