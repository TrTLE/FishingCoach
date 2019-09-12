package io.fishingcoach

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.transition.Transition
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.view.Window
import androidx.core.content.ContextCompat
import io.fishingcoach.model.Values.Place
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        setContentView(R.layout.activity_main)
    }

    fun onPlaceClicked(button : View){
        var myIntent = Intent(this,FishListActivity::class.java)

        when (button){
            pond -> myIntent.putExtra("PLACE", Place.POND)
            river -> myIntent.putExtra("PLACE", Place.RIVER)
            surfcasting -> myIntent.putExtra("PLACE", Place.SURFCASTING)
            seafishing -> myIntent = Intent(this,ApiDriveTestActivity::class.java)
        }

        /*TODO FIND HOW TO USE XML TO MANAGE TRANSITION*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            with(window){
                enterTransition = Explode()
                exitTransition = Explode()
            }
            startActivity(myIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        } else {
            startActivity(myIntent)
        }

    }
}
