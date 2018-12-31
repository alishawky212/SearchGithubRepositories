package com.example.aabouriah.searchgithubrepokotlin.Presentation

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.aabouriah.searchgithubrepokotlin.Entities.Items
import com.example.aabouriah.searchgithubrepokotlin.R
import com.example.aabouriah.searchgithubrepokotlin.Utiles.inflate
import com.example.aabouriah.searchgithubrepokotlin.Utiles.loadImage
import kotlinx.android.synthetic.main.repo_item.view.*

class RepositoriesAdapter : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {


    private var reposList: ArrayList<Items> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(viewGroup.inflate(R.layout.repo_item,false))
    }

    override fun getItemCount(): Int {
       return reposList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item : Items = reposList[position]

        holder.userImage.loadImage(item.owner.avatar_url)
        holder.repoName.text = item.name
        holder.repoDescription.text = item.description
    }

     fun setRepos(reposList: ArrayList<Items>){
         this.reposList = reposList
         notifyDataSetChanged()
     }

     class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
         val userImage:ImageView = itemView.user_avatar
         val repoName : TextView = itemView.repo_name
         val repoDescription : TextView = itemView.repo_descrption
   }
}