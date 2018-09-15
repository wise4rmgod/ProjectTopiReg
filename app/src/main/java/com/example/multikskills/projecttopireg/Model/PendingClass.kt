package com.example.multikskills.projecttopireg.Model


 //data class PendingClass(val email: String,val fullname: String, val password: String  , val regno: String, val status:String, val id: String, val imgurl: String)
  class PendingClass {
     var id:String?= null
     var regno:String?= null
     var topic: String? = null
     var fullname: String? = null
     var statement: String? = null
     var objective: String? = null
     var motivation: String? = null
     var significance: String? = null
     var imgurl: String? = null
     var methodology: String? =  null
     var status: String? = null


    constructor() {}

    constructor(id: String,regno: String,topic: String,fullname: String,statement: String, status: String,objective: String,motivation: String,imgurl: String, methodology: String) {
        this.id=id
        this.regno=regno
        this.topic = topic
        this.fullname = fullname
        this.statement = statement
        this.status = status
        this.objective = objective
        this.motivation= motivation
        this.imgurl=imgurl
        this.methodology=methodology

    }
     constructor(email: String) {
         this.fullname = fullname

     }

}


// data class ProjectTopic(var Topicname:String,var objective:String,var statement:String,var motivation:String,var significance:String,var status:String)