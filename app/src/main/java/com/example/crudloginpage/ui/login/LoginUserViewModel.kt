package com.example.crudloginpage.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.crudloginpage.model.Photo
import com.example.crudloginpage.repository.PhotoRepository
import com.example.crudloginpage.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginUserViewModel @Inject constructor(private val photoRepository: PhotoRepository) : ViewModel() {

    private val _photosLiveData = MutableLiveData<Resource<PagingData<Photo>>>()
    val photosLiveData: LiveData<Resource<PagingData<Photo>>>
        get() = _photosLiveData

    fun fetchPhotos() {
        viewModelScope.launch {
            photoRepository.getPhotos().collect { resource ->
                _photosLiveData.value = resource
            }
        }
    }
}