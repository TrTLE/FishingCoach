package io.fishingcoach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import io.fishingcoach.model.RecyclerView.Pond.FishDetail
import io.fishingcoach.model.RecyclerView.Pond.FishInThePlace
import io.fishingcoach.model.RecyclerView.Pond.FishInThePlaceAdapter
import kotlinx.android.synthetic.main.activity_pond.*

class FishDetailActivity : AppCompatActivity() {

    companion object{

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fish_detail)
    }
}
