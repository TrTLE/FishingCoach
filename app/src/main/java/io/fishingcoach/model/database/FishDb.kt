package io.fishingcoach.model.database

import android.database.Cursor
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.parseList
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
            MaterialTable.NAME
        ).parseList(classParser<Material>())
    }

    fun requestFishingType() = dbHelper.use {
        select(
            FishingTypeTable.NAME
        ).parseList(classParser<FishingType>())
    }

    fun requestFishByPlace(place: String) = dbHelper.use {
        val projections = arrayOf("NAME","ID","LIVINGPLACE","FISHINGTYPE","IMG")
        val selection = "LIVINGPLACE =?"
        val selectionArg = arrayOf(place)
        val groupeBy = "NAME"
        val having = null
        val orderBy = "NAME ASC"
        val maxResultSize = "60"
        var cursor = query(FishTable.NAME,projections,selection,selectionArg,groupeBy,having, orderBy, maxResultSize)

        cursor.parseList(classParser<Fish>())

    }

    /*TODO Use query fonction */
    fun requestFishingTypeFromFishAndPlace(fishName :String, place: String) = dbHelper.use {
        val rawQuery = "SELECT T.* FROM FISH as F, FISHINGTYPE as T WHERE F.FISHINGTYPE = T.ID AND F.LIVINGPLACE = '" + place +"' AND F.NAME = '" + fishName + "';"
        val cursor = rawQuery(rawQuery,null)

        cursor.parseList(classParser<FishingType>())
    }

    fun requestFishingTypeByID(id :Int) = dbHelper.use {
        val projections = arrayOf("NAME","ID","DESCRIPTION")
        val selection = "ID =?"
        val selectionArg = arrayOf("$id")
        val groupeBy = "NAME"
        val having = null
        val orderBy = "NAME ASC"
        val maxResultSize = "60"
        var cursor = query(FishingTypeTable.NAME,projections,selection,selectionArg,groupeBy,having, orderBy, maxResultSize)

        cursor.parseList(classParser<FishingType>())
    }
}