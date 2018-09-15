package com.example.multikskills.projecttopireg

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.view.menu.MenuPresenter
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.example.multikskills.projecttopireg.Presenter.Mainpresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() ,MainMVP.view {
    var mauth = FirebaseAuth.getInstance()
    var db = FirebaseFirestore.getInstance()
    var pd: ProgressDialog? = null
    private var animation1: AnimationUtils?= null
    lateinit var mainpresenter : Mainpresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        mainpresenter = Mainpresenter(this)
        var act = supportActionBar
        if (null != act) {
            act.hide()
        }

        pd = ProgressDialog(this)
        pd?.setMessage("Loading ....")

        button.setOnClickListener {


            if (editText.text.toString().trim().isNullOrBlank() && editText2.text.toString().trim().isNullOrBlank()) {

                Toast.makeText(this, "Fill in the details", Toast.LENGTH_SHORT).show()

            } else {
             //   progress.show()
                pd?.show()
                mauth!!.signInWithEmailAndPassword(editText.text.toString().trim(),
                        editText2.text.toString().trim())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                             //   progress.hide()
                                pd?.dismiss()
                                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                                var ty = Intent(this,Mainpage_Activity::class.java)
                                ty.putExtra("id", mauth.currentUser!!.uid)
                                ty.putExtra("email", mauth.currentUser!!.email)
                                startActivity(ty)

                            } else {
                             //   progress.hide()
                                pd?.dismiss()
                                Toast.makeText(this, "Invalid Credentials Or User Not Registered", Toast.LENGTH_SHORT).show()

                            }
                        }
            }
        }

       buttonsignup.setOnClickListener {


           if (email.text.toString().trim() .isNullOrBlank() && password.text.toString().trim().isNullOrBlank()) {

               Toast.makeText(this, "Fill in the details", Toast.LENGTH_SHORT).show()

           } else {
               //   progress.show()
                   pd?.show()
               mauth!!.createUserWithEmailAndPassword(email.text.toString().trim(),
                       password.text.toString().trim())
                       .addOnCompleteListener(this) { task ->
                           if (task.isSuccessful) {
                               //   progress.hide()
                               pd?.dismiss()
                               Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()

                               var users=HashMap<String,Any>()
                               users.put("email",email.text.toString().trim())
                               users.put("fullname",fullname.text.toString().trim())
                               users.put("password",password.text.toString().trim())
                               users.put("regno",regno.text.toString().trim())
                               users.put("supervisor",supervisor.text.toString().trim())
                               users.put("status","Pending")
                               users.put("id", mauth.currentUser!!.uid)
                               db.collection("students").document(mauth.currentUser!!.uid).set(users)
                                       .addOnSuccessListener {
                                           Toast.makeText(this,"Registration successful",Toast.LENGTH_SHORT).show()
                                           val i = Intent(this, Mainpage_Activity::class.java)
                                           i.putExtra("id", mauth.currentUser!!.uid)
                                           i.putExtra("email", mauth.currentUser!!.email)
                                           startActivity(i)
                                           finish()
                                       }
                                       .addOnFailureListener {
                                           Toast.makeText(this,"Registration not successful",Toast.LENGTH_SHORT).show()
                                       }
                           }
                           else {
                             //  progress1.hide()
                               pd?.dismiss()
                               Toast.makeText(this,"Error User Already Registered / Invalid Email",Toast.LENGTH_SHORT).show()
                           }
                       }
                           }
                       }



        val animation1 = AnimationUtils.loadAnimation(applicationContext,
                R.anim.left_out)
        val rightin = AnimationUtils.loadAnimation(applicationContext,
                R.anim.right_in)
        val rightout = AnimationUtils.loadAnimation(applicationContext,
                R.anim.right_out)
        val animation2 = AnimationUtils.loadAnimation(applicationContext, R.anim.left_in)

        buttonanimationsignup.setOnClickListener {

            editText.startAnimation(animation1)
            editText2.startAnimation(animation1)
            button.startAnimation(animation1)
            editText.visibility = View.GONE
            editText2.visibility = View.GONE
            button.visibility = View.GONE
            password.startAnimation(animation2)
            email.startAnimation(animation2)
            buttonsignup.startAnimation(animation2)
            regno.startAnimation(animation2)
            fullname.startAnimation(animation2)
            email.visibility = View.VISIBLE
            password.visibility = View.VISIBLE
            buttonsignup.visibility = View.VISIBLE
            regno.visibility = View.VISIBLE
            fullname.visibility = View.VISIBLE

        }
        buttonlogin.setOnClickListener {

            password.startAnimation(rightout)
            email.startAnimation(rightout)
            buttonsignup.startAnimation(rightout)
            regno.startAnimation(rightout)
            fullname.startAnimation(rightout)
            email.visibility = View.GONE
            password.visibility = View.GONE
            buttonsignup.visibility = View.GONE
            regno.visibility = View.GONE
            fullname.visibility = View.GONE
            editText.startAnimation(rightout)
            editText2.startAnimation(rightout)
            button.startAnimation(rightout)
            editText.visibility = View.VISIBLE
            editText2.visibility = View.VISIBLE
            button.visibility = View.VISIBLE


        }


        fab.setOnClickListener { view ->

            mainpresenter.fabclick()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
override fun onStart() {
    super.onStart()
    if (mauth.currentUser != null) {
        // User is logged in
        var ty=Intent(this,Mainpage_Activity::class.java)
        ty.putExtra("id", mauth.currentUser!!.uid)
        ty.putExtra("email", mauth.currentUser!!.email)
        startActivity(ty)
        finish()

    }

}


    override fun showschoolfees() {

    }

    override fun showupload() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showedittext() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showlogin() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showsignup() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showfab() {
        val alert = AlertDialog.Builder(this@MainActivity)
        alert.setTitle("    Admin Login").setIcon(R.drawable.ic_perm_identity_blue_24dp)

        val layout = LinearLayout(this@MainActivity)
        layout.orientation = LinearLayout.VERTICAL

        val Wifi_textbox = EditText(this@MainActivity)
        Wifi_textbox.hint="E-mail"
        layout.addView(Wifi_textbox)

        val Mac_textbox = EditText(this@MainActivity)
        Mac_textbox.hint="Password"
        layout.addView(Mac_textbox)

        alert.setView(layout)

        alert.setPositiveButton("Login", DialogInterface.OnClickListener { dialog, whichButton ->
            Wifi_textbox.text.toString()
            Mac_textbox.text.toString()
            if (Wifi_textbox.text.toString().isEmpty() && Mac_textbox.text.toString().isEmpty())
            {
                Toast.makeText(this@MainActivity, "Some fields are empty", Toast.LENGTH_LONG).show()

            }
            else {

                db.collection("students").whereEqualTo(Wifi_textbox.text.toString(),"email").whereEqualTo(Mac_textbox.text.toString(),"password").get()
                        .addOnSuccessListener {
                            Toast.makeText(this@MainActivity, "Authentication Sucessfully", Toast.LENGTH_LONG).show()
                            val i = Intent(this, Admin_Activity::class.java)
                            startActivity(i)

                        }
                        .addOnFailureListener {

                                    Toast.makeText(this@MainActivity, "Authentication Not Sucessfully", Toast.LENGTH_LONG).show()
                                }

            }

    })

        alert.setNegativeButton("", DialogInterface.OnClickListener { dialog, whichButton ->
            // what ever you want to do with No option.
        })

        alert.show()

        }




}
