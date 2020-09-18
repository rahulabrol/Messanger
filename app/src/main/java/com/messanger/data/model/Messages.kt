package com.messanger.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by clicklabs on 11/24/17.
 * Message model for user.
 */
@Parcelize
data class Messages(
        var fromId: String? = null,
        var message: String? = null,
        var imageUrl: String? = null,
        var timeStamp: Long = 0L,
        var toId: String? = null
) : Parcelable