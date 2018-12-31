package com.example.aabouriah.searchgithubrepokotlin.Presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.aabouriah.searchgithubrepokotlin.Domain.Repositories.SearchContract
import com.example.aabouriah.searchgithubrepokotlin.Entities.Items
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(private val searchContract: SearchContract) : ViewModel(){

    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

    val searchItems :MutableLiveData<ArrayList<Items>> = MutableLiveData()

     fun makeSearch(searchQuery:Observable<String>){
         compositeDisposable.add(
                 searchQuery.debounce(300, TimeUnit.MILLISECONDS)
                         .filter { s ->
                             if (s.isEmpty()) {
                                 false
                             } else {
                                 searchContract.searchRepos(s)
                                 true
                             }
                         }
                         .distinctUntilChanged()
                         .switchMap {
                             s -> searchContract.searchRepos(s)
                         }
                         .subscribe {
                             searcheMainResponse -> searchItems.setValue(searcheMainResponse.items)
                         }
         )
     }
}