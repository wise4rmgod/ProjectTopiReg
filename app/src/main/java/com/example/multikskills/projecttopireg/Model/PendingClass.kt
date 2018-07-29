package com.example.multikskills.projecttopireg.Model


 //data class PendingClass(val email: String,val fullname: String, val password: String  , val regno: String, val status:String, val id: String, val imgurl: String)
  class PendingClass {
     var email: String? = null
     var fullname: String? = null
     var regno: String? = null
     var status: String? = null
     var id: String? = null
     var Password: String? = null
     var imgurl: String? = null

    constructor() {}

    constructor(email: String,fullname: String,regno: String, status: String,id: String,password: String,imgurl: String) {
        this.email = email
        this.fullname = fullname
        this.regno = regno
        this.status = status
        this.id = id
        this.Password= password
        this.imgurl=imgurl

    }
     constructor(email: String) {
         this.email = email

     }

}


// data class ProjectTopic(var Topicname:String,var objective:String,var statement:String,var motivation:String,var significance:String,var status:String)