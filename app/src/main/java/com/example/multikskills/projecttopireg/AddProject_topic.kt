package com.example.multikskills.projecttopireg

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class AddProject_topic : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_project_topic)
        var act=supportActionBar
        if (null != act){
            act.hide()
        }
      //  GlideApp.with(this).load("http://goo.gl/gEgYUd").into(imageView);
    }
}
