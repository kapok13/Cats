package com.vb.cats.di.modules

import com.vb.cats.network.service.CatsApiService
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

const val API_URL = "https://api.thecatapi.com/v1/images/search/"
const val API_KEY = "live_zn0WFCPDvXen8A6W1iRNUBYIMaRp3cY8Vgdl6cinY7ieJQMwMrads5ht7cuA2v4h"

val networkModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    single<CatsApiService> {
        (get(Retrofit::class.java) as Retrofit).create(CatsApiService::class.java)
    }
}
