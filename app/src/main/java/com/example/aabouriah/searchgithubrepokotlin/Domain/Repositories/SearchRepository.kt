package com.example.aabouriah.searchgithubrepokotlin.Domain.Repositories

import com.example.aabouriah.searchgithubrepokotlin.Domain.Network.SearchApi
import com.example.aabouriah.searchgithubrepokotlin.Entities.SearcheMainResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchRepository @Inject constructor(private val searchService:SearchApi):SearchContract {
    override fun searchRepos(query: String): Observable<SearcheMainResponse> {
      return searchService.makeSearch(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }
}