package io.fishingcoach.model.database

object FishTable {
    val NAME = "FISH"
    val ID = "_id"
    val LIVINGPLACE = "LIVINGPLACE"
    val FISHINGTYPE = "FISHINGTYPE"
}

object MaterialTable {
    val NAME = "MATERIAL"
    val DESCRIPTION = "DESCRIPTION"
    val ID = "_id"
    val FISH = "FISH"
    val FISHINGTYPE = "FISHINGTYPE"
}

object FishingTypeTable {
    val NAME = "FISHINGTYPE"
    val DESCRIPTION = "DESCRIPTION"
    val ID = "_id"
}