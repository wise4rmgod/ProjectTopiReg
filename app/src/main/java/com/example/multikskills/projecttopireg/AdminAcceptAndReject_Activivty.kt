package com.example.multikskills.projecttopireg

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_admin_accept_and_reject__activivty.*

class AdminAcceptAndReject_Activivty : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    var pd: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_accept_and_reject__activivty)

        var id=intent.getStringExtra("id")
        var fullname=intent.getStringExtra("fullname")
        var regno=intent.getStringExtra("regno")
        var statement=intent.getStringExtra("statement")
        var objective=intent.getStringExtra("objective")
        var imgurl=intent.getStringExtra("imgurl")
        var motivation=intent.getStringExtra("motivation")
        var methodology=intent.getStringExtra("methodology")
        var significance=intent.getStringExtra("significance")
        var topic=intent.getStringExtra("topic")


        acceptfullname.text=fullname
        acceptmethodology.text=methodology
        acceptmotivation.text=motivation
        acceptobjective.text=objective
        acceptregno.text=regno
        acceptsignificance.text=significance
        acceptstatement.text=statement
        accepttopic.text=topic

        Glide.with(this)
                .load(imgurl)
                //.centerCrop()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .into(projectimg)

        pd = ProgressDialog(this)
        pd?.setMessage("Loading ....")


        acceptbtn.setOnClickListener {
            pd?.show()
            var users=HashMap<String,Any>()
            users.put("status","accepted")
            db.collection("projecttopic").document(topic).update(users)
                    .addOnSuccessListener {
                        pd?.hide()
                        Toast.makeText(this,"Project topic accepted", Toast.LENGTH_SHORT).show()
                        var adminpage=Intent(this,Admin_Activity::class.java)
                        startActivity(adminpage)
                        finish()

                    }
                    .addOnFailureListener {
                        pd?.hide()
                        Toast.makeText(this,"Invalid projet topic", Toast.LENGTH_SHORT).show()
                    }
        }

        declinetbtn.setOnClickListener {

            pd?.show()
            var users=HashMap<String,Any>()
            users.put("status","decline")
            db.collection("projecttopic").document(topic).update(users)
                    .addOnSuccessListener {
                        pd?.hide()
                        Toast.makeText(this,"Project topic rejected", Toast.LENGTH_SHORT).show()
                        var adminpage=Intent(this,Admin_Activity::class.java)
                        startActivity(adminpage)
                        finish()


                    }
                    .addOnFailureListener {
                        pd?.hide()
                        Toast.makeText(this,"Invalid projet topic", Toast.LENGTH_SHORT).show()
                    }
        }

    }
}
