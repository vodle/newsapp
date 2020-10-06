package com.vodle.newsapp

import android.content.Context
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityManager
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vodle.newsapp.ItemAdapter.ViewHolder

class ItemAdapter(
    val context : Context,
    var itemsArticles : List<ItemList>,
    val listener: (ItemList) -> Unit
) : RecyclerView.Adapter<ViewHolder>(){
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){

        val textNameSrc = view.findViewById<TextView>(R.id.textName)
        val textDateSrc = view.findViewById<TextView>(R.id.textDate)
        val imageOnCardSrc = view.findViewById<ImageView>(R.id.imageViewNews)
        val textTitleSrc = view.findViewById<TextView>(R.id.textTitle)
        val textDescriptionSrc = view.findViewById<TextView>(R.id.textDescription)

        fun bindView(article : ItemList, listener: (ItemList) -> Unit) {
            textNameSrc.text = article.author
            textDateSrc.text = article.publishDate
            textTitleSrc.text = article.title
            textDescriptionSrc.text = article.description

            Picasso.get().load(article.urlImage).into(imageOnCardSrc)

            itemView.setOnClickListener { listener(article) }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_of_list, parent, false))

    override fun getItemCount(): Int {
        return itemsArticles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(itemsArticles[position], listener)
    }
}