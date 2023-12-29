package com.example.foodapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(var uid:String,var email:String,var username:String,var image:String):Parcelable {
    constructor():this("","","","")
}