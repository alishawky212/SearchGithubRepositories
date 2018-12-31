package com.example.aabouriah.searchgithubrepokotlin.Presentation

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.aabouriah.searchgithubrepokotlin.Domain.Repositories.SearchContract
import javax.inject.Inject

class ViewModelFactory @Inject constructor(var searchContract: SearchContract): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  MainViewModel(searchContract) as T
    }
}