package com.example.application.braintradeblogs

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        println("ImageActivity Started Successfully")

        //Back Button in Toolbar
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        getIncomingIntent()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun getIncomingIntent() {

            val imageOk = intent.getStringExtra("imageOk")
            val linkOk = intent.getStringExtra("linkOk")
            val textOk = intent.getStringExtra("textOk")

            tvTitles?.text = textOk

            btnMores?.setOnClickListener {
                val i = Intent(this,GalleryActivity::class.java)
                i.putExtra("textOk",linkOk)
                this.startActivity(i)
            }

            setImage(imageOk)


    }
        private fun setImage(imageOk:String){
            Picasso.get().load(imageOk).into(imgHead)
        }

}
