package com.messanger.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.messanger.data.model.User

/**
 * Created by Rahul Abrol on 18/9/20.
 */
@Dao
interface UserDao {
//    @Query("SELECT * from users")
//    fun getAlphabetizedWords(): List<Word>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("DELETE FROM users")
    suspend fun deleteAll()
}