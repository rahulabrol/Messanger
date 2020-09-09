package com.messanger

import android.app.Application
import com.google.firebase.FirebaseApp

/**
 * Created by Rahul Abrol on 8/9/20.
 */
class MessangerApp : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }
}