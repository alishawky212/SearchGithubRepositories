package com.example.aabouriah.searchgithubrepokotlin.Di

import com.example.aabouriah.searchgithubrepokotlin.Di.Modules.ApiModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton
import android.app.Application
import com.example.aabouriah.searchgithubrepokotlin.Domain.Network.SearchApi
import com.example.aabouriah.searchgithubrepokotlin.MyApp
import dagger.BindsInstance


@Singleton
@Component(modules = [AndroidInjectionModule::class,ApiModule::class,ActivityBuilder::class])
interface AppComponent {
    @Component.Builder
     interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
    fun inject(app:MyApp)
    val searchApi :SearchApi
}