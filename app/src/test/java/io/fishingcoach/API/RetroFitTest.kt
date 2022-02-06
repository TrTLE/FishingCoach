package io.fishingcoach.API

import io.fishingcoach.App
import io.fishingcoach.model.api.APIFishService
import io.fishingcoach.utils.enumeration.DBEnum
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetroFitTest {

    private val TAG = "RetroFitTest"
    private val app = App


    @Test
    fun retrofitCall() {
        app.dbType = DBEnum.DB_TYPE_SQLLITE
        app.dbType = DBEnum.DB_TYPE_POSTRESQL

        val urlChillCoding = "http://mobile-courses-server.herokuapp.com/"

        val dbConfig = DBEnum.values().find { app.dbType == it }
        if (dbConfig == null) {
            Assert.fail("DB TYPE [%s] UNKNOW".format(app.dbType.name))
        }

        var urlFishingCoach = dbConfig!!.url

        val retrofit = Retrofit.Builder()
            .baseUrl(urlFishingCoach)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(APIFishService::class.java)

        val fishRequest = when (dbConfig) {
            DBEnum.DB_TYPE_POSTRESQL -> service.getFish()
            DBEnum.DB_TYPE_SQLLITE -> service.getFishPython()
            else -> {
                Assert.fail("DB TYPE [%s] UNKNOW".format(dbConfig.name))
                return
            }
        }
        val responseFish = fishRequest.execute()
        val expected = 200
        var actual = responseFish.raw().code()
        Assert.assertEquals(expected, actual)



        val fishRequestgetAllFish = when (dbConfig) {
            DBEnum.DB_TYPE_POSTRESQL -> service.getAllFish()
            DBEnum.DB_TYPE_SQLLITE -> service.getFishPython()
            else -> {
                Assert.fail("DB TYPE [%s] UNKNOW".format(dbConfig.name))
                return
            }
        }
        val responseFishGetAllFish = fishRequestgetAllFish.execute()
        val expectedGetAllFish = 200
        var actualGetAllFish = responseFishGetAllFish.raw().code()
        Assert.assertEquals(expectedGetAllFish, actualGetAllFish)



        val retrofitChill = Retrofit.Builder()
            .baseUrl(urlChillCoding)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val serviceChill = retrofitChill.create(APIFishService::class.java)
        val courseRequest = serviceChill.listCourses()
        val responseCourse = courseRequest.execute()
        actual = responseCourse.raw().code()
        Assert.assertEquals(expected, actual)

    }

}