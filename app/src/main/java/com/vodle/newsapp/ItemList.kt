package com.vodle.newsapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

data class ItemList(

    var id : String,
    var name : String,
    var author : String,
    var title : String,
    var description : String,
    var urlFull : String,
    var urlImage : String,
    var publishDate : String,
    var content : String
)
