package com.hedaia.preinterviewstcflickerapp.model

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    //www.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=e905300630fa2ae54e8171fed3020d82&per_page=10&page=1&format=json&nojsoncallback=1

    @GET("rest/")
    suspend fun getPostsItem(@Query("method") method:String,
                     @Query("api_key") apiKey:String,
                     @Query("per_page") perPage:Int,
                     @Query("page") Page:Int,
                     @Query("format") fromat:String,
                     @Query("nojsoncallback") noJsonCallback:String): Response<Posts>

}
