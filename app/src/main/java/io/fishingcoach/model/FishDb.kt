package io.fishingcoach.model

import android.database.sqlite.SQLiteOpenHelper
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class FishDb (private val dbHelper: dataBaseProvider = dataBaseProvider.instance) {

    fun requestFish() = dbHelper.use {
        select(FishTable.NAME, FishTable.TITLE).parseList(classParser<FishTable>())
    }

    fun insertFish(fish: fish) = dbHelper.use {
        insert(FishTable.NAME, FishTable.TITLE to fish.name)
    }
}