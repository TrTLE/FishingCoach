package io.fishingcoach.model.recyclerview.fishlist
import io.fishingcoach.App
import io.fishingcoach.model.database.Fish
import io.fishingcoach.model.database.FishingType

class FishDataRecyclerViewProvider (private val place: String){
    private val fishList : List<Fish>
    private var placeID = 0

    init {
        if(place.isNotEmpty())
        {
            fishList = App.db.requestFishByPlace(place)
            placeID = App.db.requestGetPlaceID(place)
        }
        else
            fishList = emptyList()
    }

    fun getFishsInThePlace(): Array<FishInThePlace>{
        var fishInThePlaceArray = emptyArray<FishInThePlace>()

        if(fishList.isNotEmpty()) {
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