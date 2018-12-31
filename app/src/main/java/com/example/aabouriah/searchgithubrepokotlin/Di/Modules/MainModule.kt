package com.example.aabouriah.searchgithubrepokotlin.Di.Modules

import android.arch.lifecycle.ViewModelProviders
import com.example.aabouriah.searchgithubrepokotlin.Domain.Network.SearchApi
import com.example.aabouriah.searchgithubrepokotlin.Domain.Repositories.SearchContract
import com.example.aabouriah.searchgithubrepokotlin.Domain.Repositories.SearchRepository
import com.example.aabouriah.searchgithubrepokotlin.Presentation.MainActivity
import com.example.aabouriah.searchgithubrepokotlin.Presentation.MainViewModel
import com.example.aabouriah.searchgithubrepokotlin.Presentation.RepositoriesAdapter
import com.example.aabouriah.searchgithubrepokotlin.Presentation.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideMainViewModel(activity:MainActivity,factory:ViewModelFactory):MainViewModel{
        return ViewModelProviders.of(activity,factory).get(MainViewModel::class.java)
    }

    @Provides
    fun provideAdapter():RepositoriesAdapter{
        return RepositoriesAdapter()
    }

    @Provides
    fun provideViewModelFactory(searchContract: SearchContract):ViewModelFactory{
        return ViewModelFactory(searchContract)
    }

    @Provides
    fun provideSearchRepo(searchApi: SearchApi):SearchContract{
        return SearchRepository(searchApi)
    }
}