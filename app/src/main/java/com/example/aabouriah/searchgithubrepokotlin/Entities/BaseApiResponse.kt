package com.example.aabouriah.searchgithubrepokotlin.Entities

abstract class BaseApiResponse<T> {
    var total_count: Int = 0
    var incomplete_results: Boolean = false
}