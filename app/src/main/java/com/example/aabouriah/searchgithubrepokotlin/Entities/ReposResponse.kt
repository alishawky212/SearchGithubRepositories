package com.example.aabouriah.searchgithubrepokotlin.Entities

import com.example.aabouriah.searchgithubrepokotlin.Utiles.DataResponse

data class ReposResponse(val items: List<Items>):BaseApiResponse<Items>(),DataResponse<List<Items>> {
    override fun retrieveData(): List<Items> = items
}