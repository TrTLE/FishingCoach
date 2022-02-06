package io.fishingcoach.model.recyclerView.fishlist

import io.fishingcoach.App
import io.fishingcoach.model.database.Fish
import io.fishingcoach.model.database.FishingType

class FishDataRecyclerViewProvider(private val place: String) {

    private val TAG = "FishDataRecyclerViewProvider"
    private val fishList: List<Fish>
    private var placeID = 0

    init {
        if (place.isNotEmpty()) {
            //TODO remplacer l'appel à la BDD local pour un appel REST au serveur
            fishList = App.db.requestFishByPlace(place)
            placeID = App.db.requestGetPlaceID(place)
        } else
            fishList = emptyList()
    }

    fun getFishInThePlace(): Array<FishInThePlace> {
        var fishInThePlaceArray = emptyArray<FishInThePlace>()

        if (fishList.isNotEmpty()) {
            var i = 0
            val url =
                "https://2.bp.blogspot.com/-Mtiz4rXG9AE/UdMDy9i89RI/AAAAAAAAHJY/b5tjZN3bK4U/s1600/How_to_draw_cartoon_fish+%25284%2529.jpg" // CARTOON FISH

            fishInThePlaceArray = Array<FishInThePlace>(fishList.size) {
                FishInThePlace("ONE FISH", 666, url, 666, Array<FishingType>(5) {
                    FishingType("ONE FISHINGTYPE", 666, "GOOD TO FISH")
                })
            }

            for (fish in fishList) {
                fishInThePlaceArray[i++] = FishInThePlace(
                    fish.NAME,
                    fish.ID,
                    fish.IMG,
                    placeID,
                    App.db.requestFishingTypeFromFishAndPlace(
                        fish.NAME,
                        place
                    ).toTypedArray()
                )
            }
        }

        return fishInThePlaceArray
    }
}