package io.fishingcoach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import io.fishingcoach.model.RecyclerView.Pond.FishDataRecyclerViewProvider
import io.fishingcoach.model.RecyclerView.Pond.FishInThePlace
import io.fishingcoach.model.RecyclerView.Pond.FishInThePlaceAdapter
import io.fishingcoach.model.Values.Place
import io.fishingcoach.model.database.*
import kotlinx.android.synthetic.main.activity_fishlist.*

class FishListActivity : AppCompatActivity() {

    private lateinit var fishInThePlaceArray: Array<FishInThePlace>
    private val placeToFish = intent.getStringExtra(Place.PLACE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        setContentView(R.layout.activity_fishlist)
        FishIsThePlaceRecyclerView.layoutManager = LinearLayoutManager(this)
        FishIsThePlaceRecyclerView.adapter = FishInThePlaceAdapter(fishInThePlaceArray)
    }

    /*TODO use db url pictures*/
    private fun init(){
        /*val placeToFish :String

        if (intent.getStringExtra("PLACE") == null)
            placeToFish = "POND"
        else
            placeToFish = intent.getStringExtra("PLACE")*/

        fishInThePlaceArray = FishDataRecyclerViewProvider(placeToFish).getFishsInThePlace()
    }
}
