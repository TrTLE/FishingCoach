package io.fishingcoach.model.database

import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class FishDb (private val dbHelper: FishDbHelper = FishDbHelper.instance) {

    fun requestFish() = dbHelper.use {
        select(
            FishTable.NAME
        ).parseList(classParser<Fish>())
    }

    fun insertFish(fish: Fish) = dbHelper.use {
        insert(FishTable.NAME, FishTable.LIVINGPLACE to fish.LIVINGPLACE)
    }

    fun requestMaterial() = dbHelper.use {
        select(
            FishTable.NAME
        ).parseList(classParser<Fish>())
    }
}