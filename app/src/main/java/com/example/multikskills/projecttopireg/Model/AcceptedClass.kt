package com.example.multikskills.projecttopireg.Model

class AcceptedClass {
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

    constructor(topic: String,fullname: String,statement: String, status: String,objective: String,motivation: String,imgurl: String, methodology: String) {
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