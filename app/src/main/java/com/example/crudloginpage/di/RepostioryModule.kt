package com.example.crudloginpage.di

import com.example.crudloginpage.repository.PhotoRepository
import com.example.crudloginpage.repository.PhotoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providePhotoRepository(photoRepository: PhotoRepositoryImpl): PhotoRepository
}