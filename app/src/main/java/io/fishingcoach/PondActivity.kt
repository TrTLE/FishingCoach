package io.fishingcoach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import io.fishingcoach.model.RecyclerView.Pond.FishInThePlace
import io.fishingcoach.model.RecyclerView.Pond.FishInThePlaceAdapter
import io.fishingcoach.model.database.Fish
import io.fishingcoach.model.database.FishDb
import io.fishingcoach.model.database.FishDbHelper
import kotlinx.android.synthetic.main.activity_pond.*

class PondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        val allFish = App.db.requestFish()
        var i=0
        val fishInThePlaceArray = Array<FishInThePlace>(allFish.size){FishInThePlace("ONE FISH", 666)}

        for (fish in allFish){
            fishInThePlaceArray[i++] = FishInThePlace(fish.NAME, R.drawable.trout)
        }

        setContentView(R.layout.activity_pond)
        FishIsThePlaceRecyclerView.layoutManager = LinearLayoutManager(this)
        FishIsThePlaceRecyclerView.adapter = FishInThePlaceAdapter(fishInThePlaceArray)
    }
}
