package com.example.aabouriah.searchgithubrepokotlin.Domain.Repositories

import com.example.aabouriah.searchgithubrepokotlin.Entities.ReposResponse
import com.example.aabouriah.searchgithubrepokotlin.Entities.SearcheMainResponse
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface SearchContract {
    fun searchRepos(query:String):Observable<SearcheMainResponse>
    fun searchReposAsync(query:String):Deferred<Response<ReposResponse>>
}