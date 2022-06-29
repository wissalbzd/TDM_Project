package com.example.textjuridique

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiSearch {
    @GET("_search")
    fun getAllData(@Query("q") q: String): Call<HitsObject>
    @GET("_search")
    fun getData():Call<HitsObject>
    @GET("_search/")
    fun search(
            @Query("q") query: String? //second query (prepends '&')
    ): Call<HitsObject?>?
}