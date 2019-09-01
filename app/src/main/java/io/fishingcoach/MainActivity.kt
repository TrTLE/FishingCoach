package io.fishingcoach

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import androidx.core.content.ContextCompat
import io.fishingcoach.model.Values.Place
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onPlaceClicked(buton : View){
        var myIntent = Intent(this,FishListActivity::class.java)

        when (buton){
            pond -> myIntent.putExtra("PLACE", Place.POND)
            river -> myIntent.putExtra("PLACE", Place.RIVER)
            surfcasting -> myIntent.putExtra("PLACE", Place.SURFCASTING)
            seafishing -> myIntent = Intent(this,ApiDriveTestActivity::class.java)
        }

        startActivity(myIntent)
    }
}
