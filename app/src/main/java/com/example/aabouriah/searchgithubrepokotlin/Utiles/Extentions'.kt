package com.example.aabouriah.searchgithubrepokotlin.Utiles


import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


fun ImageView.loadImage(url:String){
    Glide.with(context).load(url).into(this)
}

fun ViewGroup.inflate(layoutId:Int,attachRoot:Boolean): View {
    return LayoutInflater.from(context).inflate(layoutId,this,attachRoot)
}

fun SearchView.makeQueryObservable():Observable<String>{
    val subject:PublishSubject<String> = PublishSubject.create()

    this.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(s: String): Boolean {
            subject.onComplete()
            return true
        }
        override fun onQueryTextChange(s: String): Boolean {
            subject.onNext(s)
            return true
        }
    })
    return subject
}