package com.messanger.data.repositories

import com.messanger.data.dao.UserDao
import com.messanger.data.model.User

/**
 * Created by Rahul Abrol on 18/9/20.
 *
 * Declares the DAO as a private property in the constructor. Pass in the DAO
 * instead of the whole database, because you only need access to the DAO
 */
class UserRepository(private val userDao: UserDao) {

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun deleteAll() {
        userDao.deleteAll()
    }

    suspend fun update(user: User) {

    }
}