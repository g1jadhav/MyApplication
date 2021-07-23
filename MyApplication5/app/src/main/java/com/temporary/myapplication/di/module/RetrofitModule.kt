package com.temporary.myapplication.di.module

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.temporary.myapplication.BuildConfig
import com.temporary.myapplication.network.ApiInterface
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import dagger.Module

@Module
object RetrofitModule {

    @Provides
    @Singleton
    @JvmStatic
    fun getApiInterface(): ApiInterface {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.spacexdata.com/")
            .client(getOkHttpClient())
            .build().create(ApiInterface::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val logging: HttpLoggingInterceptor?
        val httpClient: OkHttpClient.Builder?

        logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }

        httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging).readTimeout(240, TimeUnit.SECONDS)
            .connectTimeout(240, TimeUnit.SECONDS)

        return httpClient.build()
    }
}