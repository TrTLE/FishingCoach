package io.fishingcoach.model.api

import io.fishingcoach.model.database.Fish
import retrofit2.Call
import retrofit2.http.GET

interface APIFishService {
    @GET("/FishRESTful/rest/client/getfish")
    fun getFish(): Call<APIFish>

    @GET("/FishRESTful/rest/client/getAllFish")
    fun getAllFish(): Call<APIFish>

    @GET("/FishC")
    fun getFishPython(): Call<List<Fish>>

    @GET("/courses")
    fun listCourses(): Call<List<Course>>
}