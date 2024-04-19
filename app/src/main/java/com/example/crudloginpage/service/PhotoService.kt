package com.example.crudloginpage.service

import com.example.crudloginpage.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {
    @GET("/photos")
    suspend fun getPhotos(
        @Query("_page") page: Int,
        @Query("limit") limit: Int
    ): List<Photo>
}