package com.example.aabouriah.searchgithubrepokotlin.Domain.Repositories

import com.example.aabouriah.searchgithubrepokotlin.Entities.SearcheMainResponse
import io.reactivex.Observable

interface SearchContract {
    fun searchRepos(query:String):Observable<SearcheMainResponse>
}