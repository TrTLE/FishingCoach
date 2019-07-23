package io.fishingcoach.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import io.fishingcoach.App
import org.jetbrains.anko.db.*



class dataBaseProvider(ctx : Context = App.instance) : ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {

    //SQLiteOpenHelper(ctx, DB_NAME, DB_VERSION, null)

    companion object{
        val DB_NAME = "fish.db"
        val DB_VERSION = 1
        val instance by lazy { dataBaseProvider() }
    }


    override fun onCreate(db: SQLiteDatabase) {
        //création d'une nouvelle table à partir de quoi ?
        db.createTable(FishTable.NAME, true, FishTable.ID to INTEGER + PRIMARY_KEY)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.dropTable(FishTable.NAME)
        onCreate(db)
    }

}