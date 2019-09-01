package io.fishingcoach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fish_detail.*

class FishDetailActivity : AppCompatActivity() {

    companion object{

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fish_detail)
        detailFishName.text = intent.getStringExtra("FishingType")
    }
}
