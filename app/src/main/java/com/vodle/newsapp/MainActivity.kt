package com.vodle.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val API_KEY = "apiKey=5abbc7a648c742c8917b6d53c9d8832c"
        val endpoint = "http://newsapi.org/v2/everything?"
        btnOK.setOnClickListener {
            var i = Intent(this, NewsActivity::class.java)
            var serch = "q="
            var endserch = ""
            if (textSerch.text.toString().trim() != ""){
                serch += textSerch.text.toString()
            }else{
                serch = "q=all"
            }

                endserch = endpoint + serch + "&" + API_KEY

            i.putExtra("serchreq", endserch)
            startActivity(i)
        }

    }
}