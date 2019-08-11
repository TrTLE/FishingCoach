package io.fishingcoach.model.database

import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class FishDb (private val dbHelper: FishDbHelper = FishDbHelper.instance) {

    fun requestFish() = dbHelper.use {
        select(
            FishTable.Name,
            FishTable.LivingPlace
        ).parseList(classParser<FishTable>())
    }

    fun insertFish(fish: Fish) = dbHelper.use {
        insert(FishTable.Name, FishTable.LivingPlace to fish.LivingPlace)
    }
}