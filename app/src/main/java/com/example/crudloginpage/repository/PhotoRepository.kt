package com.example.crudloginpage.repository

import androidx.paging.PagingData
import com.example.crudloginpage.model.Photo
import com.example.crudloginpage.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getPhotos(): Flow<Resource<PagingData<Photo>>>
}