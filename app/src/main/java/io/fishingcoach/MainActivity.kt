package io.fishingcoach

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import android.util.Log
import android.view.View
import io.fishingcoach.model.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onPondClicked(buton : View){
        buton.setOnClickListener { startActivity<io.fishingcoach.PondActivity>()}
    }

    fun onRiverCliked(buton: View){
        buton.setOnClickListener { startActivity<io.fishingcoach.RiverActivity>() }
    }
}
