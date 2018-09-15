package com.example.multikskills.projecttopireg

import android.app.ProgressDialog
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore


class AddProject_topic : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    var pd: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_project_topic)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        var act=supportActionBar
        if (null != act){
            act.hide()
        }
        pd = ProgressDialog(this)
        pd?.setMessage("Loading ....")

        var project=findViewById<EditText>(R.id.project)
        var statement=findViewById<EditText>(R.id.statement)
        var objective=findViewById<EditText>(R.id.objective)
        var motivation=findViewById<EditText>(R.id.motivation)
        var significance=findViewById<EditText>(R.id.significance)
        var methodology=findViewById<EditText>(R.id.methodology)
        var register=findViewById<Button>(R.id.register)

        var id=intent.getStringExtra("id")

        db.collection("students").document(id).get()
                .addOnCompleteListener { task->
                    if (task.isSuccessful){
                         var u=task.result
                       var getfullname= u.getString("fullname")
                       var getregno= u.getString("regno")
                        var imgurl=u.getString("imgurl")

        register.setOnClickListener {

            if (project.text.toString().trim().isNullOrBlank() && statement.text.toString().trim().isNullOrBlank() && objective.text.toString().trim().isNullOrBlank() &&
                    motivation.text.toString().trim().isNullOrBlank() && significance.text.toString().trim().isNullOrBlank() && methodology.text.toString().trim().isNullOrBlank()) {

                Toast.makeText(this, "Some fields are blank", Toast.LENGTH_SHORT).show()

            }

            else {
                db.collection("projecttopic").document(project.text.toString())
                        .get()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                var u = task.result
                                if (u.exists()) {
                                    Toast.makeText(this, "Project topic already exist", Toast.LENGTH_SHORT).show()
                                } else {
                                    //   progress.show()
                                    pd?.show()
                                    var users = HashMap<String, Any>()
                                    users.put("topic", project.text.toString())
                                    users.put("statement", statement.text.toString().trim())
                                    users.put("objective", objective.text.toString().trim())
                                    users.put("motivation", motivation.text.toString().trim())
                                    users.put("significance", significance.text.toString().trim())
                                    users.put("methodology", methodology.text.toString().trim())
                                    users.put("imgurl",imgurl)
                                    users.put("fullname", getfullname)
                                    users.put("regno", getregno)
                                    users.put("id", id)
                                    users.put("status", "pending")
                                    db.collection("projecttopic").document(project.text.toString()).set(users)
                                            .addOnSuccessListener {
                                                Toast.makeText(this, "Project topic saved successfully", Toast.LENGTH_SHORT).show()
                                                pd?.hide()
                                                project.text = null
                                                statement.text = null
                                                objective.text = null
                                                motivation.text = null
                                                significance.text = null
                                                methodology.text = null

                                            }
                                            .addOnFailureListener {
                                                pd?.hide()
                                                Toast.makeText(this, "Invalid information", Toast.LENGTH_SHORT).show()
                                            }
                                }
                            }
                  }
            }
        }


                    }

                }

          }
      }