package io.fishingcoach.model.API

import io.fishingcoach.model.API.FishsList
import retrofit2.Call
import retrofit2.http.GET

interface FishService {
    @GET("/species/")
    fun listFish(): Call<FishsList>
}