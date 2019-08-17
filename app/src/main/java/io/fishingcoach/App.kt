package io.fishingcoach

import android.app.Application
import io.fishingcoach.model.database.FishDb
import io.fishingcoach.model.database.FishDbHelper

class App : Application(){

    companion object{
        lateinit var instance : App
        lateinit var db : FishDb
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        db = FishDb(FishDbHelper(instance))
    }
}