package com.example.multikskills.projecttopireg

interface MainMVP {

    interface view {
        fun showschoolfees()
        fun showupload()
        fun showedittext()
        fun showlogin()
        fun showsignup()
        fun showfab()
    }


    interface presenter{
        fun fabclick()
        fun loginclick()
        fun signupclick()
        fun buttonclicked()
    }
}