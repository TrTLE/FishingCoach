package io.fishingcoach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import io.fishingcoach.model.RecyclerView.Pond.FishInThePlace
import io.fishingcoach.model.RecyclerView.Pond.FishInThePlaceAdapter
import io.fishingcoach.model.database.*
import kotlinx.android.synthetic.main.activity_fishlist.*

class FishListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    /*TODO use db url pictures*/
    private fun init(){
        val placeToFish :String

        if (intent.getStringExtra("PLACE") == null)
            placeToFish = "POND"
        else
            placeToFish = intent.getStringExtra("PLACE")

        val allPondFish = App.db.requestFishByPlace(placeToFish)

        //val url = "https://2.bp.blogspot.com/-Mtiz4rXG9AE/UdMDy9i89RI/AAAAAAAAHJY/b5tjZN3bK4U/s1600/How_to_draw_cartoon_fish+%25284%2529.jpg" // POISSON CARTOON
        val url = "http://www.aucomptoirdujardinier.com/wp-content/uploads/2017/10/gardon.jpg" // GARDON
        var i=0

        val fishInThePlaceArray = Array<FishInThePlace>(allPondFish.size){
            FishInThePlace(
                "ONE FISH", url, Array<FishingType>(5){
                FishingType(
                    "ONE FISHINGTYPE",666,"GOOD TO FISH")})}

        for (fish in allPondFish){
            fishInThePlaceArray[i++] = FishInThePlace(fish.NAME, fish.IMG, App.db.requestFishingTypeFromFishAndPlace(fish.NAME, fish.LIVINGPLACE).toTypedArray())
        }

        setContentView(R.layout.activity_fishlist)
        FishIsThePlaceRecyclerView.layoutManager = LinearLayoutManager(this)
        FishIsThePlaceRecyclerView.adapter = FishInThePlaceAdapter(fishInThePlaceArray)
    }
}
