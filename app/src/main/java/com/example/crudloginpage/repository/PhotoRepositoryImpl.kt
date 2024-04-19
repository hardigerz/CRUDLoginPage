package com.example.crudloginpage.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.crudloginpage.model.Photo
import com.example.crudloginpage.pagingdatasource.PhotoPagingDataSource
import com.example.crudloginpage.service.PhotoService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRepositoryImpl @Inject constructor(
    private val photoService: PhotoService
) : PhotoRepository{
    override fun getPhotos(): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { PhotoPagingDataSource(photoService) }
        ).flow
    }
    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
}

