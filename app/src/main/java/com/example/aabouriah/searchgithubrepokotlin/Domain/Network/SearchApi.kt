package com.example.aabouriah.searchgithubrepokotlin.Domain.Network

import com.example.aabouriah.searchgithubrepokotlin.Entities.SearcheMainResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search/repositories")
    abstract fun makeSearch(@Query("q") query: String): Observable<SearcheMainResponse>
}