package io.fishingcoach.model.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import io.fishingcoach.App
import org.jetbrains.anko.db.*



class FishDbHelper(ctx : Context = App.instance) : ManagedSQLiteOpenHelper(ctx,
    DB_NAME, null,
    DB_VERSION
) {

    companion object{
        val DB_NAME = "Fish.db"
        val DB_VERSION = 1
        val instance by lazy { FishDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        //création d'une nouvelle table à partir de quoi ?
        db.createTable(
            FishTable.Name, true, FishTable.ID to INTEGER + PRIMARY_KEY,
            FishTable.LivingPlace to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.dropTable(FishTable.Name, true)
        onCreate(db)
    }

}