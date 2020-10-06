package com.vodle.newsapp

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_detail)

        val item = intent.getParcelableExtra<ItemList>("OBJECT")

        val textName = findViewById<TextView>(R.id.textNameDetail)
        val textTitle = findViewById<TextView>(R.id.textTitleDetail)
        val textDescription = findViewById<TextView>(R.id.textDescriptionDetail)
        val textDate = findViewById<TextView>(R.id.textDateDetail)
        val image = findViewById<ImageView>(R.id.imageViewDetail)

        if (item != null) {
            textName.text = item.name
            textTitle.text = item.title
            textDescription.text = item.description
            textDate.text = item.publishDate.toString()
            Picasso.get().load(item.urlImage).into(image)

        }



    }

}