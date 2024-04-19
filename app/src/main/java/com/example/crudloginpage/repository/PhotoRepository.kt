package com.example.crudloginpage.repository

import androidx.paging.PagingData
import com.example.crudloginpage.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getPhotos(): Flow<PagingData<Photo>>
}