package io.fishingcoach

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import androidx.recyclerview.widget.GridLayoutManager
import io.fishingcoach.model.recyclerview.fishingdetail.MaterialDataRecyclerViewProvider
import io.fishingcoach.model.recyclerview.fishingdetail.MaterialToUse
import io.fishingcoach.model.recyclerview.fishingdetail.MaterialToUseAdapter
import kotlinx.android.synthetic.main.activity_fish_detail.*

class FishDetailActivity : AppCompatActivity() {

    private var fishID : Int = 0
    private lateinit var materialToUseArray: Array<MaterialToUse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        setContentView(R.layout.activity_fish_detail)

        detailFishName.text = "Catch a " + intent.getStringExtra("FishName") + " using " + intent.getStringExtra("FishingType")

        MaterialToUseRecyclerView.layoutManager = GridLayoutManager(this, materialToUseArray.size)
        MaterialToUseRecyclerView.adapter = MaterialToUseAdapter(materialToUseArray)
    }

    private fun init(){
        fishID = intent.getIntExtra(getString(R.string.MATERIAL_FISH_ID), 0)


        materialToUseArray = MaterialDataRecyclerViewProvider(fishID).getMaterialToUse()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            with(window) {
                enterTransition = Explode()
                exitTransition = Explode()
            }
        }
    }
}
