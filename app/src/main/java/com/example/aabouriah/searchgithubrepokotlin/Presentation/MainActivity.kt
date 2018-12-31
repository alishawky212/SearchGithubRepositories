package com.example.aabouriah.searchgithubrepokotlin.Presentation

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.aabouriah.searchgithubrepokotlin.R
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

        viewModel.makeSearch(searchview.makeQueryObservable())
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