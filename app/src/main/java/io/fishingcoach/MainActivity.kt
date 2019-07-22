package io.fishingcoach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get device dimensions
        val displayMetrics = DisplayMetrics()

        windowManager.defaultDisplay.getMetrics(displayMetrics)



        var width = displayMetrics.widthPixels
        var height = displayMetrics.heightPixels

        var heightPicture = ( height / 4 ) - 50

        //pond.height = heightPicture


    }
}
