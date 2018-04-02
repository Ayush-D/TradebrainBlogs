package com.example.application.braintradeblogs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchJson()
    }

    private fun fetchJson(){
        val retrofit = Retrofit.Builder()
                .baseUrl("http://www.tradebrains.in/wp-json/wp/v2/posts/")
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build()

        //creating the Api interface
        val api = retrofit.create(Api::class.java)  //:: class as data type
        val call = api.getHeroes()

        call.enqueue(object : Callback<List<HomeFeed>>{
            override fun onFailure(call: Call<List<HomeFeed>>?, t: Throwable?) {
                Toast.makeText(applicationContext, t?.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<HomeFeed>>?, response: Response<List<HomeFeed>>?) {
                val body = response?.body()
                println(body)

                val contacts: List<HomeFeed> = response?.body()!!

                recyclerView.layoutManager = LinearLayoutManager(baseContext)
                recyclerView.adapter = MainAdapterKt(this@MainActivity,contacts)

            }

        })

    }

    class HomeFeed(val id :Int, val date :String, val link :String,@SerializedName("better_featured_image")val better_featured_image:BetterImage, @SerializedName("title")val title: Title)
    class BetterImage(val source_url:String)
    class Title(val rendered:String)
}
