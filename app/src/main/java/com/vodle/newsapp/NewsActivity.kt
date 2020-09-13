package com.vodle.newsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_news.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import kotlin.math.log

class NewsActivity : AppCompatActivity(){



    var okHttpClient: OkHttpClient = OkHttpClient()

    var articleList = mutableListOf<ItemList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_news)

        var intent = intent

//"http://newsapi.org/v2/everything?q=all&apiKey=5abbc7a648c742c8917b6d53c9d8832c"

       btnOKK.setOnClickListener {
           val recycleView = findViewById<RecyclerView>(R.id.recycler)
           recycleView.layoutManager = LinearLayoutManager(this)
           recycleView.setHasFixedSize(true)
           recycleView.adapter = ItemAdapter(this, articleList){}
            var URLE = intent.getStringExtra("serchreq").toString()
           Log.d("OK", URLE.toString())
           if (URLE != null) {
               run(URLE)
           }



       }



    }

    fun run(urle : String){

        val request: Request = Request.Builder().url(urle).build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("MyError", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {

                var articles = JSONObject(response.body()!!.string())
                var article = articles.getJSONArray("articles")
                Log.d("OK", article.toString())
                for (i in 0..10){
                    var articlein = ItemList("not",
                        "not",
                        "not",
                        article.getJSONObject(i).get("title").toString(),
                        article.getJSONObject(i).get("description").toString(),
                        article.getJSONObject(i).get("url").toString(),
                        article.getJSONObject(i).get("urlToImage").toString(),
                        article.getJSONObject(i).get("publishedAt").toString(),
                        article.getJSONObject(i).get("content").toString()
                        )
                    articleList.add(articlein)

                }

            }
        })
    }
}

