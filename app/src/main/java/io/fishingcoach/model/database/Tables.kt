package io.fishingcoach.model.database
//TODO Add new columns name
//TODO Add new table
object FishTable {
    val NAME = "FISH"
    val ID = "_id"
    val IMG = "IMG"
}

object MaterialTable {
    val NAME = "MATERIAL"
    val DESCRIPTION = "DESCRIPTION"
    val ID = "_id"
    val IMG = "IMG"
}

object FishingTypeTable {
    val NAME = "FISHINGTYPE"
    val DESCRIPTION = "DESCRIPTION"
    val ID = "_id"
}

object Fishing_DetailsTable {
    val NAME = "FISHING_DETAILS"
    val FISH = "FISH"
    val FISHINGTYPE = "FISHINGTYPE"
    val MATERIAL = "MATERIAL"
}

object LivingPlace {
    val NAME = "LIVINGPLACE"
    val ID = "_id"
}