package com.example.crudloginpage.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.crudloginpage.utils.Constant.APP_TABLE


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(userEntity: UserEntity)

    @Query("SELECT * FROM $APP_TABLE ORDER BY id DESC")
    suspend fun getAllUser() :MutableList<UserEntity>

    @Query("SELECT * FROM $APP_TABLE WHERE email = :email AND password = :password")
    suspend fun getUser(email: String, password: String): UserEntity
}