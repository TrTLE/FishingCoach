package io.fishingcoach

import android.app.Application
import io.fishingcoach.model.database.FishDb
import io.fishingcoach.model.database.FishDbHelper
import io.fishingcoach.utils.enumeration.DBEnum

class App : Application() {

    companion object {
        lateinit var instance: App
        lateinit var db: FishDb
        lateinit var dbHelper: FishDbHelper
        lateinit var dbType: DBEnum
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        dbHelper = FishDbHelper(instance)
        dbHelper.readableDatabase
        db = FishDb(dbHelper)
        dbType = DBEnum.DB_TYPE_SQLLITE;
    }
}