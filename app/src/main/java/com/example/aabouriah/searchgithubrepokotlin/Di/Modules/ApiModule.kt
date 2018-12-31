package com.example.aabouriah.searchgithubrepokotlin.Di.Modules

import com.example.aabouriah.searchgithubrepokotlin.Domain.Network.SearchApi
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(client:OkHttpClient):Retrofit{
        return Retrofit
                .Builder()
                .client(client)
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideClient(interceptor: HttpLoggingInterceptor):OkHttpClient{
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideInterceptor():HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun provideSearchService(retrofit: Retrofit):SearchApi{
        return retrofit.create(SearchApi::class.java)
    }
}