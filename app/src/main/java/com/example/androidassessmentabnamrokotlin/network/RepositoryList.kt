package com.example.androidassessmentabnamrokotlin.network

import com.squareup.moshi.Json

data class RepositoryList(
    val results: List<RepositoryData>
)

data class RepositoryData(
    @Json(name = "name") val name: String?,
    @Json(name = "private") val private: Boolean?,
    @Json(name = "visibility") val visibility: String?,
    @Json(name = "owner") val owner: RepositoryOwner
)

data class RepositoryOwner(
    @Json(name = "avatar_url") val avatar_url: String?
)

data class RepositoryInfo(
    @Json(name = "count") val count: Int,
    @Json(name = "pages") val pages: String?,
    @Json(name = "next") val next: String?,
    @Json(name = "prev") val prev: String?
)