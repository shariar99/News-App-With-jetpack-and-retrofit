package com.shariar99.network

import com.shariar99.models.TopNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    fun getTopArticles(@Query("country") country:String,
                       @Query("apiKey")apiKey:String):
            Call<TopNewsResponse>

    @GET("top-headlines")
    fun getTopArticlesByCategory(@Query("category") country:String,
                                   @Query("apiKey")apiKey:String):
            Call<TopNewsResponse>




}