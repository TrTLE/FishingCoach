package io.fishingcoach.model.api

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class APIFishHelper {

    private val TAG = "APIFishHelper"
    private val url = "http://192.168.1.19:8085/" // http:192.168.1.19:8085 // http://10.0.2.2:8085/

    fun getFish() {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val service = retrofit.create(APIFishService::class.java)
        val fishRequest = service.getFish()

        fishRequest.enqueue(object : Callback<APIFish> {
            override fun onResponse(call: Call<APIFish>, response: Response<APIFish>) {
                val allFish = response.body()
                if (allFish != null) {
                    Log.i(TAG, "HERE is THE FISH FROM TrTLE SERVER:")
                    Log.i(
                        TAG,
                        "one fish : specie [ ${allFish.Species} ] : spec_code [ ${allFish.SpecCode} ] "
                    )
                }
            }

            override fun onFailure(call: Call<APIFish>, t: Throwable) {
                error("KO")
            }
        })
    }

}