package com.example.aabouriah.searchgithubrepokotlin.Utiles


import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.resume


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

suspend fun <T : Any> Deferred<Response<T>>.awaitResult(): Result<T> {
    return suspendCancellableCoroutine { continuation ->

        GlobalScope.launch {
            try {
                val response = await()
                continuation.resume(
                        if (response.isSuccessful) {
                            val body = response.body()
                            body?.let {
                                Result.Ok(it, response.raw())
                            } ?: "error".let {
                                if (response.code() ==200){
                                    Result.Exception(Exception("body is empty"))
                                }
                                else{
                                    Result.Exception(NullPointerException("Response body is null"))
                                }


                            }

                        } else {
                            Result.Error(HttpException(response), response.raw())
                        }
                )
            }
            catch (e:Throwable){
                //  Log.e("DeferredAwait",e.message)
                continuation.resume(Result.Exception(e))
            }

        }

        registerOnCompletion(continuation)
    }
}



private fun Deferred<Response<*>>.registerOnCompletion(continuation: CancellableContinuation<*>) {
    continuation.invokeOnCancellation {
        if (continuation.isCancelled)
            try {
                cancel()
            } catch (ex: Throwable) {
                //Ignore cancel exception
                ex.printStackTrace()
            }
    }
}
