package com.example.application.braintradeblogs

import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("?filter[category_name]=android&per_page=40&fields=id,title,date,link,better_featured_image")
    fun getHeroes(): Call<List<MainActivity.HomeFeed>>
}