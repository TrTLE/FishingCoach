package io.fishingcoach.model.database

import android.database.Cursor
import org.jetbrains.anko.db.*

class FishDb (private val dbHelper: FishDbHelper = FishDbHelper.instance) {

    fun requestFishNameById(fishID: Int) = dbHelper.use {
        val projections = arrayOf("NAME")
        val selection = "ID =?"
        val selectionArg = arrayOf("$fishID")
        val cursor = query(FishTable.NAME,projections,selection,selectionArg,null,null, null, null)
        cursor.parseSingle(classParser<Fish>())
    }

    fun requestFish() = dbHelper.use {
        select(
            FishTable.NAME
        ).parseList(classParser<Fish>())
    }

    fun insertFish(fish: Fish) = dbHelper.use {
        insert(FishTable.NAME, FishTable.IMG to fish.IMG)
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

    fun requestGetPlaceID(place: String) = dbHelper.use {
        val rawQuery = "SELECT ID FROM LIVINGPLACE WHERE name = '"+ place +"';"
        val cursor = rawQuery(rawQuery,null)
        cursor.parseSingle(IntParser)
    }

    fun requestFishByPlace(place: String) = dbHelper.use {
        val rawQuery = "SELECT F.* FROM FISH as F, FISHING_DETAILS as D, LIVINGPLACE as L WHERE F.ID = D.FISH AND L.name = '"+ place +"' AND D.PLACE = L.ID GROUP BY F.name;"
        val cursor = rawQuery(rawQuery,null)
        cursor.parseList(classParser<Fish>())
    }

    /*TODO Use query fonction */
    fun requestFishingTypeFromFishAndPlace(fishName :String, place: String) = dbHelper.use {
        val rawQuery = "SELECT T.* FROM FISH as F, FISHING_DETAILS as D, LIVINGPLACE as L, FISHINGTYPE as T WHERE F.ID = D.FISH AND D.PLACE = L.ID AND D.FISHINGTYPE = T.ID AND F.NAME ='"+ fishName +"' AND L.name ='"+ place +"' GROUP BY D.FISHINGTYPE;"
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
        val cursor = query(FishingTypeTable.NAME,projections,selection,selectionArg,groupeBy,having, orderBy, maxResultSize)
        cursor.parseList(classParser<FishingType>())
    }

    fun requestGetMaterialByFishId(fishID :Int) = dbHelper.use {
        val rawQuery = "SELECT M.* FROM MATERIAL as M, FISHING_DETAILS as F WHERE M.ID = F.MATERIAL AND F.FISH = '"+ fishID +"';"
        val cursor = rawQuery(rawQuery,null)
        cursor.parseList(classParser<Material>())
    }

    fun requestGetMaterialByFishIdAndFishingTypeID(fishID :Int, fishingTypeID : Int, placeID : Int) = dbHelper.use {
        val rawQuery = "SELECT M.* FROM FISH as F, FISHING_DETAILS as D, LIVINGPLACE as L, FISHINGTYPE as T, MATERIAL as M WHERE F.ID = D.FISH AND D.PLACE = L.ID AND D.FISHINGTYPE = T.ID AND D.MATERIAL = M.ID AND F.ID ='"+ fishID +"' AND T.ID ='"+ fishingTypeID +"' AND L.ID ='"+ placeID +"';"
        val cursor = rawQuery(rawQuery,null)
        cursor.parseList(classParser<Material>())
    }
}