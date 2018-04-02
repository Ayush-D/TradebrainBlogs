package com.example.application.braintradeblogs

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_layout.view.*

class MainAdapterKt (private val mContext: Context, private val users: List<MainActivity.HomeFeed>): RecyclerView.Adapter<MainAdapterKt.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(users[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_layout, parent, false)
        return ViewHolder(view,mContext)
    }

    override fun getItemCount(): Int {
    return users.size
    }


    class ViewHolder(itemView: View,private val mContext:Context): RecyclerView.ViewHolder(itemView){
       fun bind(homeFeed: MainActivity.HomeFeed) {

           itemView?.tvTitle?.text = homeFeed.title.rendered
           itemView?.tvDesc?.text = homeFeed.date

           //Fetching Image
           val thumbnailImageView = itemView?.imageFlag
           Picasso.get().load(homeFeed.better_featured_image.source_url).into(thumbnailImageView)

           itemView?.btnMore?.setOnClickListener {
                Toast.makeText(mContext,"Opening....",Toast.LENGTH_SHORT).show()
               val i = Intent(mContext, GalleryActivity::class.java)
               i.putExtra("textOk",homeFeed.link)
               mContext.startActivity(i)
           }

           thumbnailImageView?.setOnClickListener {
               val i= Intent(mContext,ImageActivity::class.java)
               i.putExtra("textOk",homeFeed.title.rendered)
               i.putExtra("imageOk",homeFeed.better_featured_image.source_url)
               i.putExtra("linkOk",homeFeed.link)
               mContext.startActivity(i)
           }
       }
    }
}