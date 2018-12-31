package com.example.aabouriah.searchgithubrepokotlin.Di

import com.example.aabouriah.searchgithubrepokotlin.Di.Modules.MainModule
import com.example.aabouriah.searchgithubrepokotlin.Presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivit():MainActivity
}