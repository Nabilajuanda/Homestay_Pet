package org.d3if4056.homestaypet.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://nabilajuanda.github.io/Homestay_Pet/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("Data_Json.json")
    suspend fun getData(): String
}

object HasilApi {
    val service: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}