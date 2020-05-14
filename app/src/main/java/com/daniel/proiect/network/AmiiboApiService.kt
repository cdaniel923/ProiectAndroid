package com.daniel.proiect.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlinx.coroutines.Deferred

private const val BASE_URL = "https://www.amiiboapi.com/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface AmiiboService
{
    @GET("amiibo")
    fun getAmiiboList(): Deferred<AmiiboList>
}

object AmiiboApi
{
    val retrofitService: AmiiboService by lazy {
        retrofit.create(AmiiboService::class.java)
    }
}
