package io.fishingcoach

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.fishingcoach.model.recyclerview.fishingdetail.MaterialDataRecyclerViewProvider
import io.fishingcoach.model.recyclerview.fishingdetail.MaterialToUse
import io.fishingcoach.model.recyclerview.fishingdetail.MaterialToUseAdapter
import kotlinx.android.synthetic.main.activity_fish_detail.*

class FishDetailActivity : AppCompatActivity() {

    private var fishID = 0
    private var fishingTypeID = 0
    private var placeID = 0
    private lateinit var materialToUseArray: Array<MaterialToUse>
    private lateinit var fishName : String
    private lateinit var fishingType : String
    private lateinit var fishPicture : String

    private var isFitToSreen = false
    private lateinit var fishDetailActivitylayoutParam: ViewGroup.LayoutParams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        setContentView(R.layout.activity_fish_detail)

        Glide
            .with(this)
            .load(fishPicture)
            .fitCenter()
            .into(fishDetailPicture)

        fishDetailActivitylayoutParam = fishDetailPicture.layoutParams

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fishDetailPicture.transitionName = R.string.FISH_PICTURE.toString()
        }

        val detailFishNameText = "Catch a $fishName by using $fishingType"
        detailFishName.text = detailFishNameText

        MaterialToUseRecyclerView.layoutManager = GridLayoutManager(this, 3)
        MaterialToUseRecyclerView.adapter = MaterialToUseAdapter(materialToUseArray)
    }

    private fun init(){
        fishID = intent.getIntExtra(getString(R.string.MATERIAL_FISH_ID), 0)
        fishingTypeID = intent.getIntExtra("FishingTypeID", 0)
        placeID = intent.getIntExtra("PlaceID", 0)
        fishName = intent.getStringExtra(getString(R.string.MATERIAL_FISHNAME))
        fishingType = intent.getStringExtra(getString(R.string.MATERIAL_FISHINGTYPE))
        fishPicture = intent.getStringExtra("FishPicture")
        materialToUseArray = MaterialDataRecyclerViewProvider(fishID,fishingTypeID,placeID).getMaterialToUse()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            with(window) {
                enterTransition = Explode()
                exitTransition = Explode()
            }
        }
    }

    fun onFishClicked(view:View) {
        if (isFitToSreen){
            isFitToSreen = !isFitToSreen
            fishDetailPicture.layoutParams = fishDetailActivitylayoutParam
            fishDetailPicture.adjustViewBounds = true
        }else{
            isFitToSreen = !isFitToSreen
            fishDetailPicture.layoutParams.width = ConstraintLayout.LayoutParams.WRAP_CONTENT
            fishDetailPicture.layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT
            fishDetailPicture.layoutParams = ConstraintLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            fishDetailPicture.scaleType = ImageView.ScaleType.FIT_CENTER
        }
    }
}