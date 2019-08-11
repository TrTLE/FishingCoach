package io.fishingcoach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import io.fishingcoach.model.RecyclerView.Pond.FishInThePlace
import io.fishingcoach.model.RecyclerView.Pond.FishInThePlaceAdapter
import kotlinx.android.synthetic.main.activity_pond.*

class PondActivity : AppCompatActivity() {

    val FishInThePlaceArray = arrayOf(FishInThePlace("TROUT", R.drawable.trout), FishInThePlace("CARPUS", R.drawable.carpus))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pond)
        FishIsThePlaceRecyclerView.layoutManager = LinearLayoutManager(this)
        FishIsThePlaceRecyclerView.adapter = FishInThePlaceAdapter(FishInThePlaceArray)
    }


}
