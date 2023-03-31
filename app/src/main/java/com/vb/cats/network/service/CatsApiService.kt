package com.vb.cats.network.service

import com.vb.cats.modules.API_URL
import com.vb.cats.cats.model.CatResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsApiService {
    @GET(API_URL)
    fun getCats(
        @Query("api_key") key: String,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Observable<List<CatResponse>>
}
