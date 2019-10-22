package io.fishingcoach

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.fishingcoach.model.recyclerview.fishingdetail.MaterialDataRecyclerViewProvider
import io.fishingcoach.model.recyclerview.fishingdetail.MaterialToUse
import io.fishingcoach.model.recyclerview.fishingdetail.MaterialToUseAdapter
import kotlinx.android.synthetic.main.activity_fish_detail.*

class FishDetailActivity : AppCompatActivity() {

    private var fishID = 0
    private lateinit var materialToUseArray: Array<MaterialToUse>
    private lateinit var fishName : String
    private lateinit var fishingType : String
    private lateinit var fishPicture : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        setContentView(R.layout.activity_fish_detail)

        Glide
            .with(this)
            .load(fishPicture)
            .centerCrop()
            .into(fishDetailPicture)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fishDetailPicture.transitionName = R.string.FISH_PICTURE.toString()
        }

        detailFishName.text = "Catch a " + fishName + " using " + fishingType

        MaterialToUseRecyclerView.layoutManager = GridLayoutManager(this, 2)
        MaterialToUseRecyclerView.adapter = MaterialToUseAdapter(materialToUseArray)
    }

    private fun init(){
        fishID = intent.getIntExtra(getString(R.string.MATERIAL_FISH_ID), 0)
        fishName = intent.getStringExtra(getString(R.string.MATERIAL_FISHNAME))
        fishingType = intent.getStringExtra(getString(R.string.MATERIAL_FISHINGTYPE))
        fishPicture = intent.getStringExtra("FishPicture")

        materialToUseArray = MaterialDataRecyclerViewProvider(fishID).getMaterialToUse()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            with(window) {
                enterTransition = Explode()
                exitTransition = Explode()
            }
        }
    }
}
