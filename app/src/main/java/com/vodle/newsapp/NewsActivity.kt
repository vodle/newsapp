package com.vodle.newsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.arraySetOf
import androidx.core.os.persistableBundleOf
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


   var ADAPTER = ItemAdapter(this, articleList){
       var INTENT = Intent(this, DetailActivity::class.java)
       INTENT.putExtra("OBJECT", it)
       startActivity(INTENT)
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_news)


        val recycleView = findViewById<RecyclerView>(R.id.recycler)
        var intent = intent
        var URLE = intent.getStringExtra("serchreq").toString()
        recycleView.adapter = ADAPTER
        run(URLE)
        Log.d("OKK", articleList.size.toString())
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.setHasFixedSize(true)

//"http://newsapi.org/v2/everything?q=all&apiKey=5abbc7a648c742c8917b6d53c9d8832c"




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
                    var ID = "ID"
                    if (article.getJSONObject(i).has("id")){
                        ID = article.getJSONObject(i).get("id").toString()
                    }
                    var NAME = "NAME"
                    if (article.getJSONObject(i).has("name")){
                        NAME = article.getJSONObject(i).get("name").toString()
                    }
                    var AUTHOR = "author"
                    if (article.getJSONObject(i).has("author")){
                        AUTHOR = article.getJSONObject(i).get("author").toString()
                    }

                    var articlein = ItemList(ID,
                        NAME,
                        AUTHOR,
                        article.getJSONObject(i).get("title").toString(),
                        article.getJSONObject(i).get("description").toString(),
                        article.getJSONObject(i).get("url").toString(),
                        article.getJSONObject(i).get("urlToImage").toString(),
                        article.getJSONObject(i).get("publishedAt").toString(),
                        article.getJSONObject(i).get("content").toString()
                        )
                    //INTENT.putExtra("name", articlein.name)

                    articleList.add(articlein)
                    Log.d("OKKK", articleList.size.toString())

                }
                runOnUiThread(Runnable(){
                    ADAPTER.itemsArticles = articleList
                    ADAPTER.notifyDataSetChanged()
                })
                Log.d("OKKKK", articleList.size.toString())
            }
        })
    }
}

