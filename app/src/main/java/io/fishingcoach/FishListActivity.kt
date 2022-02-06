package io.fishingcoach

import android.os.Build
import android.os.Bundle
import android.transition.Explode
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.fishingcoach.model.enumeration.Place
import io.fishingcoach.model.recyclerView.fishlist.FishDataRecyclerViewProvider
import io.fishingcoach.model.recyclerView.fishlist.FishInThePlace
import io.fishingcoach.model.recyclerView.fishlist.FishInThePlaceAdapter
import kotlinx.android.synthetic.main.activity_fishlist.*

class FishListActivity : AppCompatActivity() {

    private lateinit var fishInThePlaceArray: List<FishInThePlace>
    private val placeToFish by lazy { intent.getStringExtra(Place.PLACE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        setContentView(R.layout.activity_fishlist)
        FishInThePlaceRecyclerView.layoutManager = LinearLayoutManager(this)
        FishInThePlaceRecyclerView.adapter = FishInThePlaceAdapter(fishInThePlaceArray, this)
    }

    private fun init() {
        fishInThePlaceArray = FishDataRecyclerViewProvider(placeToFish).getFishInThePlace()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            with(window) {
                enterTransition = Explode()
                exitTransition = Explode()
            }
        }
    }
}
