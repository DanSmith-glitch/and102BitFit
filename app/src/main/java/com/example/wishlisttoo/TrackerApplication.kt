package com.example.wishlisttoo

import android.app.Application

class TrackerApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}