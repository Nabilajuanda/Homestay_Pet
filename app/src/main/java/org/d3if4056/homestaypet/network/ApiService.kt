package org.d3if4056.homestaypet.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if4056.homestaypet.model.HasilData
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://nabilajuanda.github.io/Homestay_Pet/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("Data_Json.json")
    suspend fun getData(): List<HasilData>
}

object HasilApi {
    val service: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}