package com.messanger.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.messanger.data.dao.UserDao
import com.messanger.data.model.User

/**
 * Created by Rahul Abrol on 18/9/20.
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
//@TypeConverters(value = [TypeResponseConverter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "messenger_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}