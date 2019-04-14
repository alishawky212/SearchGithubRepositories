package com.example.aabouriah.searchgithubrepokotlin.Domain.Network

import com.example.aabouriah.searchgithubrepokotlin.Entities.ReposResponse
import com.example.aabouriah.searchgithubrepokotlin.Entities.SearcheMainResponse
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search/repositories")
     fun makeSearch(@Query("q") query: String): Observable<SearcheMainResponse>

    @GET("search/repositories")
    fun makeSearchAsync(@Query("q") query: String): Deferred<Response<ReposResponse>>
}