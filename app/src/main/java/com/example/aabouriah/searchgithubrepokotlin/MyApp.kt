package com.example.aabouriah.searchgithubrepokotlin

import android.app.Activity
import android.app.Application
import com.example.aabouriah.searchgithubrepokotlin.Di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject



class MyApp : Application(),HasActivityInjector{

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    @Inject
    lateinit var dispatchingAndroidInjector : DispatchingAndroidInjector<Activity>
    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }
}