package com.temporary.myapplication.network

import com.temporary.myapplication.pojoModel.RocketDetailsResponseModel
import com.temporary.myapplication.pojoModel.RocketListResponseModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("v4/rockets")
    fun getRocketDetails(
        @Query("id") key: String?
    ): Single<Response<RocketDetailsResponseModel>>

    @GET("v4/rockets")
    fun getRocketList(): Single<Response<List<RocketListResponseModel.Main>>>
}