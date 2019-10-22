package io.fishingcoach

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import io.fishingcoach.model.recyclerview.fishlist.*
import io.fishingcoach.model.Values.Place
import kotlinx.android.synthetic.main.activity_fishlist.*

class FishListActivity : AppCompatActivity() {

    private lateinit var fishInThePlaceArray: Array<FishInThePlace>
    private val placeToFish by lazy { intent.getStringExtra(Place.PLACE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        setContentView(R.layout.activity_fishlist)
        FishInThePlaceRecyclerView.layoutManager = LinearLayoutManager(this)
        FishInThePlaceRecyclerView.adapter = FishInThePlaceAdapter(fishInThePlaceArray, this)
    }

    private fun init(){
        fishInThePlaceArray = FishDataRecyclerViewProvider(placeToFish).getFishsInThePlace()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            with(window){
                enterTransition = Explode()
                exitTransition = Explode()
            }
    }
}
