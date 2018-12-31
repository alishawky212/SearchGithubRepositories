package com.example.aabouriah.searchgithubrepokotlin.Entities

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class SearcheMainResponse(@SerializedName("total_count") var totalCount: Int,
                               @SerializedName("incomplete_results") var incompleteResults:Boolean,
                               @SerializedName("items") var items:ArrayList<Items>)
