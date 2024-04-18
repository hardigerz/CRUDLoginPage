package com.example.crudloginpage.repository

import com.example.crudloginpage.database.UserDao
import com.example.crudloginpage.database.UserEntity
import javax.inject.Inject

class DbRepository
@Inject constructor(
    private val dao: UserDao
) {
    suspend fun registerUser(userEntity: UserEntity) = dao.insertUser(userEntity)
    suspend fun updateUser(userEntity: UserEntity) = dao.updateUser(userEntity)

    suspend fun deleteUser(userEntity: UserEntity) = dao.deleteUser(userEntity)

    suspend fun getUser(email: String, pass: String) = dao.getUser(email = email, password = pass)

    suspend fun getallUser() = dao.getAllUser()

}