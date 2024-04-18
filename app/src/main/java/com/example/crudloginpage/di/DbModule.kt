package com.example.crudloginpage.di

import android.content.Context
import androidx.room.Room
import com.example.crudloginpage.database.UserDatabase
import com.example.crudloginpage.database.UserEntity
import com.example.crudloginpage.utils.Constant.APP_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context) = Room.databaseBuilder(
        applicationContext, UserDatabase::class.java, APP_DB
    )
        .build()

    @Provides
    @Singleton
    fun provideDao(db: UserDatabase) = db.userDao()

    @Provides
    fun provideEntity() = UserEntity()
}