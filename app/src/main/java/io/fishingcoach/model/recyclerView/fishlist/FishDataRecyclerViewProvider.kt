package io.fishingcoach.model.recyclerView.fishlist

import io.fishingcoach.App
import io.fishingcoach.model.database.Fish

class FishDataRecyclerViewProvider(private val place: String) {

    private val TAG = "FishDataRecyclerViewProvider"
    private val fishList: List<Fish>
    private var placeID = 0

    init {
        when(place.isNotEmpty()) {
            true -> {
                //TODO remplacer l'appel à la BDD local pour un appel REST au serveur
                fishList = App.db.requestFishByPlace(place)
                placeID = App.db.requestGetPlaceID(place)
            }
            else -> fishList = emptyList()
        }
    }

    fun getFishInThePlace(): List<FishInThePlace> {
        return when(fishList.isNotEmpty()) {
            true -> generateFishInThePlaceArray()
            else -> emptyList()
        }
    }

    private fun generateFishInThePlaceArray(): List<FishInThePlace> {
        val fishInThePlaceArray = mutableListOf<FishInThePlace>()

        fishList.forEach {
            fishInThePlaceArray.add(
                FishInThePlace(
                    it.NAME,
                    it.ID,
                    it.IMG,
                    placeID,
                    App.db.requestFishingTypeFromFishAndPlace(it.NAME, place).toTypedArray()
                )
            )
        }

        return fishInThePlaceArray
    }
}