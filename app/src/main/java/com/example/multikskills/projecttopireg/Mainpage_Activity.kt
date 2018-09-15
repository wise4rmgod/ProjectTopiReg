package com.example.multikskills.projecttopireg

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.NonNull
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask

import kotlinx.android.synthetic.main.activity_mainpage_.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_mainpage_.*
import java.io.IOException

class Mainpage_Activity : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    var PICK_IMAGE_REQUEST = 111
    private var fileUri: Uri? = null
    private var bitmap: Bitmap? = null
    var pd: ProgressDialog? = null
    private var mauth = FirebaseAuth.getInstance()
    //creating reference to firebase storage
    var storage = FirebaseStorage.getInstance()

    var storageRef = storage.reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainpage_)
        setSupportActionBar(toolbar)
        val blink = AnimationUtils.loadAnimation(applicationContext,
                R.anim.blink)
        pendingtext.startAnimation(blink)
        val zoom= AnimationUtils.loadAnimation(applicationContext,R.anim.zoom)
        val fadein=AnimationUtils.loadAnimation(applicationContext,R.anim.abc_fade_out)
        pd = ProgressDialog(this)
        pd?.setMessage("Uploading....")

        var emailintent= intent.getStringExtra("email")
        var idintent=intent.getStringExtra("id")

        // fetches the students fullname

                 /**   db.collection("projecttopic").document(mauth.currentUser!!.uid).collection("topic").whereEqualTo("status", "accepted")
                            .get()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    var doc = task.result
                                    for (dc in doc) {

                                        pendingtext.text = dc.getString("status")
                                    }
                                }
                            }  **/
                    choseimg.setOnClickListener {

                        val intent = Intent()
                        intent.setType("image/*")
                        intent.setAction(Intent.ACTION_PICK)
                        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST)
                    }
                    upload.setOnClickListener {
                        var id = intent.getStringExtra("id")
                        if (fileUri != null) {
                            pd?.show()
                            val childRef = storageRef.child(id)
                            //uploading the image
                            val uploadTask = childRef.putFile(fileUri!!)
                            uploadTask.addOnSuccessListener(object : OnSuccessListener<UploadTask.TaskSnapshot> {
                                override fun onSuccess(taskSnapshot: UploadTask.TaskSnapshot) {

                                    pd?.dismiss()
                                    var users = HashMap<String, Any>()
                                    users.put("imgurl", taskSnapshot.downloadUrl.toString())

                                    db.collection("students").document(id).update(users)
                                            .addOnSuccessListener {
                                                Toast.makeText(this@Mainpage_Activity, "Upload successful", Toast.LENGTH_SHORT).show()


                                                upload.startAnimation(fadein)
                                                choseimg.startAnimation(fadein)
                                                imageupload.startAnimation(fadein)
                                                upload.visibility = View.GONE
                                                choseimg.visibility = View.GONE
                                                imageupload.visibility = View.GONE
                                                fab.visibility = View.VISIBLE
                                                pendingtext.visibility = View.VISIBLE
                                            }
                                            .addOnFailureListener {
                                                Toast.makeText(this@Mainpage_Activity, "Registration not successful", Toast.LENGTH_SHORT).show()
                                            }

                                }
                            }).addOnFailureListener(object : OnFailureListener {
                                override fun onFailure(@NonNull e: Exception) {
                                    pd?.dismiss()
                                    Toast.makeText(this@Mainpage_Activity, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show()
                                }
                            })
                        } else {
                            Toast.makeText(this, "Select an image", Toast.LENGTH_SHORT).show()
                        }
                    }


                    fab.setOnClickListener { view ->
                        var emailintent= intent.getStringExtra("email")
                        var idintent=intent.getStringExtra("id")
                        val i = Intent(this, AddProject_topic::class.java)
                        i.putExtra("id", idintent)
                        i.putExtra("email", emailintent)
                        startActivity(i)
                    }
                }


override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (bitmap != null) {
        bitmap!!.recycle()
    }

    if (requestCode == PICK_IMAGE_REQUEST && resultCode == AppCompatActivity.RESULT_OK && data != null && data.data != null) {
        fileUri = data.data
        try {
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, fileUri)
            imageupload.setImageBitmap(bitmap)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_mainpage, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.action_settings) {
            mauth.signOut()
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show()
            var i=Intent(this,MainActivity::class.java)
            startActivity(i)
            finish()

        }
        if(id == R.id.supervisor){
            var id= intent.getStringExtra("id")
            val i = Intent(this, StudentProjectTopicList_Activity::class.java)
            i.putExtra("id", id)
            startActivity(i)
        }

        if(id == R.id.schoolfees){
            val fadein=AnimationUtils.loadAnimation(applicationContext,R.anim.abc_fade_out)
            fab.startAnimation(fadein)
            pendingtext.startAnimation(fadein)
            fab.visibility = View.GONE
            pendingtext.visibility = View.GONE
            upload.visibility = View.VISIBLE
            choseimg.visibility = View.VISIBLE
            imageupload.visibility = View.VISIBLE
        }
        return super.onOptionsItemSelected(item)
    }
}
