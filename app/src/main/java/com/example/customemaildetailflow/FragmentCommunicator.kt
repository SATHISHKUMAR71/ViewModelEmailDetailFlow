package com.example.customemaildetailflow

interface FragmentCommunicator {
    fun displayDetails(email:CustomEmail,title:String,subtitle:String,date:String,content:String,isStarred:Boolean,isViewed:Boolean,transitionName:String)
}