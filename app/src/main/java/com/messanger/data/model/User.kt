package com.messanger.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Rahul Abrol on 10/24/17.
 */
@Entity(tableName = "users")
data class User(
        @PrimaryKey var email: String? = null,
        /*@ColumnInfo(name = "word")*/ var name: String? = null,
        var id: String? = null
)