package com.example.multikskills.projecttopireg.Presenter

import android.support.v7.view.menu.MenuPresenter
import com.example.multikskills.projecttopireg.MainMVP

class Mainpresenter (private val view: MainMVP.view) : MainMVP.presenter {
    override fun fabclick() {
       view.showfab()
    }

    override fun loginclick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signupclick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun buttonclicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

