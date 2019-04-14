package com.example.aabouriah.searchgithubrepokotlin.Presentation

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.aabouriah.searchgithubrepokotlin.Entities.Items
import com.example.aabouriah.searchgithubrepokotlin.Entities.Resource
import com.example.aabouriah.searchgithubrepokotlin.R
import com.example.aabouriah.searchgithubrepokotlin.R.id.repos_list
import com.example.aabouriah.searchgithubrepokotlin.R.id.searchview
import com.example.aabouriah.searchgithubrepokotlin.Utiles.makeQueryObservable
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel
    @Inject
    lateinit var repositoriesAdapter: RepositoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        initRecyclerView()

        viewModel.searchItems.observe(this, Observer {
            items-> repos_list.scrollToPosition(0)
            repositoriesAdapter.setRepos(items!!)
        })

        //viewModel.makeSearch(searchview.makeQueryObservable())

        viewModel.makeSearchAsync("Kotlin").observe(this, Observer {
            when (it?.status) {
                Resource.LOADING -> {
                    Log.d("MainActivity", "--> Loading...")
                }
                Resource.SUCCESS -> {
                    Log.d("MainActivity", "--> Success! | loaded ${it.data?.size ?: 0} records.")
                    repositoriesAdapter.setRepos(it.data as ArrayList<Items>)
                }
                Resource.ERROR -> {
                    Log.d("MainActivity", "--> Error!")
                    Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun initRecyclerView(){
        repos_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            adapter = repositoriesAdapter
        }
    }
}