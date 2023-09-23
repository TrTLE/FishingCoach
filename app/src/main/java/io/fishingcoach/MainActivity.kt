package io.fishingcoach

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.Explode
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import io.fishingcoach.model.enumeration.Place
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        setContentView(R.layout.activity_main)
    }

    fun onPlaceClicked(button: View) {
        var myIntent = Intent(this, FishListActivity::class.java)

        when (button) {
            pond -> myIntent.putExtra("PLACE", Place.POND)
            river -> myIntent.putExtra("PLACE", Place.RIVER)
            surfcasting -> myIntent = Intent(this, NoImplementActivity::class.java)
            seafishing -> myIntent = Intent(this, ApiDriveTestActivity::class.java)
        }

        /*TODO FIND HOW TO USE XML TO MANAGE TRANSITION*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            with(window) {
                enterTransition = Explode()
                exitTransition = Explode()
            }
            startActivity(myIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        } else {
            startActivity(myIntent)
        }
    }
}
