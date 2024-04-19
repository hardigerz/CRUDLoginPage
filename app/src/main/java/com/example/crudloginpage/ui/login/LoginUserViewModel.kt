package com.example.crudloginpage.ui.login

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.crudloginpage.model.Photo
import com.example.crudloginpage.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class LoginUserViewModel @Inject constructor(private val photoRepository: PhotoRepository) : ViewModel() {
    private var currentResult: Flow<PagingData<Photo>>? = null


    fun getPhoto(): Flow<PagingData<Photo>> {
        val newResult: Flow<PagingData<Photo>> =
            photoRepository.getPhotos()
        currentResult = newResult
        return newResult
    }

}